/*/////////////////////////////////////////////////////////////////////////////
 * Author:       Richard Leone
 * Written:      9/7/13
 * Last Updated  9/8/13
 *
 * Compilation:  javac Percolation
 * Execution:    java Percolation N (takes one command line argument N, the
 * dimension of the row or column
 *
 *
 * Name:         Percolation Class
 * Description:  Stores percolation sites in an NxN matrix. Use Weighted
 * Quick Union algorithm to connect sites. When sites are connected from top
 * to bottom, the system has achieved percolation
 * Dependencies: WeightedQuickUnionUF.class of the algs4.jar library
*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    // number of rows from input N (since the system is a square, the number
	//of columns equals the number of rows)
	private int rows;
	//location of the virtual Top node in the grid
	private int virtualTop;
	//location of the virtual Bottom node in the grid
	private int virtualBottom;
	//openSites represents the NxN 2D sites that are open, if i,j is open then
	//its value is true
	private boolean openSites[][];
	//fullSites represents the NxN 2D sites that are open, if i,j is open then
    //its value is true
	private boolean fullSites[][];

	//the grid used to determine if a site is full and if the system
	//percolates
	private WeightedQuickUnionUF grid;

	// Constructor creates N-by-N grid, with all sites blocked
	 public Percolation(int N) {
		 if(N <= 0) {
			 throw new IllegalArgumentException("N is less than or eqaul to 0");
		 }
		 rows = N;
		 virtualTop = 0;
		 virtualBottom = N*N +1;

         //Note index goes from 1 to N so the zeroth row and columns are
		 //not be used.
		 openSites = new boolean[N + 1][N + 1];
		 fullSites = new boolean[N + 1][N + 1];

		 /*
		  * The size of the grid array is NxN + 2; with two additional
		  * elements to support a virtual node the top row will connect
		  * to (at site 0) and a virtual node that will connect to the
		  * bottom row at site N*N +1). The constructor connects the top
		  * row the virtual node at site 0 and the bottom row to the
		  * virtual node at site N*N + 1. The first row occupies sites
		  * 1 through N, second row occupies sites N + 1 to 2N, and the
		  * last row occupies sites (N*(N-1)) + 1 to N*N.
		  * 11,12,13     1,2,3
		  * 21,22,23 ->  4,5,6
		  * 31,32,33     7,8,9
		  */
		 grid = new WeightedQuickUnionUF(N*N + 2);

	 }
     /**
      * open site (row i, column j) if it is not already open
	  * then connects to all open neighbors.
      * @param i row of the NxN matrix
      * @param j column of the NxN matrix
      */
    public void open(int i, int j) {
        if (isOpen(i, j) ) {
		    return;
        }
		openSites[i][j] = true;
		/*
		 * connect to site above location i,j if it is open. If i is the top
		 *  row then connect to virtualTop
		 */
        if ( i > 1 && isOpen(i - 1, j)) {
		    grid.union(mapSite(i, j), mapSite(i-1, j));
		}
		else if (i == 1) {
		    grid.union(virtualTop, mapSite(i, j));
		}

		//connect to site below i,j if it is open. If j is the bottom row
		//then connect to virtualBottom
		if (i < rows && isOpen(i + 1, j)) {
			  grid.union(mapSite(i, j), mapSite(i + 1, j));
		}
		else if (i == rows) {
		    grid.union(virtualBottom, mapSite( i, j));
		}
		//connect to site to the right of i,j, if it is open. If column j
		//is the right edge then do nothing
		if ((j < rows) && isOpen(i, j+1)) {
			grid.union(mapSite(i, j),mapSite(i, j+1));
		}
		//connect to site to the left of i,j, if it is open. If column j is
		//the left edge then do nothing
		if ((j > 1) && isOpen(i, j - 1)) {
		    grid.union(mapSite(i, j),mapSite(i, j-1));
        }
	    //fullSites[i][j] = grid.connected(virtualTop, mapSite(i, j));
    }

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		   if (checkBounds(i,j))
		       return openSites[i][j];
		   else return false;
		}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		if(isOpen(i, j))
			return grid.connected(virtualTop, mapSite(i, j));
		else return false;
		//if (checkBounds(i,j))
		      // return fullSites[i][j];
		   //else return false;
	}

	// does the system percolate?
	public boolean percolates() {
		return grid.connected(virtualTop, virtualBottom);
		}

	// Private Method to Resolve site of i and j to its array id number to
	// pass to WeightedQuickUnionUF
	// The output will be used to pass a p or q to the methods in the
	// WightedQuickUnionUF API.
	private int mapSite(int i, int j) {
		   if (checkBounds(i, j))
		      return (i - 1) * rows + j;
		   else return 0;

	   }
	//Private method to determine if i, j is inbounds i.e. >=1 and <=N
	private boolean checkBounds(final int i, final int j) {
	    if (i < 1 || i > rows) {
		    throw new IndexOutOfBoundsException("row index i out of" +
		    " bounds");
	    }
		else if (j < 1 || j > rows) {
		    throw new IndexOutOfBoundsException("column index j out of" +
		    " bounds");
		}
		else return true;
	}
	//private method that displays the contents of openSites
	private void printSites() {
		 StdOut.println("-----------------");
		   for (int i = 1; i <= rows; i++)
			   	for (int j = 1; j <= rows; j++) {
						StdOut.println("site[" + i +  "][" + j +"] = "
					+isOpen(i,j) + " Full = " + isFull(i,j));
				}
		   StdOut.println("-----------------");
	   }

	// Test routine for Percolate class. for a given input dimension N
    // the test will connect all sites in a column then check for percolation
	// it runs on all columns numbered 1 through N and prints if percolated
	// after each column has been connected
	public static void main(String [] args){
		int N = Integer.parseInt(args[0]);
		Percolation perco = new Percolation(N);
		  
		//check that all columns percolate independently
		for (int j = 1; j <= N; j++) {
			  for (int i = 1; i <= N; i++) {
		          perco.open(i, j);  
			  }
			  perco.printSites();
		      if (perco.percolates()) {
	            StdOut.println(" System percolates for N =" + N 
	            		+ " colunm= " + j + ", as it should");
	          }
        }
    }
}

