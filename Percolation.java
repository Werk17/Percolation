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

    public Percolation(int N)  {
        // create N-by-N grid, with all sites initially blocked
        boolean grid[][] = new boolean[N][N];
        // initialize weighted quick union find with N * N + 1 element (top gate)
        WQUF = new WeightedQuickUnionUF(N*N + 1);
        
    }
    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (grid[row][col] == true) {
            
        } else {
            grid[row][col] = true;
            openSites++;
        }

    }
    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        return grid[row][col]; //FIXME
    }
    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        // if open site is on top row
        // if(grid[row][col] && row == 0) {
        //     return true;
        // } else if(grid[row][col] && row > 0) {
        //     isOpen(row - 1, col);
        //     isFull(row-1, col);
        // }
        
        return false; //FIXME
    }
    public int numberOfOpenSites() {
        // number of open sites
        return openSites; //FIXME
    }
    public boolean percolates() {
        // does the system percolate?
        return false; //FIXME
    }

    public static void main(String[] args) {
        // unit testing (suggested)
    }
}