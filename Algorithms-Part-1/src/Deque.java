/* ****************************************************************************
 * Author:       Richard Leone
 * Written:      9/13/13
 * Last Updated  9/18/13
 *
 * Compilation:  javac Deque
 * Execution:    java Deque
 *
 *
 * Name:         Deque Class
 * Description:  Allows user to add to the start or the end of the collection.
 * Dependencies: StdIn.class of the algs4.jar library
 *****************************************************************************/ 
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   private int  N;     // the size of the deque
   private Node first; // first node of the deque
   private Node last;  // last node of the deque

   private class Node{
	   private Item item; // the item to be contained in the deque
	   private Node next; // the next node in the linked list
	   private Node previous; // the previous node on the linked list
   }
	/**
	 * construct an empty deque
	 */
	public Deque() {
		N     = 0;
		first = null;
		last  = null; 
	}
	/**
	 * is the deque empty?
	 * @return
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	/**
	 * return the number of items on the deque
	 * @return
	 */
	public int size() {
		return N;
	}
	/**
	 * insert the item at the front
	 * @param item
	 */
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("Attempted to add a null pointer to the beginning of deque");
		}
		Node newFirst = new Node();
		newFirst.item = item;
		newFirst.previous = null;
		if (N == 0){
			newFirst.next = null;
			last = newFirst;
		}
		else {
			newFirst.next = first;
			first.previous = newFirst;
		}
		first = newFirst;
		N++; 
	}
	/**
	 * insert the item at the end
	 * @param item
	 */
	public void addLast(Item item) {
		if( item == null) {
			throw new NullPointerException("Attempted to add a null pointer to the end of deque");
		}
		 Node newLast = new Node();
		 newLast.item = item;
		 newLast.next = null;
		 if (N == 0) {
			 newLast.previous = null;
			 first = newLast;
		 }
		 else {
			 newLast.previous = last;
			 last.next        = newLast;
		 }
		 last = newLast;
		 N++;
	}
	  
	/**
	 * delete and return the item at the front
	 * @return
	 */
	public Item removeFirst() {
		if (first == null) {
			throw new NoSuchElementException("Attempted to remove an item from an empty deque");
		}
		Node oldFirst = first;
		first = oldFirst.next;
		if (first != null)
		  first.previous  = null;
		N--;
		return oldFirst.item;
	}
	/**
	 * delete and return the item at the end
	 * @return
	 */
	public Item removeLast() {
		if (last == null) {
			throw new NoSuchElementException("Attempted to remove an item from an empty deque");
		}
		Node oldLast = last;
		last = oldLast.previous;
		if( last == null)
				first = last;
		else last.next    = null;
		N--;
		return oldLast.item;
	}
	/**
	 * return an iterator over items in order from front to end
	 */
	public Iterator<Item> iterator(){
		return new DequeIterator();
	}
	/**
	 * 
	 */
	private class DequeIterator implements Iterator <Item>{
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
			
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext() ) throw new NoSuchElementException("There are no more items on the deque");
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  Deque<String> testD = new Deque<String>();
		  if (!testD.isEmpty() ) {
				StdOut.println("Error: testD should be empty but is not");
			}
			else StdOut.println("Pass: isEmpty() after constructor");
			if (!(testD.size() == 0)){ 
				StdOut.println("Error: testD should be size == 0 but is not");
			}
			else StdOut.println("Pass: size() == 0 after constructor");
			testD.addFirst(new String("firstString"));
			testD.addFirst(new String("secondString"));
			testD.addFirst(new String("thirdString"));
			testD.addLast(new String("fourthString"));
			testD.addLast(new String("fithString"));
			testD.addLast(new String("sixthString"));
			testD.addLast(new String("sevethString"));
			StdOut.println("(" + testD.size() + " items in the deque)");
			Iterator<String> tDI =  testD.iterator();
			
			StdOut.println("Testing iterator..........");
	        for (String s: testD) {
	        	StdOut.println(s);
	        }
	        StdOut.println("Order should be thirdString, secondString, " +
	        		"firstString, fourthString, fithString, sixthString" +
	        		"seventhString");
	        int size = testD.size();
	        for (int k = 0; k < size; k++) {
	        	//StdOut.println("size = " + testD.size());
	        	StdOut.println("testD.removeFirst = " + testD.removeFirst() + " testD.size() = " + testD.size());
	        }
	          
	        StdOut.println("Removed all size = " + testD.size());
	        testD.addFirst(new String("newFirstString"));
	        if (testD.size() != 1)
	           StdOut.println("Error: size did not increase to = 1");
	        else StdOut.println("Pass: added newFirstString: size = 1 ");
	        size = testD.size();
	        for (int k = 0; k < size; k++) {
	        	//StdOut.println("size = " + testD.size());
	        	StdOut.println("testD.removeFirst() = " + testD.removeFirst() + " testD.size() = " + testD.size());
	        }
	        testD.addLast(new String("newLastString"));
	        if (testD.size() != 1)
	           StdOut.println("Error: size did not increase to = 1");
	        else StdOut.println("Pass: Size increased size = 1 ");
	        size = testD.size();
	        for (int k = 0; k < size; k++) {
	        	StdOut.println("size = " + testD.size());
	        	StdOut.println("Removed Last " + testD.removeLast());
	        	StdOut.println("reduced size to " + testD.size());
	        }
	        testD.addFirst(new String("firstString"));
			testD.addFirst(new String("secondString"));
			testD.addFirst(new String("thirdString"));
			 if (testD.size() != 3)
		           StdOut.println("Error: size did not increase to = 3");
		        else StdOut.println("Pass: Size increased size = 3 ");
			 size = testD.size();
			 for (int k = 0; k < size; k++) {
		        	StdOut.println("size = " + testD.size());
		        	StdOut.println("Removed first " + testD.removeFirst());
		        	StdOut.println("reduced size to " + testD.size());
		        }
			 testD.addFirst(new String("firstString"));
				testD.addFirst(new String("secondString"));
				testD.addFirst(new String("thirdString"));
				testD.addLast(new String("fourthString"));
				testD.addLast(new String("fithString"));
				testD.addLast(new String("sixthString"));
				testD.addLast(new String("sevethString"));
				StdOut.println("(" + testD.size() + " items in the deque)");
	
				
		        for(String s: testD) {
		        	StdOut.println(s);
		        }
	}
}
