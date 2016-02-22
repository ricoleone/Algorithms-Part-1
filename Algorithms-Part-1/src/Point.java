/*************************************************************************
 * Name:   Richard Leone
 * Email:  richard.a.leone@gmail.com
 * Date:   02/21/2016
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x; // x coordinate
    private final int y; // y coordinate

    /**
     * Comparator for comparing slope order between points
     * @author rico
     *
     */
    private class SlopeOrder implements Comparator<Point> {

        public int compare(Point point1, Point point2) {
            Double pointOneSlope = Point.this.slopeTo(point1);
            Double pointTwoSlope = Point.this.slopeTo(point2);
            if (pointOneSlope < pointTwoSlope)
                return -1;
            else if (pointOneSlope > pointTwoSlope)
                return 1;
            else
                return 0;
        }
    }

    /**
     * Initializes a new point.
     *
     * @param x
     *            the <em>x</em>-coordinate of the point
     * @param y
     *            the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to
     * standard draw.
     *
     * @param that
     *            the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally,
     * if the two points are (x0, y0) and (x1, y1), then the slope is (y1 - y0)
     * / (x1 - x0). For completeness, the slope is defined to be +0.0 if the
     * line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical; and
     * Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that
     *            the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        if (this.y == that.y)
            return 0.0;
        return Double.valueOf(that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that
     *            the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point
     *         (x0 = x1 and y0 = y1); a negative integer if this point is less
     *         than the argument point; and a positive integer if this point is
     *         greater than the argument point
     */
    public int compareTo(Point that) {
        if (this.y < that.y)
            return -1;
        if (this.y > that.y)
            return 1;
        if (this.x < that.x)
            return -1;
        if (this.x > that.x)
            return 1;
        return 0;
    }

    /**
     * Compares two points by the slope they make with this point. The slope is
     * defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new SlopeOrder();
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {

        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(1, 2);
        Point p4 = new Point(2, 1);
        Point p5 = new Point(2, 1);
        StdOut.println("Point 1 = " + p1.toString());
        StdOut.println("Point 2 = " + p2.toString());
        StdOut.println("slope from p1 to p2 = " + p1.slopeTo(p2));
        StdOut.println("Point 3 = " + p3.toString());
        StdOut.println("Point 4 = " + p4.toString());
        StdOut.println("slope from p2 to p3 = " + p2.slopeTo(p3));
        StdOut.println("slope from p3 to p2 = " + p3.slopeTo(p2));
        StdOut.println("slope from p2 to p4 = " + p2.slopeTo(p4));
        StdOut.println("slope from p4 to p2 = " + p4.slopeTo(p2));
        if (p1.compareTo(p3) == -1) {
            StdOut.println("Pass: p1 is less than p3");
        } else
            StdOut.println("Fail: p1 should be less than p3");
        if (p2.compareTo(p1) == 1) {
            StdOut.println("Pass: p2 is greater than p1");
        } else
            StdOut.println("Fail: p2 should be greater than p1");
        if (p4.compareTo(p5) == 0) {
            StdOut.println("Pass: p4 is equal to p5");
        } else
            StdOut.println("Fail: p4 should be equal to p5");
        if (p2.compareTo(p3) == 1) {
            StdOut.println("Pass: p2 is greater than p3");
        } else
            StdOut.println("Fail: p2 should be greater than p3");
        if (p3.compareTo(p2) == -1) {
            StdOut.println("Pass: p3 is less than p2");
        } else
            StdOut.println("Fail: p3 should be less than p2");
        if (p3.slopeTo(p3) == Double.NEGATIVE_INFINITY) {
            StdOut.println("Pass: p3 has slope to self of " + p3.slopeTo(p3));
        } else
            StdOut.println("Fail: p3 has slope to self = " + p3.compareTo(p3));
        if (p1.slopeTo(p3) == Double.POSITIVE_INFINITY) {
            StdOut.println("Pass: p1 has slope to p3 of " + p1.slopeTo(p3));
        } else
            StdOut.println("Fail: p1 has slope to p3 = " + p1.compareTo(p4));
        Point[] pointArray = new Point[5];
        pointArray[0] = new Point(1, 5);
        pointArray[1] = new Point(3, 7);
        pointArray[2] = new Point(2, 2);
        pointArray[3] = new Point(6, 6);
        pointArray[4] = new Point(1, 1);
        Arrays.sort(pointArray);
        for (int i = 0; i < 5; i++) {
            StdOut.println(pointArray[i].toString());
        }
    }
}
