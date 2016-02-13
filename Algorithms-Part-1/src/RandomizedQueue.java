/*************************************************************************
 *  Author:       Richard Leone
 *  Written:      9/15/13
 *  Last Updated  9/18/13 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *  
 *  Random Queue implementation with a resizing array.
 *
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] itemArray;    // array of items
    private int    N;            // number of elements on stack

    // create an empty stack
    public RandomizedQueue() {
    	N = 0;
        itemArray = (Item[]) new Object[2];
    }
    /**
    * 
    * @return
    */
    public boolean isEmpty(){ 
    	return N == 0;
    }
    /**
     * 
     * @return
     */
    public int size(){ 
    	return N;
    }
    /**
     * add an Item to the queue
     * @param item
     */
    public void enqueue ( Item item ) {
    	if( item == null) {
    		throw new NullPointerException();
    	}
    	if( N == itemArray.length ) {
    		resize( 2* itemArray.length);
    	}
    	itemArray[N++] = item;
    }
    /**
     * 
     * @return Item
     */
    public Item dequeue() {
    	if( N == 0 ) {
    		throw new NoSuchElementException();
    	}
    	int randomIndex    = StdRandom.uniform(0,N);
    	Item dequeuedItem  = itemArray[randomIndex];
    	Item [] temp = (Item[]) new Object[N];
    	for( int i = 0; i < randomIndex; i++) {
    		temp[i] = itemArray[i];
    	}
    	for( int i = randomIndex; i < N - 1; i++) {
    		temp[i] = itemArray[i + 1]; 
    	}
    	itemArray = temp;
    	N--;
    	return dequeuedItem;
    }
    /**
     * return but do not delete a random item
     * @return
     */
    public Item sample() {
    	if( N == 0 ) {
    		throw new NoSuchElementException();
    	}
    	int randomIndex    = StdRandom.uniform(0,N);
    	return itemArray[randomIndex];
    }

    public Iterator<Item> iterator(){
    	return new RandomizedQueueIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        private Item [] iteratorArray;
        
        public RandomizedQueueIterator() {
            current = 0;
            iteratorArray = (Item[]) new Object[N];
            for( int i = 0; i < N; i++ ) {
            	iteratorArray[i] = itemArray[i]; 
            }
            StdRandom.shuffle(iteratorArray);
        }

        public boolean hasNext() {
            return current < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
            	throw new NoSuchElementException();
            }
            return iteratorArray[current++];
        }
    }
    /**
     *  resize the underlying array holding the order of the items
     * @param capacity
     */
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = itemArray[i];
        }
        itemArray = temp;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomizedQueue<String> testQ = new RandomizedQueue<String>();
		if( !testQ.isEmpty() ) {
			StdOut.println("Error: testQ should be empty but is not");
		}
		else StdOut.println("Pass: isEmpty() after constructor");
		if( !(testQ.size() == 0)){ 
			StdOut.println("Error: testQ should be size == 0 but is not");
		}
		testQ.enqueue(new String("StringOne"));
		testQ.enqueue(new String("StringTwo"));
		testQ.enqueue(new String("StringThree"));
		testQ.enqueue(new String("StringFour"));
		testQ.enqueue(new String("StringFive"));
		if( testQ.isEmpty() ) {
			StdOut.println("Error: testQ should have four Sting Items");
		}
		if(testQ.size() != 5) {
			StdOut.println("Error: testQ should have size == 5");
		}
		else StdOut.println("Pass: testQ has size == 5");
		
		if( testQ.isEmpty() ) {
			StdOut.println("Error: testQ should have three Sting Items");
		}
		StdOut.println("Dequeued the following Item " + testQ.dequeue());
		if(testQ.size() != 4) {
			StdOut.println("Error: testQ should have size == 4");
		}
		else StdOut.println("Pass: testQ has size == 4");
		StdOut.println("Sampled the following Item " + testQ.sample());
		
		Iterator<String> tQi = testQ.iterator();	
		while(tQi.hasNext() ) {
			StdOut.println(tQi.next());
		}
		StdOut.println("Strings should be in random order");
		
		Iterator<String> tQii = testQ.iterator();	
		while(tQii.hasNext() ) {
			StdOut.println(tQii.next());
		}
		StdOut.println("Strings should be in random order but different " +
				"from list above");
		while( testQ.size() > 0) {
			StdOut.println("Dequeued the following Item " + testQ.dequeue());
		}
		if( !testQ.isEmpty() ) {
			StdOut.println("Error: testQ should be empty but is not");
		}
		else StdOut.println("Pass: isEmpty() after all item removed");
		if( !(testQ.size() == 0)){ 
			StdOut.println("Error: testQ should be size == 0 but is not");
		}
		else StdOut.println("Pass: testQ size == 0 after all items dequeued");
		testQ.enqueue(new String("StringOne"));
		testQ.enqueue(new String("StringTwo"));
		testQ.enqueue(new String("StringThree"));
		testQ.enqueue(new String("StringFour"));
		testQ.enqueue(new String("StringFive"));
		while( testQ.size() > 0) {
			StdOut.println("194: Dequeued the following Item " + testQ.dequeue());
		}
		if( !testQ.isEmpty() ) {
			StdOut.println("Error: testQ should be empty but is not");
		}
		else StdOut.println("Pass: isEmpty() after all item removed");
		if( !(testQ.size() == 0)){ 
			StdOut.println("Error: testQ should be size == 0 but is not");
		}
	}

}
