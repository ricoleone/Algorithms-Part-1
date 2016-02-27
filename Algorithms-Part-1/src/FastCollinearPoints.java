

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

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Quick;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private Point[] points;
    private Point[] refPoints;
    private int numSegments;
    
    /**
     * 
     * @param pointsArray
     */
    public FastCollinearPoints(Point[] pointsArray) {
        
        if (pointsArray == null) {
            throw new NullPointerException("argument is null");
        }
        //set up internal copy of input array and check for null entries and duplicates
        points = new Point[pointsArray.length];
        for (int i = 0; i < pointsArray.length; i++) {
            points[i] =  pointsArray[i];
            if (points[i] == null) {
                throw new NullPointerException("argument array contains a null");
            }
            for (int j = i + 1; j < pointsArray.length; j++) {
                if (pointsArray[i].compareTo(pointsArray[j]) == 0) {
                    throw new IllegalArgumentException("duplicate points found");
                }
            }
        }
        if (points.length < 20) {
            Insertion.sort(points);
        }
        else {
            Quick.sort(points);
        }
        refPoints = new Point[points.length];
        System.arraycopy(points, 0, refPoints, 0, points.length);
        segments = new LineSegment[points.length]; //allocate enough space to hold line segments, but less than points
        numSegments = 0;
        for (int i = 0; i < points.length; i++) {
            // preserve the order for checking every point
            Point referencePoint = refPoints[i];
            if (points.length < 20) {
                Insertion.sort(points);
            }
            else {
                Arrays.sort(points);
            }
            Arrays.sort(points, referencePoint.slopeOrder());
            int subSegments   = 0;
            int startIndex    = 0;
            int stopIndex     = 0;
            double slopeCheck = 0.0;
            for (Point p: points) {
                if (subSegments == 0) {
                    slopeCheck = referencePoint.slopeTo(p);
                    subSegments++;
                }
                else if (referencePoint.slopeTo(p) == slopeCheck) {
                    stopIndex++;
                    subSegments++;
                }
                else if (subSegments < 3) {
                    slopeCheck = referencePoint.slopeTo(p);
                    startIndex = ++stopIndex;
                    subSegments = 1;
                }
                else { 
                     addLineSegment(startIndex, stopIndex, referencePoint, points);
                     slopeCheck = referencePoint.slopeTo(p);
                     startIndex = ++stopIndex;
                     subSegments = 1;
                } 
                
            }
            //Check that the last set of points has at least 3 collinear points
            if (subSegments >= 3) {
               addLineSegment(startIndex, stopIndex, referencePoint, points);
            }
          }     
        
    }
    /**
     *To eliminate duplicates only create line a LineSegment object if the reference point 
     *is the smallest point on the segment. The initial size of segements is equal to the
     *number of points, it will grow dynamically as needed by a factor of 2 x (the current size).
     * @param start
     * @param stop
     * @param refPoint
     * @param pts
     */
    private void addLineSegment(int start, int stop, Point refPoint, Point[] pts) {
        if (refPoint.compareTo(pts[start]) < 0) {
            if (numSegments == segments.length) {
                LineSegment[] temp = new LineSegment[segments.length * 2];
                for (int i = 0; i < segments.length; i++) {
                    temp[i] = segments[i];
                }
                segments = temp;
            }
            segments[numSegments++] = new LineSegment(refPoint, points[stop]);
         }
    }
    /**
     * 
     * @return
     */
    public int numberOfSegments() {
        return numSegments;
    }
    /**
     * 
     * @return
     */
    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[numSegments];
        System.arraycopy(segments, 0, temp, 0, numSegments);
        return temp;
    }
}
