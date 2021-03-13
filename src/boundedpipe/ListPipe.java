package boundedpipe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Pipe implemented by build-in list.
 *
 * @author pohan(pohan)
 * @param <E> the type of elements in this pipe
 * @version 1.0
 */
public class ListPipe<E> extends AbstractPipe<E> {

    private final List<E> list;

    /**
     * Constructor
     *
     * @param capacity the number of element that this pipe can hold
     */
    public ListPipe(int capacity) {
        super(capacity);
        list = new LinkedList<>();
    }

    @Override
    public E first() {
        return isEmpty() ? null : list.get(0);
    }

    @Override
    public E last() {
        return isEmpty() ? null : list.get(list.size() - 1);
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

        list.add(0, element);
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

        list.add(element);
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return list.remove(0);
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return list.remove(list.size() - 1);
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public Pipe<E> newInstance() {
        return new ListPipe<>(capacity());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
