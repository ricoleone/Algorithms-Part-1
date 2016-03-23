import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.princeton.cs.algs4.Queue;


public class BoardTester {
    private Board twoBy2SolvedBoard = null;
    private Board twoBy2RandomBoard = null;
    private Board threeBy3SolvedBoard = null;
    private Board threeBy3RandomBoard = null;
    private Board hamming1 = null;
    private Board hamming2 = null;
    private Board hamming8 = null;
    private Board hamming8_2 = null;
    private Board hamming8_2_copy = null;
    private Board threeBy3RandomBoard_UP = null;
    private Board threeBy3RandomBoard_DOWN = null;
    private Board threeBy3RandomBoard_LEFT = null;
    private Board threeBy3RandomBoard_RIGHT = null;
    private Board threeBy3RandomBoard_UpperRight = null;
    private Board threeBy3RandomBoard_UpperRight_LEFT = null;
    private Board threeBy3RandomBoard_UpperRight_DOWN = null;
    private Board threeBy3RandomBoard_BottomLeft = null;
    private Board threeBy3RandomBoard_BottomLeft_RIGHT = null;
    private Board threeBy3RandomBoard_BottomLeft_UP = null;
    
    
    @Before
    public void setUp() throws Exception {
        
        //Data to test dimensions, isGoal and Twin methods
        int[][] twoBy2Array = {{1, 2},{3, 0}};
        twoBy2SolvedBoard = new Board(twoBy2Array);
        int[][] twoBy2RandomArray = {{2, 3},{0, 1}};
        twoBy2RandomBoard = new Board(twoBy2RandomArray); 
        int[][] threeBy3Array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        threeBy3SolvedBoard = new Board(threeBy3Array);
        int[][] threeBy3RandomArray = {{2, 4, 7}, {8, 0, 1}, {6, 3, 5}};
        threeBy3RandomBoard = new Board(threeBy3RandomArray);
        
        //Data to test the neighbors method
        int[][] threeBy3RandomArray_UP = {{2, 0, 7}, {8, 4, 1}, {6, 3, 5}};
        threeBy3RandomBoard_UP = new Board(threeBy3RandomArray_UP);
        int[][] threeBy3RandomArray_DOWN = {{2, 4, 7}, {8, 3, 1}, {6, 0, 5}};
        threeBy3RandomBoard_DOWN = new Board(threeBy3RandomArray_DOWN);
        int[][] threeBy3RandomArray_LEFT = {{2, 4, 7}, {0, 8, 1}, {6, 3, 5}};
        threeBy3RandomBoard_LEFT = new Board(threeBy3RandomArray_LEFT);
        int[][] threeBy3RandomArray_RIGHT = {{2, 4, 7}, {8, 1, 0}, {6, 3, 5}};
        threeBy3RandomBoard_RIGHT = new Board(threeBy3RandomArray_RIGHT);
        //Data to test neighbors edge case when "0" is top row right column
        int[][] threeBy3RandomUpperRightArray = {{7, 4, 0}, {8, 2, 1}, {6, 3, 5}};
        threeBy3RandomBoard_UpperRight = new Board(threeBy3RandomUpperRightArray);
        int[][] threeBy3RandomUpperRightArray_LEFT = {{7, 0, 4}, {8, 2, 1}, {6, 3, 5}};
        threeBy3RandomBoard_UpperRight_LEFT = new Board(threeBy3RandomUpperRightArray_LEFT);
        int[][] threeBy3RandomUpperRightArray_DOWN = {{7, 4, 1}, {8, 2, 0}, {6, 3, 5}};
        threeBy3RandomBoard_UpperRight_DOWN = new Board(threeBy3RandomUpperRightArray_DOWN);
        //Data to test neighbors edge case when "0" is bottom row, left column
        int[][] threeBy3RandomBottomLeftArray = {{7, 4, 6}, {8, 2, 1}, {0, 3, 5}};
        threeBy3RandomBoard_BottomLeft = new Board(threeBy3RandomBottomLeftArray);
        int[][] threeBy3RandomBottomLeftArray_RIGHT = {{7, 4, 6}, {8, 2, 1}, {3, 0, 5}};
        threeBy3RandomBoard_BottomLeft_RIGHT = new Board(threeBy3RandomBottomLeftArray_RIGHT);
        int[][] threeBy3RandomBottomLeftArray_UP = {{7, 4, 6}, {0, 2, 1}, {8, 3, 5}};
        threeBy3RandomBoard_BottomLeft_UP = new Board(threeBy3RandomBottomLeftArray_UP);
        
        //Data to test hamming and manhattan methods
        int[][] hamming1Array = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};//hamming of 1, manhatten of 1
        hamming1 = new Board(hamming1Array);        
        int[][] hamming2Array = {{1, 2, 4}, {3, 5, 6}, {7, 8, 0}};//hamming of 1, manhatten of 6
        hamming2 = new Board(hamming2Array);
        int[][] hamming8Array = {{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};//zero at last element, manhatten of 16
        hamming8 = new Board(hamming8Array);
        int[][] hamming8Array2 = {{8, 7, 6}, {5, 4, 3}, {2, 0, 1}}; //zero not at last element, manhatten of 17
        hamming8_2 = new Board(hamming8Array2);
        //Data to test the equals method
        hamming8_2_copy = new Board(hamming8Array2);
    }

