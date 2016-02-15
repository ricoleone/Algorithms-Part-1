/* ****************************************************************************
 * Author:       Richard Leone
 * Written:      9/13/13
 * Last Updated  2/13/2016
 *
 * Compilation:  javac Subset
 * Execution:    java Subset N < file.txt
 *
 *
 * Name:         Deque Class
 * Description:  Allows user to add to the start or the end of the collection.
 * Dependencies: StdIn.class of the algs4.jar library
 *****************************************************************************/
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Subset {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        // StdOut.println("read integer = " + N);
        RandomizedQueue<String> rDQ = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            // StdOut.println("String = " + item);
            rDQ.enqueue(item);
        }
        for (int i = 0; i < N; i++) {
            StdOut.println(rDQ.dequeue());
        }
    }
}
