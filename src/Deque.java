import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;

        private Node(Item item, Node<Item> next, Node<Item> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    //TODO: Should I init this?
    public Deque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) throws IllegalArgumentException {
        validateItem(item);

        Node<Item> oldFirst = first;
        first = new Node<>(item, oldFirst, null);
        if (isEmpty()) {
            last = first;
        }
        size++;
    }

    public void addLast(Item item) throws IllegalArgumentException {
        validateItem(item);

        Node<Item> oldLast = last;
        last = new Node<>(item, null, oldLast);
        if (isEmpty()) {
            first = last;
        }
        size++;
    }

    public Item removeFirst() {
        validateEmpty();
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        validateEmpty();
        Item item = last.item;
        last = last.previous;
        if (isEmpty()) {
            first = null;
        }
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void validateItem(Item item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void validateEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque =  new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst("first");
        deque.addLast("second and last");
        System.out.println(deque.size());

        for (String s : deque){
            System.out.println(s);
        }
//
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeLast());

    }
}