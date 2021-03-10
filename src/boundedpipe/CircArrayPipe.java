package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircArrayPipe<E> extends AbstractPipe<E> {
    final private E[] elements;
    private int first = -1;
    private int last = -1;

    public CircArrayPipe(int capacity) {
        super(capacity);
        elements = (E[]) new Object[capacity];
    }

    @Override
    public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
        if (isFull()) throw new IllegalStateException();

        if (element == null) throw new IllegalArgumentException();

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
    public void append(E element) throws IllegalStateException, IllegalArgumentException {
        if (isFull()) throw new IllegalStateException();

        if (element == null) throw new IllegalArgumentException();

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
        if (isEmpty()) throw new IllegalStateException();

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
        if (isEmpty()) throw new IllegalStateException();

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
        if (last == -1 && first == -1) {
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
        private final int end = (last + 1) % capacity();

        @Override
        public boolean hasNext() {
            return !isEmpty() && ptr != end;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            int currentPtr = ptr;
            ptr = (ptr + 1) % capacity();
            return elements[currentPtr];
        }
    }
}
