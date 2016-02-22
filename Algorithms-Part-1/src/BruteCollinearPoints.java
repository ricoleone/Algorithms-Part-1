/*************************************************************************
 *  Author:       Richard Leone
 *  Date:         February 21, 2016
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java Brute input.txt
 *  Dependencies: Point.java, In.java, StdDraw.java, Quick.java
 *
 *  Takes the name of a file as a command-line argument.
 *  Reads in an integer N followed by N pairs of points (x, y)
 *  with coordinates between 0 and 32,767, and plots them using
 *  standard drawing.
 *
 *************************************************************************/
import java.util.Arrays;

import edu.princeton.cs.algs4.Quick;

public class BruteCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("argument is null");
        }
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException("argument array contains a null");
            }

        }
        Quick.sort(points);
        // find line segments four points at a time
        int N = points.length;
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int m = k + 1; m < N; m++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[m];
                        if ((p.slopeTo(q) == q.slopeTo(r))
                                && (q.slopeTo(r) == r.slopeTo(s))) {
                            segments = addElement(segments, new LineSegment(p, 
                                    s));
                        }
                    }
                }
            }
        }
        // display to screen all at once
        StdDraw.show(0);
    }

    public int numberOfSegments() {

        return segments.length;
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++) {
            temp[i] = segments[i];
        }
        return temp;
    }

    private LineSegment[] addElement(LineSegment[] a, LineSegment ls) {
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = ls;
        return a;
    }

    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println("Number of Linesegments = " + collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
            StdDraw.show(0);
        }
    }
}
