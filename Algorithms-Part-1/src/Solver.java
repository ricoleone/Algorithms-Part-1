
/**
 * 
 */
/**
 * @author Richard Leone
 *
 */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StopwatchCPU;

public class Solver {
    private SearchNode twinSolution;
    private SearchNode solution;

    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode previous;
        private int moves;
        private int manhattan;
        private int hamming;

        public SearchNode(Board b, SearchNode prev, int mvs) {
            this.board = b;
            this.previous = prev;
            this.moves = mvs;
            this.manhattan = this.board.manhattan();
            this.hamming = this.board.hamming();
        }

        private int priority() {
            return this.manhattan + this.moves;
        }
        /**
         * compareTo uses multiple comparisons to break ties which should
         * reduce the number of operations to speed up the processing 
         */
        @Override
        public int compareTo(SearchNode o) {
            int that_hamming = o.board.hamming();
            int that_manhattan = o.board.manhattan();
            int that_priority = that_manhattan + o.moves;

            if (this.priority() < that_priority) {
                return -1;
            }
            if (this.priority() > that_priority) {
                return 1;
            }
            if (Math.abs(this.manhattan - this.hamming) < Math
                    .abs(that_manhattan - that_hamming)) {
                return -1;
            }
            if (Math.abs(this.manhattan - this.hamming) > Math
                    .abs(that_manhattan - that_hamming)) {
                return 1;
            }
            if (this.moves > o.moves) {
                return -1;
            }
            if (this.moves < o.moves) {
                return 1;
            }
            if (this.manhattan < o.manhattan) {
                return -1;
            }
           
            if (this.manhattan > o.manhattan) {
                return 1;
            }
 
            /*
            if (this.hamming > that_hamming) {
                return -1;
            }
            if (this.hamming < that_hamming) {
                return 1;
            }
        */
            return 0;
        }
        public boolean isNeighbor(SearchNode that) {
            int diff = this.manhattan - that.manhattan;
            return diff == 1|| diff == -1 || diff == 0;
        }
    }

    /**
     * 
     * @param initial
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException(
                    "null pointer passed as initial board");
        }
        /*
         * Set up and initialize data structures
         * Create nodes for initial and twin then insert in MinPQs to process nodes
         * to find the boar that matches the solution board
         */
        SearchNode currNode = new SearchNode(initial, null, 0);
        SearchNode currTwinNode = new SearchNode(initial.twin(), null, 0);
        MinPQ<SearchNode> openSet = new MinPQ<SearchNode>();
        MinPQ<SearchNode> openTwinSet = new MinPQ<SearchNode>();
        openSet.insert(currNode);
        openTwinSet.insert(currTwinNode);
        this.solution = currNode;
        this.twinSolution = currTwinNode;
        
        /*
         * Set up loop to delete node with the minimum priority
         * Add neighbors for initial board and its twin until
         * either reaches the solution board
         */
        while (!currNode.board.isGoal() && !currTwinNode.board.isGoal()) {
            currNode = openSet.delMin();
            for (Board b: currNode.board.neighbors()) {
                if (currNode.previous == null || !b.equals(currNode.previous.board)) {
                    openSet.insert(new SearchNode(b, currNode, currNode.moves + 1));
                }
            }
            this.solution = currNode;
            
            currTwinNode = openTwinSet.delMin();
            for (Board tb : currTwinNode.board.neighbors()) {
                if (currTwinNode.previous == null || !tb.equals(currTwinNode.previous.board)) {
                    openTwinSet.insert(new SearchNode(tb, currTwinNode, currTwinNode.moves + 1));
                }
            }
            this.twinSolution = currTwinNode;
         }
    }

    /**
     * 
     * @return
     */
    public boolean isSolvable() {
        return this.solution.board.isGoal() && !this.twinSolution.board.isGoal();
    }

    /**
     * 
     * @return
     */
    public int moves() {
        if (!this.isSolvable()) {
            return -1;
        }
        return this.solution.moves;
    }

    /**
     * 
     * @return
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> stk = new Stack<Board>();
        SearchNode sNode = this.solution;
        // StdOut.println("DEBUG: solution()-> added " + sNode.board);
        stk.push(sNode.board);
        while (sNode.previous != null) {
            sNode = sNode.previous;
            stk.push(sNode.board);
            // StdOut.println("DEBUG: solution()-> added " + sNode.board);
        }

        return stk;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        
        StopwatchCPU watch = new StopwatchCPU();
        Solver solver = new Solver(initial);
        double CPU_time = watch.elapsedTime();
        StdOut.printf("CPU time = %.6f\n", CPU_time);
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }

}
