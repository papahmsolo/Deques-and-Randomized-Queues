import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[4];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void enqueue(Item item)  {}         // add the item
    public Item dequeue() {
        validateEmpty();
        Item item = null;
        int index;
        while (item == null){
            index = StdRandom.uniform(size);
            item = items[index];
        }
        items[index] = null;
        return item;
    }

    public Item sample() {
        validateEmpty();
        Item item = null;
        while (item == null){
            item = items[StdRandom.uniform(size)];
        }
        return item;
    }

    private void validateEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }
    public Iterator<Item> iterator() {}         // return an independent iterator over items in random order
    public static void main(String[] args) {
        RandomizedQueue queue = new RandomizedQueue();
        queue.size();
        queue.isEmpty();
        queue.sample();
    }
}