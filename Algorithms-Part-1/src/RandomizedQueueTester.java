/* ****************************************************************************
 * Author:       Richard Leone
 * Written:      2/12/2016
 * Last Updated  2/12/2016
 *
 * Compilation:  javac RandomizedQueueTester
 * Execution:    java RandomizedQueueTester
 *
 *
 * Name:         RandomizedQueueTester Class
 * Description:  JUnit test for each method in the RandomizedQueue class
 * Dependencies: StdIn.class of the algs4.jar library
 *****************************************************************************/ 
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


public class RandomizedQueueTester {
    RandomizedQueue<String> emptyQ;
    RandomizedQueue<String> smallQ;
    RandomizedQueue<String> oneStringQ;
    RandomizedQueue<String> biggerQ;
    
	@Before
	public void setUp() throws Exception {
		emptyQ = new RandomizedQueue<String>();
		smallQ = new RandomizedQueue<String>();
		oneStringQ = new RandomizedQueue<String>();
		biggerQ = new RandomizedQueue<String>();
		
		
		smallQ.enqueue("smallQ: firstString");
		smallQ.enqueue("smallQ: secondString");
		smallQ.enqueue("smallQ: thirdString");
		
		oneStringQ.enqueue("oneStringQ added last");
		
		biggerQ.enqueue("biggerQ: firstString");
		biggerQ.enqueue("biggerQ: secondString");
		biggerQ.enqueue("biggerQ: thirdString");
		biggerQ.enqueue("biggerQ: fourthString");
		biggerQ.enqueue("biggerQ: fithString");
		biggerQ.enqueue("biggerQ: sixthString");
		biggerQ.enqueue("biggerQ: seventhString");
		biggerQ.enqueue("biggerQ: eigthString");
	}

	@Test
	public void testSize() {
		assertEquals("Testing size for emptyQ RandomizedQueue", 0, emptyQ.size());
		assertEquals("Testing size for smallQ RandomizedQueue", 3, smallQ.size());
		assertEquals("Testing size for a oneStringQ RandomizedQueue", 1, oneStringQ.size());
		assertEquals("Testing size for a one biggerQ RandomizedQueue", 8, biggerQ.size());
	}
	@Test
	public void testIsEmpty() {
		
		assertEquals("Testing isEmpty for emptyQ RandomizedQueue", true, emptyQ.isEmpty());
		assertEquals("Testing isEmpty for smallQ RandomizedQueue", false, smallQ.isEmpty());
		assertEquals("Testing isEmpty for oneStringQ RandomizedQueue", false, oneStringQ.isEmpty());
		assertEquals("Testing isEmpty for biggerQ RandomizedQueue", false, biggerQ.isEmpty());
	}
	@Test
	public void testEnqueue() {
		
		//need case for adding null item
		try {
			String nullString = null;
			emptyQ.enqueue(nullString);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			StdOut.println("Correctly handled Null Pointer exception");
		}
		
		String testString ="New Sting added first on the deque";
		emptyQ.enqueue(testString);
		smallQ.enqueue(testString);
		oneStringQ.enqueue(testString);
		biggerQ.enqueue(testString);
		
		assertEquals("Testing size for emptyQ RandomizedQueue", 0 + 1, emptyQ.size());
		assertEquals("Testing size for smallQ RandomizedQueue", 3 + 1, smallQ.size());
		assertEquals("Testing size for a oneStringQ RandomizedQueue", 1 + 1, oneStringQ.size());
		assertEquals("Testing size for a one biggerQ RandomizedQueue", 8 + 1, biggerQ.size());
		
	}
	@Test
	public void testDequeue() {
		
		//need case for deleting and empty 	queue
		try {
			emptyQ.dequeue();
			fail("Check out of bounds");
		}
		catch (NoSuchElementException e) {
			StdOut.println("Correctly handled dequeuing from an emppty queue");
		}
		//Test that a string is returned when an item is dequeued
		assertEquals("Testing dequeue for smallQ RandomizedQueue", true, String.class.isInstance(smallQ.dequeue()));
		assertEquals("Testing dequeue for a oneStringQ RandomizedQueue", true, String.class.isInstance(oneStringQ.dequeue()));
		assertEquals("Testing dequeue for a one biggerQ RandomizedQueue", true, String.class.isInstance(biggerQ.dequeue()));
		
		//Test that the size is correct after item is dequeue
		assertEquals("Testing size for smallQ RandomizedQueue", 3 - 1, smallQ.size());
		assertEquals("Testing size for a oneStringQ RandomizedQueue", 1 - 1, oneStringQ.size());
		assertEquals("Testing size for a one biggerQ RandomizedQueue", 8 - 1, biggerQ.size());
	}
	@Test
	public void testResize() {
		//Decrease from 8 elements to 2 elements to reduce queue size to 4. Add 3 more elements to force a resize to 8.
		for (int i = 1; i <= 6; i++) {
		    assertEquals("Testing dequeue for a one biggerQ RandomizedQueue", true, String.class.isInstance(biggerQ.dequeue()));
		    assertEquals("Testing size for a one biggerQ RandomizedQueue", 8 - i, biggerQ.size());
		}
		for (int i = 1; i <=3; i++) {
			biggerQ.enqueue(new String("biggerQ: newString" + i));
			assertEquals("Testing size for a one biggerQ RandomizedQueue", 2 + i, biggerQ.size());
		}
	} 
		
	@Test
	public void testIterator() {

		// Test that iterators are independent and the next() method returning items in different random order
		String run1 = "";
		String run2 ="";
		for (String s : biggerQ) {
			 run1 += s;
			
		}
		for (String s : biggerQ) {
            run2 += s;
           
       }
		assertEquals("Testing Iterator for biggerQ deque", false, run1.equals(run2));
		// Test that calling next when there are no more items to return throws exception
		Iterator<String> smallQIter = smallQ.iterator();
		while (smallQIter.hasNext()) {
		    smallQIter.next();
		}
		try {
			smallQIter.next();
			fail("iterator.next() has no more items to return");
		} 
		catch (NoSuchElementException e) {
			StdOut.println("Correctly handled calling Iterator next() on exhausted deque");
		}
		// Test remove throws exception
		try {
			smallQIter = smallQ.iterator();
			smallQIter.remove();
			fail("iterator.remove is not supported");
		} 
		catch (UnsupportedOperationException e) {
			StdOut.println("Correctly handled calling Iterator remove()");
		}
		// Test operations on an empty deque.
		Iterator<String> emptyQIter = emptyQ.iterator();
		assertEquals("Testing size for emptyQ deque", false,
				emptyQIter.hasNext());
		try {
			emptyQIter.next();
			fail("Check next() on empty deque");
		} 
		catch (NoSuchElementException e) {
			StdOut.println("Correctly handled calling next() on empty deque");
		}
	}
}