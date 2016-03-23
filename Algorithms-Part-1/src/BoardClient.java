
public class BoardClient {
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
                StdOut.println("blocks[i][j]= " + blocks[i][j]);
            }
        Board initial = new Board(blocks);
        StdOut.println(initial.toString());
        StdOut.println("the hamming function = " + initial.hamming());
        StdOut.println("the manhattan function = " + initial.manhattan());
        StdOut.println("isGoal() = " + initial.isGoal());
        
        Board initialCopy = initial;
        StdOut.println("is equals() to copy? " + initial.equals(initialCopy));
        int [][] a = new int[3][3];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                a[i][j] = i + j;
            }
        initialCopy = new Board(a);
        StdOut.println("is equals() to a? " + initial.equals(initialCopy));
        initialCopy = initial.twin();
        StdOut.println(initialCopy.toString());
        

    }

}
