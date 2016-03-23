import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PointTest {

    @Before
    public void setUp() throws Exception {
        Point[] _45deg = {new Point(0,0), new Point(1000,1000), new Point(2000,2000), 
                new Point(3000,3000), new Point(4000,4000)};
        Point[] _horLine = {new Point(0, 1000), new Point(1000, 1000), new Point(2000, 1000), new Point(3000, 1000) };
        Point[] _vertLine = {new Point(1000, 0), new Point(1000, 1000), new Point(1000, 2000), new Point(1000, 3000)};
        Point[] _45Neg = {new Point(-3000, 3000), new Point(-1000, 1000), new Point(0,0), 
                new Point(1000, -1000), new Point(2000, -2000), new Point(3000, -3000)};
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