    @Test
    public void dimensionTest() {
        assertEquals("Testing dimension for twoBy2SolvedBoard", 2, twoBy2SolvedBoard.dimension());
        assertEquals("Testing dimension for twoBy2SolvedBoard", 3, threeBy3SolvedBoard.dimension());
    }

    @Test
    public void hammingTest() {
        assertEquals("Testing hamming() for hamming1", 1, hamming1.hamming());
        assertEquals("Testing hamming() for hamming2", 2, hamming2.hamming());
        assertEquals("Testing hamming() for hamming8", 8, hamming8.hamming());
        assertEquals("Testing hamming() for hamming8_2", 8, hamming8_2.hamming());
    }
    
    @Test
    public void manhattenTest() {
        assertEquals("Testing manhattan() for hamming1", 1, hamming1.manhattan());
        assertEquals("Testing manhattan() for hamming2", 6, hamming2.manhattan());
        assertEquals("Testing manhattan() for hamming8", 16, hamming8.manhattan());
        assertEquals("Testing manhattan() for hamming8_2", 17, hamming8_2.manhattan());
    }
    
    @Test
    public void isGoalTest() {
        assertEquals("Testing isGoal() for twoBy2SolvedBoard", true, twoBy2SolvedBoard.isGoal());
        assertEquals("Testing isGoal() for twoBy2SolvedBoard", true, threeBy3SolvedBoard.isGoal());
        assertEquals("Testing isGoal() for hamming8", false, hamming8.isGoal());
        assertEquals("Testing isGoal() for hamming8_2", false, hamming8_2.isGoal());
    }
    
    @Test
    public void twinTest() {
        assertEquals("Testing twin() for hamming8_2", false, hamming8_2.equals(hamming8_2.twin()));
    }
    @Test
    public void equalsTest() {
        assertEquals("Testing equals() for hamming8_2", true, hamming8_2.equals(hamming8_2));
        assertEquals("Testing equals() for hamming8_2", true, hamming8_2.equals(hamming8_2_copy));
        assertEquals("Testing equals() for hamming8_2", false, hamming8_2.equals(hamming8));
    }
    @Test
    public void neighborsTest() {
        Queue<Board> queue = (Queue<Board>) threeBy3RandomBoard.neighbors();
        for (Board b: queue) {
            if (b.equals(threeBy3RandomBoard_UP)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_UP));
            }
            else if (b.equals(threeBy3RandomBoard_DOWN)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_DOWN));
            }
            else if (b.equals(threeBy3RandomBoard_LEFT)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_LEFT));
            }
            else if (b.equals(threeBy3RandomBoard_RIGHT)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_RIGHT));
            }
            else {
                fail("Neibor did not match b = \n" + b );
            }
        }
        queue = (Queue<Board>) threeBy3RandomBoard_UpperRight.neighbors();
        for (Board b: queue) {
            if (b.equals(threeBy3RandomBoard_UpperRight_LEFT)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_UpperRight_LEFT));
            }
            else if (b.equals(threeBy3RandomBoard_UpperRight_DOWN)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_UpperRight_DOWN));
            }
            else {
                fail("Neibor did not match b = \n" + b );
            }
        }
        queue = (Queue<Board>) threeBy3RandomBoard_BottomLeft.neighbors();
        for (Board b: queue) {
            if (b.equals(threeBy3RandomBoard_BottomLeft_RIGHT)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_BottomLeft_RIGHT));
            }
            else if (b.equals(threeBy3RandomBoard_BottomLeft_UP)) {
                assertEquals("Testing neighbors UP", true, b.equals(threeBy3RandomBoard_BottomLeft_UP));
            }
            else {
                fail("Neibor did not match b = \n" + b );
            }
        }
    }
}
