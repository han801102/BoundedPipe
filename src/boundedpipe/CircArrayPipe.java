package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Pipe implemented by circular approach.
 *
 * @author pohan(pohan)
 * @param <E> the type of elements in this pipe
 * @version 1.0
 */
public class CircArrayPipe<E> extends AbstractPipe<E> {
    final private E[] elements;
    private int first = -1;
    private int last = -1;

    /**
     * Constructor
     *
     * @param capacity the number of element that this pipe can hold
     */
    @SuppressWarnings("unchecked")
    public CircArrayPipe(int capacity) {
        super(capacity);
        elements = (E[]) new Object[capacity];
    }

    @Override
    public E first() {
        return first == -1 ? null : elements[first];
    }

    @Override
    public E last() {
        return last == -1 ? null : elements[last];
    }

    @Override
    public void prepend(E element) throws IllegalStateException,
            IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (isFull()) {
            throw new IllegalStateException();
        }

        if (isEmpty()) {
            first = 0;
            last = 0;
            elements[first] = element;
            return;
        }

        first = (first + capacity() - 1) % capacity();
        elements[first] = element;
    }

    @Override
    public void append(E element) throws IllegalStateException,
            IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (isFull()) {
            throw new IllegalStateException();
        }

        if (isEmpty()) {
            first = 0;
            last = 0;
            elements[first] = element;
            return;
        }

        last = (last + capacity() + 1) % capacity();
        elements[last] = element;
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int preFirst = first;
        if (first == last) {
            resetPtr();
        } else {
            first = (first + capacity() + 1) % capacity();
        }

        return elements[preFirst];
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int preLast = last;
        if (first == last) {
            resetPtr();
        } else {
            last = (last + capacity() - 1) % capacity();
        }

        return elements[preLast];
    }

    @Override
    public int length() {
        if (first == -1) {
            return 0;
        } else {
            int realLast = last >= first ? last : last + capacity();
            return realLast - first + 1;
        }
    }

    @Override
    public Pipe<E> newInstance() {
        return new CircArrayPipe<>(capacity());
    }

    @Override
    public void clear() {
        resetPtr();
    }

    @Override
    public Iterator<E> iterator() {
        return new PipeIterator();
    }

    private void resetPtr() {
        first = -1;
        last = -1;
    }

    class PipeIterator implements Iterator<E> {
        private int ptr = first;
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count != length();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            count++;
            int currentPtr = ptr;
            ptr = (ptr + 1) % capacity();
            return elements[currentPtr];
        }
    }
}
