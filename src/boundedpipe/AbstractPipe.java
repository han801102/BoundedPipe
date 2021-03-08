package boundedpipe;

import java.util.Iterator;

public abstract class AbstractPipe<E> implements Pipe<E> {

    private int capacity;

    public AbstractPipe(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public boolean isFull() {
        return length() == capacity;
    }

    @Override
    public void appendAll(Pipe<E> that) {
        // TODO: 3/2/21
    }

    @Override
    public Pipe<E> copy() {
        // TODO: 3/2/21
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: 3/2/21
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO: 3/2/21
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iterator = iterator();
        while(iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        sb.append(":");
        sb.append(capacity);
        return sb.toString();
    }
}
