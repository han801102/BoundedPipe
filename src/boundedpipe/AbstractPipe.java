package boundedpipe;

import java.util.Iterator;

/**
 * @inheritDoc
 */
public abstract class AbstractPipe<E> implements Pipe<E> {

    private final int capacity;

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
        if (that == null) {
            throw new IllegalArgumentException();
        }

        if (that.length() == 0) {
            return;
        }

        E element = that.removeFirst();
        this.append(element);
        this.appendAll(that);
    }

    @Override
    public Pipe<E> copy() {
        Pipe<E> result = this.newInstance();
        for (E element : this) {
            result.append(element);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pipe)) {
            return false;
        }

        Pipe<?> that = (Pipe<?>) obj;
        if (this.capacity() != that.capacity()) {
            return false;
        }
        if (this.length() != that.length()) {
            return false;
        }

        Iterator<E> thisIter = this.iterator();
        Iterator<?> thatIter = that.iterator();
        while (thisIter.hasNext())
        {
            E e = thisIter.next();
            Object o = thatIter.next();
            if (!e.equals(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (E element : this) {
            result = 31 * result + element.hashCode();
        }
        result = 31 * result + capacity();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iterator = iterator();
        while (iterator.hasNext())
        {
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
