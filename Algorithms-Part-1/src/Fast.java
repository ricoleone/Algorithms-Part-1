/*************************************************************************
 *  Author:       Richard Leone
 *  Date:         September 24, 2013
 *  Compilation:  javac Fast.java
 *  Execution:    java Fast input.txt
 *  Dependencies: Point.java, In.java, StdDraw.java
 *
 *  Takes the name of a file as a command-line argument.
 *  Reads in an integer N followed by N pairs of points (x, y)
 *  with coordinates between 0 and 32,767, and plots them using
 *  standard drawing.
 *
 *************************************************************************/
import java.util.Arrays;

public class Fast {
    
    private int N ;
    //private String fileName;
    private Point [] pointsArray;
    private Point [] referencePointArray;
    public Fast () {
        StdDraw.setXscale( 0, 32768 );
        StdDraw.setYscale( 0, 32768 );
        StdDraw.show(0);
          // read in the input
        //fileName = args[0];
        //In in = new In(fileName);
        
        N = StdIn.readInt();
        pointsArray = new Point[N];
        referencePointArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            Point p = new Point(x, y);
            pointsArray[i] = p;
            referencePointArray[i] = p;
            p.draw();
        }
        //print the points
        StdDraw.show(0);
        // identify lines to print
        findLines();
        //print the lines
        //printLines();
    }
    private Fast(String file) {
        
        StdDraw.setXscale( 0, 32768 );
        StdDraw.setYscale( 0, 32768 );
        StdDraw.show(0);
          // read in the input
        //fileName = args[0];
        In in = new In(file);
        N = in.readInt();
        pointsArray = new Point[N];
        referencePointArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            pointsArray[i] = p;
            referencePointArray[i] = p;
            p.draw();
        }
        StdDraw.show(0);
        findLines();
    }
        
    private void printLine(int startIndex, int stopIndex, Point referencePoint){
        Arrays.sort(pointsArray, startIndex, stopIndex);
        //StdOut.println( "point at startIndex = " + pointsArray[startIndex].toString());
        //StdOut.println( "reference point = " + referencePoint.toString());
        //StdOut.println( "slope = " +referencePoint.slopeTo(pointsArray[startIndex]));
        if (referencePoint.compareTo(pointsArray[startIndex]) < 0 ) {
             StdOut.print( referencePoint.toString() + "->");
                 referencePoint.drawTo(pointsArray[startIndex]);
             for (  int k = startIndex; k < stopIndex; k++) {
                 //StdOut.print( "slope = " +referencePoint.slopeTo(pointsArray[k]));
                 StdOut.print( pointsArray[k].toString() + "->");
                 pointsArray[k].drawTo(pointsArray[k + 1]);
             }
             StdOut.println( pointsArray[stopIndex].toString());
             StdDraw.show(0);
         }
    }
    private void findLines() {
        for ( int i = 0; i < N; i++) {
             // preserve the order for checking every point
             Point referencePoint = referencePointArray[i];
             Arrays.sort(pointsArray, referencePoint.slopeOrder() );
             int counter       = 0;
             int startIndex    = 0;
             int stopIndex     = 0;
             double slopeCheck = referencePoint.slopeTo(pointsArray[0]);
             for ( Point p: pointsArray){
                 if( counter == 0 ){
                     slopeCheck = referencePoint.slopeTo(p);
                     counter++;
                 }
                 else if( referencePoint.slopeTo(p) == slopeCheck ){
                     stopIndex++;
                     counter++;
                    }
                 else if ( counter < 3 ) {
                     slopeCheck = referencePoint.slopeTo(p);
                     startIndex = ++stopIndex;
                     counter = 1;
                 }
                 else{ //only print if the reference point is the smallest 
                       //point in the array, this eliminates duplicates
                      printLine(startIndex, stopIndex, referencePoint);
                      slopeCheck = referencePoint.slopeTo(p);
                      startIndex = ++stopIndex;
                      counter = 1;
                 } 
                 //if the last point p in the pointArray was added to pointsOnALine
                 //check for printing 
             }
             if( counter >= 3 ) {
                printLine(startIndex, stopIndex, referencePoint);
             }
           }     
    }

    // quicksort the subarray from a[lo] to a[hi]
    private void sort(Point[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

 // partition the subarray a[lo .. hi] by returning an index j
    // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private int partition(Point[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Point v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }
        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

 // is v < w ?
    private boolean less(Point v, Point w) {
        return (v.compareTo(w) < 0);
    }
        
    // exchange a[i] and a[j]
    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    
    public static void main(String[] args) {

        // scale coordinates and turn on animation mode
        

        Fast fastTest = new Fast(args[0]);
     }
}
