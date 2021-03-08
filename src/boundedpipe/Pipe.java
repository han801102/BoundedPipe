package boundedpipe;

/**
 * <p>
 * A bounded, double ended data structure. Elements in the pipe may not be null.
 * </p>
 * <p>
 * A typical string representation of a bounded pipe is
 * <code>[e_1, e_2, ...,e_n-1, e_n]:c</code>,
 * where <code>e_1</code> is the first element of the pipe
 * and <code>e_n</code> is the last element,
 * and <code>c</code> is the capacity.
 * </p>
 * <p>
 * Implementation of this interface should have a one-argument constructor
 * that take the desired capacity and creates an empty pipe.
 * The capacity must be strictly greater than zero.
 * </p>
 * <p><code>public Pipe(int max)</code></p>
 *
 * <p>
 * A pipe iterator iterates through the pipe from beginning to end.
 * </p>
 *
 * @author pohan
 * @param <E> the type of elements in this pipe
 */
public interface Pipe<E> extends Iterable<E>{

    /**
     * <p>
     * Adds the specified element to the beginning of this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>p.prepend(x)</code><br>
     * { <code>p = [X, A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * </p>
     *
     * @param element the element to be prepended to this pipe
     * @throws IllegalStateException if this pipe is full
     * @throws IllegalArgumentException if the specified element is null
     */
    void prepend(E element) throws IllegalStateException, IllegalArgumentException;

    /**
     * <p>
     * Adds the specified element to the end of this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>s.append(x)</code><br>
     * { <code>p = [A, B, C, X]:6</code> <em>and</em> <code>x = X</code> }<br>
     * </p>
     *
     * @param element the element to be appended to this pipe
     * @throws IllegalStateException if this pipe is full
     * @throws IllegalArgumentException if the specified element is null
     */
    void append(E element) throws IllegalStateException, IllegalArgumentException;

    /**
     * <p>
     * Removes and returns the first element from this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>firstItem = p.removeFirst()</code><br>
     * { <code>p = [B, C]:6</code> <em>and</em> <code>firstItem = A</code> }<br>
     * </p>
     *
     * @return the first element from this pipe
     * @throws IllegalStateException if this pipe is empty
     */
    E removeFirst() throws IllegalStateException;

    /**
     * <p>
     * Removes and returns the last element from this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>lastItem = p.removeLast()</code><br>
     * { <code>p = [A, B]:6</code> <em>and</em> <code>lastItem = C</code> }<br>
     * </p>
     *
     * @return the last element from this pipe
     * @throws IllegalStateException if this pipe is empty
     */
    E removeLast() throws IllegalStateException;

    /**
     * <p>
     * Returns the number of elements in this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>l = p.length()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>l = 3</code> }<br>
     * </p>
     *
     * @return the number of elements in this pipe
     */
    int length();

    /**
     * <p>
     * Returns the number of element that this pipe can hold.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>c = p.capacity()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>c = 6</code> }<br>
     * </p>
     *
     * @return the number of element that this pipe can hold
     */
    int capacity();

    /**
     * <p>
     * Creates and returns a new, empty bounded pipe with the same capacity as this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>p_n = p.newInstance()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>p_n = []:6</code> }<br>
     * </p>
     *
     * @return a new, empty bounded pipe with the same capacity as this pipe
     */
    Pipe<E> newInstance();

    /**
     * <p>
     * Removes all the elements from this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>p.clear()</code><br>
     * { <code>p = []:6</code> }<br>
     * </p>
     *
     */
    void clear();

    /**
     * <p>
     * Returns true if the length of this pipe is 0, otherwise false.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = []:6</code> }<br>
     * <code>e = p.isEmpty()</code><br>
     * { <code>p = []:6</code> <em>and</em> <code>e = true</code> }<br>
     * </p>
     *
     * @return true if the length of this pipe is 0, otherwise false
     */
    boolean isEmpty();

    /**
     * <p>
     * Returns true if the length equals to the capacity of this pipe, otherwise false.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C, D, E, F]:6</code> }<br>
     * <code>f = p.isFull()</code><br>
     * { <code>p = [A, B, C, D, E, F]:6</code> <em>and</em> <code>f = true</code> }<br>
     * </p>
     *
     * @return true if the length equals to the capacity of this pipe, otherwise false
     */
    boolean isFull();

    /**
     * <p>
     * Adds all the elements of the specified pipe to the end of this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>p1 = [D, E]:6</code> }<br>
     * <code>p.appendAll(p1)</code><br>
     * { <code>p = [A, B, C, D, E]:6</code> <em>and</em> <code>p1 = [D, E]:6</code> }<br>
     * </p>
     *
     * @param that the pipe to be appended to this pipe
     */
    void appendAll(Pipe<E> that);


    /**
     * <p>
     * Returns a copy of this pipe. The elements in the copy are
     * references to the elements in this pipe.
     * </p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>p1 = p.copy()</code><br>
     * { <code>p = [A, B, C, D, E]:6</code> <em>and</em> <code>p1 = [A, B, C, D, E]:6</code> }<br>
     * </p>
     *
     *
     * @return a copy of this pipe.
     */
    Pipe<E> copy();
}
