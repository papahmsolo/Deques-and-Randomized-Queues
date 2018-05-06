import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private int biggestInsertedIndex;
    private Item[] items;

    public RandomizedQueue() {
        items = (Item[]) new Object[4];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (biggestInsertedIndex >= items.length) {
            resize(items.length * 2);
        }
        items[biggestInsertedIndex] = item;
        biggestInsertedIndex++;
        size++;
    }

    public Item dequeue() {
        checkEmpty();
        Item item = null;
        //???
        int index = 0;
        while (item == null) {
            index = StdRandom.uniform(biggestInsertedIndex);
            item = items[index];
        }
        items[index] = null;
        size--;
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public Item sample() {
        checkEmpty();
        Item item = null;
        while (item == null) {
            item = items[StdRandom.uniform(biggestInsertedIndex)];
        }
        return item;
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        int insertIndex = 0;
        for (Item item : items) {
            if (item != null) {
                newArray[insertIndex] = item;
                insertIndex++;
            }
        }
        biggestInsertedIndex = size;
        items = newArray;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            return isEmpty();
        }

        @Override
        public Item next() {
            return dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }
    }


}