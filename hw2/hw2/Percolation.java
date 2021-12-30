package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation {

    private WeightedQuickUnionUF wquf;
    private WeightedQuickUnionUF uf;
    private int numOpenSites;
    private boolean[] grid;
    private int n;
    /* the index of virtual top site */
    private int top;
    /* the index of virtual bottom site */
    private int bottom;

    /**
     * create N-by-N grid, with all sites initially blocked
     *
     * @param N grid Number
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Illegal Argument");
        }
        wquf = new WeightedQuickUnionUF(N * N + 2);
        uf = new WeightedQuickUnionUF(N * N + 2);
        n = N;
        numOpenSites = 0;
        grid = new boolean[N * N];
        top = N * N;
        bottom = N * N - 1;
        for (int i = 0; i < N; i++) {
            // union virtual top site
            wquf.union(i, top);
            // union virtual bottom site
            wquf.union(xyToOneD(N - 1, i), bottom);
            //
            uf.union(i, top);
        }
    }

    /**
     * open the site (row, col) if it is not open already
     *
     * @param row number
     * @param col number
     */
    public void open(int row, int col) {
        validate(row, col);
        if (!this.grid[xyToOneD(row, col)]) {
            this.grid[xyToOneD(row, col)] = true;
            this.numOpenSites += 1;
            for (Integer index : neighbours(row, col)) {
                if (this.grid[index]) {
                    wquf.union(index, xyToOneD(row, col));
                    uf.union(index, xyToOneD(row, col));
                }
            }
        }
    }

    /**
     * @param row number
     * @param col number
     * @return is the site (row,col) open?
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return this.grid[xyToOneD(row, col)];
    }

    /**
     * @param row number
     * @param col number
     * @return is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) && uf.connected(top, xyToOneD(row, col));
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    /**
     * @return does the system precolate?
     */
    public boolean percolates() {
        return wquf.connected(top, bottom);
    }

    private void validate(int row, int col) {
        if (row < n && row >= 0 && col >= 0 && col < n) {
            return;
        }
        throw new java.lang.IndexOutOfBoundsException("index out of bound");
    }

    /**
     * assume arguments have been already validated
     *
     * @param row number
     * @param col number
     * @return the index
     */
    private int xyToOneD(int row, int col) {
        return row * n + col;
    }

    /**
     * assume arguments have been already validated
     *
     * @param row number
     * @param col number
     * @return the neighbours' index
     */
    private ArrayList<Integer> neighbours(int row, int col) {
        ArrayList<Integer> indexs = new ArrayList<>();

        indexs.add(xyToOneD(Math.max(0, row - 1), col));
        indexs.add(xyToOneD(Math.min(n - 1, row + 1), col));
        indexs.add(xyToOneD(row, Math.max(0, col - 1)));
        indexs.add(xyToOneD(row, Math.min(n - 1, col + 1)));
        return indexs;
    }

    /**
     * @param args arguments
     */
    public static void main(String[] args) {
    }
}
