/*************************************************************************
 *  Author:       Richard Leone
 *  Written:      9/15/13
 *  Last Updated  2/12/2016 
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *  
 *  Random Queue implementation with a resizing array.
 *
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] itemArray; // array of items
    private int N; // number of elements on stack

    // create an empty stack
    public RandomizedQueue() {
        N = 0;
        itemArray = (Item[]) new Object[2];
    }

    /**
     * 
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * add an Item to the queue
     * 
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == itemArray.length) {
            resize(2 * itemArray.length);
        }
        itemArray[N++] = item;
    }

    /**
     * 
     * @return Item
     */
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(0, N);
        Item dequeuedItem = itemArray[randomIndex];
        itemArray[randomIndex] = itemArray[N - 1];
        //for (int i = randomIndex; i < N - 1; i++) {
            //itemArray[i] = itemArray[i + 1];
        //}
        itemArray[N - 1] = null;
        N--;
        if (N > 0 && N == itemArray.length / 4) {
            // StdOut.printf("dequeue calling resize when N = %d, itemArray.length() = %d\n",
            // N, itemArray.length);
            resize(itemArray.length / 2);
            // StdOut.printf("dequeue called resize when N = %d, itemArray.length() = %d\n",
            // N, itemArray.length);
        }
        return dequeuedItem;
    }

    /**
     * return but do not delete a random item
     * 
     * @return
     */
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        return itemArray[StdRandom.uniform(0, N)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // an iterator, doesn't implement remove()
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        private boolean [] indexArray = new boolean[N];

        public RandomizedQueueIterator() {
            current = N - 1;

        }

        public boolean hasNext() {
            return current >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current--;
            int index = StdRandom.uniform(0, N);
            while (indexArray[index]) {
                index = StdRandom.uniform(0, N);
            }
            indexArray[index] = true;
            return itemArray[index];
        }
    }

    /**
     * resize the underlying array holding the order of the items
     * 
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

    private void printQueue() {
        StdOut.println("N = " + N);
        StdOut.println("queue lenth = " + itemArray.length);
        for (Item s : itemArray) {
            StdOut.println(s);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        RandomizedQueue<String> testQ = new RandomizedQueue<String>();
        if (!testQ.isEmpty()) {
            StdOut.println("Error: testQ should be empty but is not");
        } else
            StdOut.println("Pass: isEmpty() after constructor");
        if (!(testQ.size() == 0)) {
            StdOut.println("Error: testQ should be size == 0 but is not");
        }
        testQ.enqueue("StringOne");
        testQ.enqueue("StringTwo");
        testQ.enqueue("StringThree");
        testQ.enqueue("StringFour");
        testQ.enqueue("StringFive");
        if (testQ.isEmpty()) {
            StdOut.println("Error: testQ should have four Sting Items");
        }
        if (testQ.size() != 5) {
            StdOut.println("Error: testQ should have size == 5");
        } else
            StdOut.println("Pass: testQ has size == 5");

        if (testQ.isEmpty()) {
            StdOut.println("Error: testQ should have three Sting Items");
        }
        StdOut.println("Dequeued the following Item " + testQ.dequeue());
        if (testQ.size() != 4) {
            StdOut.println("Error: testQ should have size == 4");
        } else
            StdOut.println("Pass: testQ has size == 4");
        StdOut.println("Sampled the following Item " + testQ.sample());

        Iterator<String> tQi = testQ.iterator();
        while (tQi.hasNext()) {
            StdOut.println(tQi.next());
        }
        StdOut.println("Strings should be in random order");

        Iterator<String> tQii = testQ.iterator();
        while (tQii.hasNext()) {
            StdOut.println(tQii.next());
        }
        StdOut.println("Strings should be in random order but different "
                + "from list above");
        while (testQ.size() > 0) {
            StdOut.println("Dequeued the following Item " + testQ.dequeue());
        }
        if (!testQ.isEmpty()) {
            StdOut.println("Error: testQ should be empty but is not");
        } else
            StdOut.println("Pass: isEmpty() after all item removed");
        if (!(testQ.size() == 0)) {
            StdOut.println("Error: testQ should be size == 0 but is not");
        } else
            StdOut.println("Pass: testQ size == 0 after all items dequeued");
        testQ.enqueue("StringOne");
        testQ.enqueue("StringTwo");
        testQ.enqueue("StringThree");
        testQ.enqueue("StringFour");
        testQ.enqueue("StringFive");
        while (testQ.size() > 0) {
            StdOut.println("194: Dequeued the following Item "
                    + testQ.dequeue());
        }
        if (!testQ.isEmpty()) {
            StdOut.println("Error: testQ should be empty but is not");
        } else
            StdOut.println("Pass: isEmpty() after all item removed");
        if (!(testQ.size() == 0)) {
            StdOut.println("Error: testQ should be size == 0 but is not");
        }
    }

}
