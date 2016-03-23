/**
 * @author Richard Leone
 *
 */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;


public class Board {
    private  int manPriority;
    private  int hamPriority;
    private final int N; //dimension of square
    private final int[][] blocks; //blocks in square
    /**
     * 
     * @param blocks - an NxN array of integers 0 - N-1 that represents the starting point for the game
     */
    public Board(int[][] blocks) {
        N = blocks[0].length; //Assume an NxN 2D array
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                this.blocks[i][j] = blocks[i][j];
        }
        //Calculate hamming function
        setHamming();
        //Calculate manhattan function
        setManhattan();
    }
    /**
     *     
     * @return - N the dimensions of an NxN array
     */
    public int dimension() {
        return N;
        
    }
    /**
     * 
     * sets the hamming distance of the board, which is the number of 
     * values out of place - to be called in constructor
     */
    private void setHamming() {
        this.hamPriority = 0;
        int value = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != value && this.blocks[i][j] != 0) {
                    this.hamPriority++;
                }
                value++;
            }
        }
    }
    /**
     * 
     * sets the manhattan distance of the board, which it the total sum of the 
     * distance of each element from its position on a solved board - to be called 
     * in constructor
     */
    private void setManhattan() {
        this.manPriority = 0;
        int value = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != value && this.blocks[i][j] != 0) {
                    int row    = (this.blocks[i][j] - 1)/N;
                    int column = (this.blocks[i][j] - 1) % N;
                    this.manPriority += Math.abs(row - i) + Math.abs(column - j);
                }
                value++;
            }
        }
    }
    /**
     * 
     * @return
     */
    public int hamming() {
        return this.hamPriority;
    }
    public int manhattan() {
        return this.manPriority;
    }
    /**
     * 
     * @return - true if this instance is a solved board
     */
    public boolean isGoal() {
        int value = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != value) {
                    return (i == N-1 && j == N-1 && this.blocks[N-1][N-1] == 0);
                }
                value++;
            }
        }
        return true;
    }
    /**
     * 
     * @return - a new board that is a twin of this instance
     */
    public Board twin() {
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = blocks[i][j];
            }
        }
        //rows and columns to swap
        int row1 = StdRandom.uniform(0, N);
        int col1 = StdRandom.uniform(0, N);
        int row2 = StdRandom.uniform(0, N);
        int col2 = StdRandom.uniform(0, N);
        while (blocks[row1][col1] == 0) {
            row1 = StdRandom.uniform(0, N);
            col1 = StdRandom.uniform(0, N);
        }
        while ((row1 == row2 && col1 == col2) || blocks[row2][col2] == 0) {
            row2 = StdRandom.uniform(0, N);
            col2 = StdRandom.uniform(0, N);
        }
        a[row1][col1] = blocks[row2][col2];
        a[row2][col2] = blocks[row1][col1];
        return new Board(a);
    }
    /**
     * returns true if the elements of the board and the input board are equal
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (this.N != that.N) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 
     * @return - A Queue instance that contains all the neighbor boards to
     * this instance
     */
    public Iterable<Board> neighbors() {
        Queue<Board> queue = new Queue<Board>();
        int row = 0;
        int col = 0;
        outerLoop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] == 0) {
                    row = i;
                    col = j;
                    break outerLoop;
                }
            }
        }
        //StdOut.println("DEBUG: neighbors()-> row = " + row + " col = " + col);
      
        if (row != 0) {
            queue.enqueue(new Board(move(row, col, 'U')));
        }
        if (row != N - 1) {
            queue.enqueue(new Board(move(row, col, 'D')));
        }
        
        if (col != 0) {
            queue.enqueue(new Board(move(row, col, 'L')));
        }
        if (col != N - 1) {
            queue.enqueue(new Board(move(row, col, 'R')));
        }
        return queue;
    }
    /**
     * writes the array in a block matrix format
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    /**
     * 
     * @param row - the row value for the "0" entry 
     * @param col - the column value for the "0" entry
     * @param dir - the direction (UP, DOWN, LEFT, RIGHT) to move the "0" entry and swap it with that entry
     * @return - the integer array that represents a neighbor board.
     */
    private int[][] move(int row, int col, char dir) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = this.blocks[i][j];
            }
        }
        switch (dir) {
            case 'U':
                tmp[row][col] = tmp[row - 1][col];
                tmp[row -1][col] = 0;
                //StdOut.println("DEBUG: move()-> UP");
                break;
            case 'D':
                tmp[row][col] = tmp[row + 1][col];
                tmp[row + 1][col] = 0;
                //StdOut.println("DEBUG: move()-> DOWN");
                break;
            case 'L':
                tmp[row][col] = tmp[row][col - 1];
                tmp[row][col - 1] = 0;
                //StdOut.println("DEBUG: move()-> LEFT");
                break;
            case 'R':
                tmp[row][col] = tmp[row][col + 1];
                tmp[row][col + 1] = 0;
                //StdOut.println("DEBUG: move()-> RIGHT");
                break;
        }
        
        return tmp;
    }
}
