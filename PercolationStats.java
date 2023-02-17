/* *****************************************************************************
 *  Name:  Charles Werk
 *  Last modified:     2-16-23
 **************************************************************************** */


public class PercolationStats {
    private double[] aux;
    private int experiments;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        experiments = T;
        // No grid size or trials supplies
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Given n <= 0 || trials <= 0");
        }
        // initialize gride size
        aux = new double[N];
        for(int trial = 0; trial < experiments; trial++) {
            int openSites = 0;
            Percolation percolation = new Percolation(N);

        //     open sites until percolation is reached
            while (!percolation.percolates()) {
                // assuming range is between 0 - N exclusive
                int randRow = StdRandom.uniform(0, N + 1);
                int randCol = StdRandom.uniform(0, N + 1);
                // if site now open, the open. Using 0-based indexing.
                if(!percolation.isOpen(randRow,randCol) && !percolation.isFull(randRow,randCol)) {
                    percolation.open(randRow, randCol);
                }
            }
            openSites = percolation.numberOfOpenSites();
            aux[trial] = openSites/N*N;
        }

    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(aux); //FIXME
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(aux); //FIXME
    }

    public double confidenceLow() {
        // low  endpoint of 95% confidence interval
        return  mean() - ((1.96 * stddev()) / Math.sqrt(experiments)); //FIXME
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return -9999.9999; //FIXME
    }

    public static void main(String[] args) {
        // Not required for the API, but useful to test if
        // your code is doing reasonable things
        PercolationStats st = new PercolationStats(10, 10);
        // ...
    }
}