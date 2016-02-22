import edu.princeton.cs.algs4.Quick;

/*************************************************************************
 *  Author:       Richard Leone
 *  Date:         September 24, 2013
 *  Compilation:  javac Brute.java
 *  Execution:    java Brute input.txt
 *  Dependencies: Point.java, In.java, StdDraw.java, Quick.java
 *
 *  Takes the name of a file as a command-line argument.
 *  Reads in an integer N followed by N pairs of points (x, y)
 *  with coordinates between 0 and 32,767, and plots them using
 *  standard drawing.
 *
 *************************************************************************/

public class Brute {
    public static void main(String[] args) {

        // scale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        StdOut.println("filename = " + filename);
        In in = new In(filename);
        int N = in.readInt();
        Point [] pointArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            pointArray[i] = p;
            p.draw();
        }
        Quick.sort(pointArray);
        // find line segments four points at a time
        for ( int i = 0; i < N - 3; i++ ) {
            for ( int j  = i + 1; j < N - 2; j++) {
                    for ( int k = j + 1; k < N - 1; k++) {
                        for ( int m = k + 1; m < N; m++) {
                            Point p = pointArray[i];
                            Point q = pointArray[j];
                            Point r = pointArray[k];
                            Point s = pointArray[m];
                            if( ( p.slopeTo(q) == q.slopeTo(r)) && 
                                    (q.slopeTo(r) == r.slopeTo(s)) ) {
                                p.drawTo(q);
                                q.drawTo(r);
                                r.drawTo(s);
                                StdOut.println( p.toString() + "->" + 
                                q.toString() + "->" +
                                r.toString() + "->" + s.toString());
                                StdDraw.show(0);
                           }
                        }
                    }
            }
        }
        // display to screen all at once
        StdDraw.show(0);
    }
}