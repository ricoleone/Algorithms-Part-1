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

public class BruteCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];
    private Point[] points;

    public BruteCollinearPoints(Point[] pointsArray) {
        if (pointsArray == null) {
            throw new NullPointerException("argument is null");
        }
        for (Point p : pointsArray) {
            if (p == null) {
                throw new NullPointerException("argument array contains a null");
            }
            if (duplicates(pointsArray)) {
                throw new IllegalArgumentException("duplicate points found");
            }

        }
        points = new Point[pointsArray.length];
        for (int i = 0; i < pointsArray.length; i++) {
            points[i] = pointsArray[i];
        }
        Arrays.sort(points);
        // find line segments four points at a time
        int N = points.length;
        for (int i = 0; i < N - 3; i++) {
            Point p = points[i];
            for (int j = i + 1; j < N - 2; j++) {
                Point q = points[j];
                for (int k = j + 1; k < N - 1; k++) {
                    Point r = points[k];
                    if (p.slopeTo(q) == q.slopeTo(r)) {
                        for (int m = k + 1; m < N; m++) {
                            Point s = points[m];
                            if (q.slopeTo(r) == r.slopeTo(s)) {
                                segments = addElement(segments,
                                        new LineSegment(p, s));
                            }
                        }
                    }
                }
            }
        }
        // display to screen all at once
        // StdDraw.show(0);
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

    private boolean duplicates(Point[] pts) {
        for (int i = 0; i < pts.length; i++) {
            for (int j = 0; j != i && j <= pts.length; j++) {
                if (pts[i].compareTo(pts[j]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
