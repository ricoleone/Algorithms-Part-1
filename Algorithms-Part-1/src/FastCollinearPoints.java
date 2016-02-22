import java.util.Arrays;

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

public class FastCollinearPoints {

    private LineSegment[] segments = new LineSegment[0];
    
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("argument is null");
        }
        for (Point p: points) {
            if (p == null) {
                throw new NullPointerException("argument array contains a null");
            }
        }
        Point[] refPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            refPoints[i] = points[i];
        }
        for (int i = 0; i < points.length; i++) {
            // preserve the order for checking every point
            Point referencePoint = refPoints[i];
            Arrays.sort(points, referencePoint.slopeOrder());
            int counter       = 0;
            int startIndex    = 0;
            int stopIndex     = 0;
            double slopeCheck = referencePoint.slopeTo(points[0]);
            for (Point p: points) {
                if (counter == 0) {
                    slopeCheck = referencePoint.slopeTo(p);
                    counter++;
                }
                else if (referencePoint.slopeTo(p) == slopeCheck) {
                    stopIndex++;
                    counter++;
                }
                else if (counter < 3) {
                    slopeCheck = referencePoint.slopeTo(p);
                    startIndex = ++stopIndex;
                    counter = 1;
                }
                else { //only print if the reference point is the smallest 
                      //point in the array, this eliminates duplicates
                     addLineSegment(startIndex, stopIndex, referencePoint, points);
                     slopeCheck = referencePoint.slopeTo(p);
                     startIndex = stopIndex++;
                     counter = 1;
                } 
                //if the last point p in the pointArray was added to pointsOnALine
                //check for printing 
            }
            if (counter >= 3) {
               addLineSegment(startIndex, stopIndex, referencePoint, points);
            }
          }     
        
    }
    
    private void addLineSegment(int start, int stop, Point refPoint, Point[] points) {
        Arrays.sort(points, start, stop + 1);
        if (refPoint.compareTo(points[start]) < 0 ) {
             segments = addElement(segments, new LineSegment(refPoint, points[stop]));
         }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.println("Number of Linesegments = " + collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
            StdDraw.show(0);
        }
    }
}
