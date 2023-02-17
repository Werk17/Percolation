/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][];
    private WeightedQuickUnionUF WQUF;
    private int openSites;
    private int SIZE;
    private int TOP_SITE;
    private int BOTTOM_SITE;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("Can't use N <= 0");
        // create N-by-N grid, with all sites initially blocked
        grid = new boolean[N][N];
        // initialize weighted quick union find with N * N + 1 element (top gate)
        WQUF = new WeightedQuickUnionUF(N * N + 1);
        TOP_SITE = (N * N);
        BOTTOM_SITE = (N * N) + 1;
        SIZE = N;
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (validate(row, col) && grid[row][col]) {
            // if open site is on top row
            if (row == 0) {
                // connect top node with this cell
                WQUF.union(convert1D(row, col), TOP_SITE);
                // connect bottom cell
                if (isOpen(row + 1, col)) WQUF.union(convert1D(row + 1, col), convert1D(row, col));
                //connect left cell
                if (isOpen(row, col - 1)) WQUF.union(convert1D(row, col - 1), convert1D(row, col));
                // right cell
                if (isOpen(row, col + 1)) WQUF.union(convert1D(row, col + 1), convert1D(row, col));
            }
            // check cell above if row >= 1
            else if (row >= 1) {
                // connect bottom cell
                if (isOpen(row + 1, col)) WQUF.union(convert1D(row + 1, col), convert1D(row, col));
                //connect left cell
                if (isOpen(row, col - 1)) WQUF.union(convert1D(row, col - 1), convert1D(row, col));
                // right cell
                if (isOpen(row, col + 1)) WQUF.union(convert1D(row, col + 1), convert1D(row, col));
                // connect above cell
                if (isOpen(row - 1, col)) WQUF.union(convert1D(row - 1, col), convert1D(row, col));

            }
            else if (row == SIZE) {
                {
                    WQUF.union(convert1D(row, col), BOTTOM_SITE);
                    //connect left cell
                    if (isOpen(row, col - 1))
                        WQUF.union(convert1D(row, col - 1), convert1D(row, col));
                    // right cell
                    if (isOpen(row, col + 1))
                        WQUF.union(convert1D(row, col + 1), convert1D(row, col));
                    // connect above cell
                    if (isOpen(row - 1, col))
                        WQUF.union(convert1D(row - 1, col), convert1D(row, col));
                }

            }
            else {
                throw new IndexOutOfBoundsException();
            }
        } //outer if end
        openSites++;
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        if (validate(row, col)) {
            return grid[row][col];
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isFull(int row, int col) {
        if (validate(row, col)) {
            // is the site (row, col) full?
            return (WQUF.find(convert1D(row, col)) == WQUF.find(TOP_SITE));
        }
        else {
            throw new IndexOutOfBoundsException();
        }

    }

    public int numberOfOpenSites() {
        // number of open sites
        return openSites;
    }

    public boolean percolates() {
        // does the system percolate?
        return WQUF.find(TOP_SITE) == WQUF.find(BOTTOM_SITE);
    }

    private boolean validate(int row, int col) {
        if (row < 0 || row > SIZE || col < 0 || col > SIZE) {
            return false;
        }
        return true;
    }

    private int convert1D(int row, int col) {
        int oneDIndex = (row * SIZE) + col;
        return oneDIndex;
    }


    public static void main(String[] args) {
        // unit testing (suggested)
    }
}