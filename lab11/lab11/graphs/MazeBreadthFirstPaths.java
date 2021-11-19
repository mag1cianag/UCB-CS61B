package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.awt.desktop.QuitEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    //source
    private int s;
    // target
    private int t;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        this.maze =m;

        this.s = m.xyTo1D(sourceX, sourceY);
        this.t = m.xyTo1D(targetX, targetY);
        edgeTo[s] = s;
        distTo[s] = 0;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int q = queue.remove();
            marked[q] = true;
            announce();
            if(q == t) {
                return ;
            }
            // neighbours
            for (int n : maze.adj(q)) {
                if(marked[n]) {
                    continue;
                }
                queue.add(n);
                edgeTo[n] = q;
                announce();
                distTo[n] = distTo[q] + 1;
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

