package hw2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class PercolationStats {
    private ArrayList<Double> list;
    private int times;

    /**
     * perform T independent experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("IllegalArguments");
        }
        times = T;
        double total = N*N;
        list = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
            }
            list.add(p.numberOfOpenSites()/total);
        }
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        double sum = 0;
        for (Double val : list) {
            sum += val;
        }
        return sum / times;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        double m = this.mean();
        double sum = 0.0;
        for (Double val : list) {
            sum += (val - m) * (val - m);
        }
        return Math.sqrt(sum / (times - 1));
    }

    /**
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }
}
