/* ****************************************************************************
 * Author:       Richard Leone
 * Written:      9/13/13
 * Last Updated  9/18/13
 *
 * Compilation:  javac Subset
 * Execution:    java Subset N < file.txt
 *
 *
 * Name:         Deque Class
 * Description:  Allows user to add to the start or the end of the collection.
 * Dependencies: StdIn.class of the algs4.jar library
 *****************************************************************************/ 
import java.util.Iterator;


public class Subset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		
		//StdOut.println("read integer = " + N);
		RandomizedQueue<String> rDQ = new RandomizedQueue<String>();
		String item;
		while (!StdIn.isEmpty()) {
            item = StdIn.readString();
           // StdOut.println("String = " + item);
            rDQ.enqueue(item);
        }
		int size = rDQ.size();
        Iterator<String> rDQi = rDQ.iterator();
		for(int i = 0; i < N && i < size; i++ ) {
			StdOut.println(rDQi.next());
		}	    
  }
}

