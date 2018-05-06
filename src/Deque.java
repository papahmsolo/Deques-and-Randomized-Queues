import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item> {
        final private Item item;
        private Node<Item> next;
        private Node<Item> previous;

        private Node(Item item, Node<Item> next, Node<Item> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkNull(item);
        Node<Item> oldFirst = first;
        first = new Node<>(item, oldFirst, null);
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        size++;
    }

    public void addLast(Item item) {
        checkNull(item);

        Node<Item> oldLast = last;
        last = new Node<>(item, null, oldLast);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        checkDequeIsEmpty();
        Item item = first.item;
        first = first.next;
        first.previous = null;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public Item removeLast() {
        checkDequeIsEmpty();
        Item item = last.item;
        last = last.previous;
        last.next = null;
        size--;
        if (isEmpty()) {
            first = null;
        }
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
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void checkNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkDequeIsEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        System.out.println(deque.size());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
    }
}