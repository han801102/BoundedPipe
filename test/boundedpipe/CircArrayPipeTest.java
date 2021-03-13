package boundedpipe;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CircArrayPipeTest {

    private Pipe<String> pipeABC6;
    private Pipe<String> pipeEmpty6;
    private Pipe<String> pipeFull3;
    private Pipe<String> pipeA3;

    @Before
    public void setUp() {
        pipeABC6 = initPipe(6, "A", "B", "C");
        pipeEmpty6 = initPipe(6);
        pipeFull3 = initPipe(3, "A", "B", "C");
        pipeA3 = initPipe(3, "A");
    }

    public Pipe<String> initPipe(int capacity, String... args) {
        Pipe<String> p = new CircArrayPipe<>(capacity);
        for (String s : args) {
            p.append(s);
        }
        return p;
    }

    @Test
    public void length_ABC_3() {
        assertEquals(3, pipeABC6.length());
    }

    @Test
    public void length_empty_0() {
        assertEquals(0, pipeEmpty6.length());
    }

    @Test
    public void capacity_ABC_6() {
        assertEquals(6, pipeABC6.capacity());
    }

    @Test
    public void prependX_ABC_XABC() {
        pipeABC6.prepend("X");
        assertEquals(4, pipeABC6.length());
        assertEquals("X", pipeABC6.removeFirst());
    }

    @Test
    public void prependX_empty_X() {
        pipeEmpty6.prepend("X");
        assertEquals(1, pipeEmpty6.length());
        assertEquals("X", pipeEmpty6.removeFirst());
    }

    @Test(expected = IllegalStateException.class)
    public void prependX_full_exception() {
        pipeFull3.prepend("X");
    }

    @Test(expected = IllegalArgumentException.class)
    public void prependNull_full_exception() {
        pipeFull3.prepend(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void prependNull_ABC_exception() {
        pipeABC6.prepend(null);
    }

    @Test
    public void appendX_ABC_ABCX() {
        pipeABC6.append("X");
        assertEquals(4, pipeABC6.length());
        assertEquals("X", pipeABC6.removeLast());
    }

    @Test
    public void appendX_empty_X() {
        pipeEmpty6.append("X");
        assertEquals(1, pipeEmpty6.length());
        assertEquals("X", pipeEmpty6.removeLast());
    }

    @Test(expected = IllegalStateException.class)
    public void appendX_full_exception() {
        pipeFull3.append("X");
    }

    @Test(expected = IllegalArgumentException.class)
    public void appendNull_full_exception() {
        pipeFull3.append(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void appendNull_ABC_exception() {
        pipeABC6.append(null);
    }

    @Test
    public void removeFirst_ABC_A() {
        String first = pipeABC6.removeFirst();
        assertEquals("A", first);
        assertEquals(2, pipeABC6.length());
    }

    @Test
    public void removeFirst_A_A() {
        String first = pipeA3.removeFirst();
        assertEquals("A", first);
        assertEquals(0, pipeA3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void removeFirst_empty_exception() {
        pipeEmpty6.removeFirst();
    }

    @Test
    public void removeLast_ABC_C() {
        String last = pipeABC6.removeLast();
        assertEquals("C", last);
        assertEquals(2, pipeABC6.length());
    }

    @Test
    public void removeLast_A_A() {
        String last = pipeA3.removeLast();
        assertEquals("A", last);
        assertEquals(0, pipeA3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void removeLast_empty_exception() {
        pipeEmpty6.removeLast();
    }

    @Test
    public void iterator_ABC() {
        StringBuilder result = new StringBuilder();
        for (String s : pipeABC6) {
            result.append(s);
        }

        assertEquals("ABC", result.toString());
    }

    @Test
    public void iterator_empty() {
        StringBuilder result = new StringBuilder();
        for (String s : pipeEmpty6) {
            result.append(s);
        }

        assertEquals("", result.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_emptyOnce_exception() {
        Iterator<String> iterator = pipeEmpty6.iterator();
        iterator.next();
    }

    @Test
    public void toString_ABC6() {
        assertEquals("[A, B, C]:6", pipeABC6.toString());
    }

    @Test
    public void toString_empty() {
        assertEquals("[]:6", pipeEmpty6.toString());
    }

    @Test
    public void equals_null_false() {
        assertFalse(pipeABC6.equals(null));
    }

    @Test
    public void equals_self_true() {
        assertTrue(pipeABC6.equals(pipeABC6));
    }

    @Test
    public void equals_ABC6ToABC6_true() {
        Pipe<String> p = initPipe(6, "A", "B", "C");
        assertTrue(pipeABC6.equals(p));
    }

    @Test
    public void equals_ABC6ToString_false() {
        assertFalse(pipeABC6.equals("[A, B, C]:6"));
    }

    @Test
    public void equals_ABC6ToABC10_false() {
        Pipe<String> p = initPipe(10, "A", "B", "C");
        assertFalse(pipeABC6.equals(p));
    }

    @Test
    public void equals_ABC6ToAB6_false() {
        Pipe<String> p = initPipe(6, "A", "B");
        assertFalse(pipeABC6.equals(p));
    }

    @Test
    public void equals_empty6ToEmpty6_true() {
        Pipe<String> p = initPipe(6);
        assertTrue(pipeEmpty6.equals(p));
    }

    @Test
    public void equals_empty6ToEmpty5_false() {
        Pipe<String> p = initPipe(5);
        assertFalse(pipeEmpty6.equals(p));
    }

    @Test
    public void equals_ABC6ToDEF6_false() {
        Pipe<String> p = initPipe(6, "D", "E", "F");
        assertFalse(pipeABC6.equals(p));
    }

    @Test
    public void equals_ABC6ListToABC6Array_true() {
        Pipe<String> listPipe = new ListPipe<>(6);
        listPipe.append("A");
        listPipe.append("B");
        listPipe.append("C");
        assertTrue(pipeABC6.equals(listPipe));
    }

    @Test
    public void equals_ABC6LinkedToABC6Array_true() {
        Pipe<String> linkedPipe = new LinkedPipe<>(6);
        linkedPipe.append("A");
        linkedPipe.append("B");
        linkedPipe.append("C");
        assertTrue(pipeABC6.equals(linkedPipe));
    }

    @Test
    public void hashCode_ABC6ToABC6_true() {
        Pipe<String> p = initPipe(6, "A", "B", "C");
        assertTrue(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ToABC10_false() {
        Pipe<String> p = initPipe(10, "A", "B", "C");
        assertFalse(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ToAB6_false() {
        Pipe<String> p = initPipe(6, "A", "B");
        assertFalse(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ToDEF6_false() {
        Pipe<String> p = initPipe(6, "D", "E", "F");
        assertFalse(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ListToABC6Array_true() {
        Pipe<String> listPipe = new ListPipe<>(6);
        listPipe.append("A");
        listPipe.append("B");
        listPipe.append("C");
        assertTrue(pipeABC6.hashCode() == listPipe.hashCode());
    }

    @Test
    public void hashCode_ABC6LinkedToABC6Array_true() {
        Pipe<String> linkedPipe = new LinkedPipe<>(6);
        linkedPipe.append("A");
        linkedPipe.append("B");
        linkedPipe.append("C");
        assertTrue(pipeABC6.hashCode() == linkedPipe.hashCode());
    }

    @Test
    public void copy_ABC() {
        Pipe<String> copy = pipeABC6.copy();
        assertEquals(pipeABC6.capacity(), copy.capacity());
        assertEquals(pipeABC6.length(), copy.length());
        assertEquals(pipeABC6, copy);
        String s1 = pipeABC6.removeFirst();
        String copyS1 = copy.removeFirst();
        assertTrue(s1 == copyS1);
    }

    @Test
    public void copy_Empty() {
        Pipe<String> copy = pipeEmpty6.copy();
        assertEquals(pipeEmpty6.capacity(), copy.capacity());
        assertEquals(pipeEmpty6.length(), copy.length());
        assertEquals(pipeEmpty6, copy);
    }

    @Test
    public void newInstance_empty() {
        Pipe<String> copy = pipeABC6.newInstance();
        assertEquals(0, copy.length());
        assertEquals(pipeABC6.capacity(), copy.capacity());
    }

    @Test
    public void clear_ABC6() {
        pipeABC6.clear();
        int capacity = pipeABC6.capacity();
        assertEquals(0, pipeABC6.length());
        assertEquals(capacity, pipeABC6.capacity());
    }

    @Test
    public void appendAll_DEToABC_ABCDE() {
        Pipe<String> p = initPipe(2, "D", "E");

        pipeABC6.appendAll(p);

        assertEquals(5, pipeABC6.length());
        assertEquals(6, pipeABC6.capacity());
        assertEquals("[A, B, C, D, E]:6", pipeABC6.toString());
    }

    @Test
    public void appendAll_emptyToABC_ABC() {
        pipeABC6.appendAll(pipeEmpty6);

        assertEquals(3, pipeABC6.length());
        assertEquals(6, pipeABC6.capacity());
        assertEquals("[A, B, C]:6", pipeABC6.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void appendAll_nullToABC_exception() {
        pipeABC6.appendAll(null);
    }

    @Test
    public void appendAll_DEListToABCArray_ABCDE() {
        Pipe<String> listPipe = new ListPipe<>(3);
        listPipe.append("D");
        listPipe.append("E");

        pipeABC6.appendAll(listPipe);
        assertEquals(5, pipeABC6.length());
        assertEquals(6, pipeABC6.capacity());
        assertEquals("[A, B, C, D, E]:6", pipeABC6.toString());
    }

    @Test
    public void appendAll_DELinkedToABCArray_ABCDE() {
        Pipe<String> linkedPipe = new LinkedPipe<>(3);
        linkedPipe.append("D");
        linkedPipe.append("E");

        pipeABC6.appendAll(linkedPipe);
        assertEquals(5, pipeABC6.length());
        assertEquals(6, pipeABC6.capacity());
        assertEquals("[A, B, C, D, E]:6", pipeABC6.toString());
    }

    @Test
    public void first_ABC_A() {
        String first = pipeABC6.first();
        assertEquals(first, "A");
    }

    @Test
    public void first_empty_null() {
        String first = pipeEmpty6.first();
        assertNull(first);
    }

    @Test
    public void last_ABC_C() {
        String last = pipeABC6.last();
        assertEquals(last, "C");
    }

    @Test
    public void last_empty_null() {
        String last = pipeEmpty6.last();
        assertNull(last);
    }

    @Test
    public void removeFirstTwiceAppendXY_ABC_CXY() {
        Pipe<String> p = initPipe(4, "A", "B", "C");
        String f = p.removeFirst();
        assertEquals(f, "A");
        assertEquals(2, p.length());
        f = p.removeFirst();
        assertEquals(f, "B");
        assertEquals(1, p.length());
        p.append("X");
        assertEquals(2, p.length());
        p.append("Y");
        assertEquals(3, p.length());
        assertEquals("[C, X, Y]:4", p.toString());
    }
}
