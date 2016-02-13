import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


public class DequeTester {
    Deque<String> emptyQ;
    Deque<String> smallQ;
    Deque<String> oneStringQ;
    Deque<String> addLastQ;
    
	@Before
	public void setUp() throws Exception {
		emptyQ = new Deque<String>();
		smallQ = new Deque<String>();
		oneStringQ = new Deque<String>();
		addLastQ = new Deque<String>();
		
		
		smallQ.addFirst(new String("smallQ: firstString"));
		smallQ.addFirst(new String("smallQ: secondString"));
		smallQ.addFirst(new String("smallQ: thirdString"));
		
		oneStringQ.addLast(new String("oneStringQ added last"));
		
		addLastQ.addLast(new String("addLastQ: firstString"));
		addLastQ.addLast(new String("addLastQ: secondString"));
		addLastQ.addLast(new String("addLastQ: thirdString"));
		addLastQ.addLast(new String("addLastQ: fourthString"));
	}

	@Test
	public void testSize() {
		assertEquals("Testing size for emptyQ deque", 0, emptyQ.size());
		assertEquals("Testing size for smallQ deque", 3, smallQ.size());
		assertEquals("Testing size for a oneStringQ deque", 1, oneStringQ.size());
		assertEquals("Testing size for a one addLastQ deque", 4, addLastQ.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals("Testing isEmpty for emptyQ deque", true, emptyQ.isEmpty());
		assertEquals("Testing isEmpty for smallQ deque", false, smallQ.isEmpty());
		assertEquals("Testing isEmpty for oneStringQ deque", false, oneStringQ.isEmpty());
		assertEquals("Testing isEmpty for addLastQ deque", false, addLastQ.isEmpty());
	}
	@Test
	public void testAddFirst() {
		
		//need case for adding null item
		try {
			String nullString = null;
			emptyQ.addFirst(nullString);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			StdOut.println("Correctly handled Null Pointer exception");
		}
		
		String testString ="New Sting added first on the deque";
		emptyQ.addFirst(testString);
		smallQ.addFirst(testString);
		oneStringQ.addFirst(testString);
		addLastQ.addFirst(testString);
		
		assertEquals("Testing size for emptyQ deque", 0 + 1, emptyQ.size());
		assertEquals("Testing size for smallQ deque", 3 + 1, smallQ.size());
		assertEquals("Testing size for a oneStringQ deque", 1 + 1, oneStringQ.size());
		assertEquals("Testing size for a one addLastQ deque", 4 + 1, addLastQ.size());
		
		assertEquals("Testing addFirst for emptyQ deque", testString, emptyQ.removeFirst());
		assertEquals("Testing addFirst for smallQ deque", testString, smallQ.removeFirst());
		assertEquals("Testing addFirst for oneStringQ deque", testString, oneStringQ.removeFirst());
		assertEquals("Testing addFirst for addLastQ deque", testString, addLastQ.removeFirst());
	}
	@Test
	public void testAddLast() {

		// need case for adding null item
		try {
			String nullString = null;
			emptyQ.addFirst(nullString);
			fail("Check out of bounds");
		} catch (NullPointerException e) {
			StdOut.println("Correctly handled Null Pointer exception");
		}

		String testString = "New Sting added last on the deque";
		emptyQ.addLast(testString);
		smallQ.addLast(testString);
		oneStringQ.addLast(testString);
		addLastQ.addLast(testString);

		assertEquals("Testing size for emptyQ deque", 0 + 1, emptyQ.size());
		assertEquals("Testing size for smallQ deque", 3 + 1, smallQ.size());
		assertEquals("Testing size for a oneStringQ deque", 1 + 1, oneStringQ.size());
		assertEquals("Testing size for a one addLastQ deque", 4 + 1, addLastQ.size());

		assertEquals("Testing addFirst for emptyQ deque", testString, emptyQ.removeLast());
		assertEquals("Testing addFirst for smallQ deque", testString, smallQ.removeLast());
		assertEquals("Testing addFirst for oneStringQ deque", testString, oneStringQ.removeLast());
		assertEquals("Testing addFirst for addLastQ deque", testString, addLastQ.removeLast());
	}

	@Test
	public void testRemoveFirst() {
		// need case for adding null item
		try {
			emptyQ.removeFirst();
			fail("Check out of bounds");
		} catch (NoSuchElementException e) {
			StdOut.println("Correctly handled removing first item from empty deque");
		}
		
		String smallQFirstItem = "smallQ: thirdString";
		String oneStringFirstItem = "oneStringQ added last";
		String addLastQFirstItem = "addLastQ: firstString";
		
		assertEquals("Testing addFirst for smallQ deque", true, smallQFirstItem.equals(smallQ.removeFirst()));
		assertEquals("Testing addFirst for oneStringQ deque", true, oneStringFirstItem.equals(oneStringQ.removeFirst()));
		assertEquals("Testing addFirst for addLastQ deque", true, addLastQFirstItem.equals(addLastQ.removeFirst()));
		
		assertEquals("Testing size for emptyQ deque", 0, emptyQ.size());
		assertEquals("Testing size for smallQ deque", 3 - 1, smallQ.size());
		assertEquals("Testing size for a oneStringQ deque", 1 - 1, oneStringQ.size());
		assertEquals("Testing size for a one addLastQ deque", 4 - 1, addLastQ.size());
	}
	@Test
	public void testRemoveLast() {
		// need case for adding null item
		try {
			emptyQ.removeLast();
			fail("Check out of bounds");
		} catch (NoSuchElementException e) {
			StdOut.println("Correctly handled removing last item from empty deque");
		}
		String smallQLastItem = "smallQ: firstString";
		String oneStringLastItem = "oneStringQ added last";
		String addLastQLastItem = "addLastQ: fourthString";
		
		assertEquals("Testing addFirst for smallQ deque", true, smallQLastItem.equals(smallQ.removeLast()));
		assertEquals("Testing addFirst for oneStringQ deque", true, oneStringLastItem.equals(oneStringQ.removeLast()));
		assertEquals("Testing addFirst for addLastQ deque", true, addLastQLastItem.equals(addLastQ.removeLast()));
		
		assertEquals("Testing size for emptyQ deque", 0, emptyQ.size());
		assertEquals("Testing size for smallQ deque", 3 - 1, smallQ.size());
		assertEquals("Testing size for a oneStringQ deque", 1 - 1, oneStringQ.size());
		assertEquals("Testing size for a one addLastQ deque", 4 - 1, addLastQ.size());
	}
	@Test
	public void testIterator() {
		
		//Test that iterators are independent
		Iterator<String> smallQIter = smallQ.iterator();
		for(String s: smallQ) {
			String ss = smallQIter.next();
		    assertEquals("Testing Iterator for smallQ deque", true, s.equals(ss));
		}
		//Test remove throws exception
		try {
			smallQIter = smallQ.iterator();
			smallQIter.remove();
			fail("iterator.remove is not supported");
		}
		catch (UnsupportedOperationException e) {
			StdOut.println("Correctly handled calling Iterator remove");
		}
		//Test operations on an empty deque.
		Iterator<String> emptyQIter = emptyQ.iterator();
		assertEquals("Testing size for emptyQ deque", false, emptyQIter.hasNext());
		try{ 
			emptyQIter.next();
			fail("Check next() on empty deque");
		}
		catch (NoSuchElementException e) {
			StdOut.println("Correctly handled calling next on empty deque");
		}
	}

}
