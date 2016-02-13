/** ---------------------------------------------------------------------------
 * Author:       Richard Leone
 * Written:      9/8/13
 * Last Updated  9/8/13
 * Compilation:  javac PercolationStats
 * Execution:    java PercolationStats N T (N is dimension, T is trials)
 *               dimension of the row or column
 * Name:         PercolationStats Class
 * Description:  PrecolationStats computes T trials of an NxN array of sites to
 *               determine the percolation threshold. The mean, standard
 *               deviation and 95% confidence are also calculated.
 * Dependencies: WeightedQuickUnionUF.class of the algs4.jar library and
 *               Percolation.class
*/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	// dimension of NxN array of sites
	private int N;
	//number of independent trials to percolate;
	private int T;
	// mean of the number of open sites for T trial run
	private double mean;
	// Standard deviation of the number of open sites for T trial runs
	private double sigma;
	// stores the percentage of the number of open sites for each trial run
	//private double [] threshold;
	// Percolation object to perform percolation experiments
	//private Percolation percolationSystem;
	/**
	 * perform T independent computational experiments on an N-by-N grid
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T) {
		if (N <= 0 || T<= 0 )
			throw new IllegalArgumentException("N or T is less than or eqaul to 0");
		this.N                 = N;
		this.T                 = T;
		this.mean              = 0.0;
		this.sigma             = 0.0;
		double [] threshold    = new double[T];
		// i is row and j is column for identifying a site in a 2x2 array
		int i;
		int j;
		double siteCount;
		double nSquared;
				
		for (int k = 0; k < T; k++) {
			int openSiteCount = 0;
			Percolation percolationSystem = new Percolation(N);
			while ( !percolationSystem.percolates()) {
				i = StdRandom.uniform(1,N + 1);
				j = StdRandom.uniform(1,N + 1);
				if (!percolationSystem.isOpen(i, j)) {
					percolationSystem.open( i, j );
						 openSiteCount++;
				}
			}
			siteCount = openSiteCount;
			nSquared  = N*N;
			threshold[k] = siteCount / nSquared;
			
			mean = StdStats.mean(threshold);
			sigma = StdStats.stddev(threshold);
		}
	}
		
	
	/**
	 * sample mean of percolation threshold
	 * @return
	 */
	public double mean() {
		return mean;
	}
	
	/**
	 * sample standard deviation of percolation threshold
	 * @return
	 */
	public double stddev() {
		if (T == 1){
			return Double.NaN;
		}
		return sigma;
	}
	
	/**
	 * returns lower bound of the 95% confidence interval
	 * @return
	 */
	public double confidenceLo() {
		return mean - (1.96 * sigma)/Math.sqrt(T);
	}
	
	/**
	 * returns upper bound of the 95% confidence interval
	 * @return
	 */
	public double confidenceHi() {
		return mean + (1.96 * sigma)/Math.sqrt(T);
	}
	/**
	 *
	 */
	
	/**
	 * Test Client
	 * @param args N, T
	 */
	public static void main(String [] args){
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats percolationSystemStats = new PercolationStats(N, T);
		

		
		StdOut.println("mean                    = "
		+ percolationSystemStats.mean());
		StdOut.println("stddev                  = "
		+ percolationSystemStats.stddev());
		StdOut.println("95% confidence interval = "
		+ percolationSystemStats.confidenceLo() + ", "
		+ percolationSystemStats.confidenceHi());

  }
}