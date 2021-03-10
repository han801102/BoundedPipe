package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPipe<E> extends AbstractPipe<E> {

    private Node first;
    private Node last;
    private int length;

    public LinkedPipe(int capacity) {
        super(capacity);
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public E first() {
        return first == null ? null : first.content;
    }

    @Override
    public E last() {
        return last == null ? null : last.content;
    }

    @Override
    public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
        if (isFull()) throw new IllegalStateException();

        if (element == null) throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.content = element;
        newNode.prev = null;

        if (first == null) {
            newNode.next = null;
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }

        length++;
    }

    @Override
    public void append(E element) throws IllegalStateException, IllegalArgumentException {
        if (isFull()) throw new IllegalStateException();

        if (element == null) throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.content = element;
        newNode.next = null;

        if (last == null) {
            newNode.prev = null;
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty()) throw new IllegalStateException();

        Node removeNode = first;
        if (length == 1) {
            reset();
        } else {
            first = first.next;
            first.prev = null;
            length--;
        }

        return removeNode.content;
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty()) throw new IllegalStateException();

        Node removeNode = last;
        if (length == 1) {
            reset();
        } else {
            last = last.prev;
            last.next = null;
            length--;
        }

        return removeNode.content;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Pipe<E> newInstance() {
        return new LinkedPipe<>(capacity());
    }

    @Override
    public void clear() {
        reset();
    }

    private void reset() {
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new PipeIterator();
    }

    class PipeIterator implements Iterator<E> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            E content = currentNode.content;
            currentNode = currentNode.next;
            return content;
        }
    }

    class Node {
        public E content;
        public Node prev;
        public Node next;
    }
}
