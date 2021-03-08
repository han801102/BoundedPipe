package boundedpipe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListPipeTest {

    private Pipe<String> pipeABC6;
    private Pipe<String> pipeEmpty6;

    @Before
    public void setUp() {
        pipeABC6 = initABC(6);

        pipeEmpty6 = new ListPipe<>(6);
    }

    @Test
    public void length_ABC_3() {
        assertEquals(pipeABC6.length(), 3);
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
    public void appendX_ABC_ABCX() {
        pipeABC6.append("X");
        assertEquals(4, pipeABC6.length());
        assertEquals("X", pipeABC6.removeLast());
    }

    @Test
    public void removeFirst_ABC_A() {
        String first = pipeABC6.removeFirst();
        assertEquals(first, "A");
        assertEquals(2, pipeABC6.length());
    }

    @Test
    public void removeLast_ABC_C() {
        String last = pipeABC6.removeLast();
        assertEquals(last, "C");
        assertEquals(2, pipeABC6.length());
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
        Pipe<String> p = initABC(6);
        assertTrue(pipeABC6.equals(p));
    }

    @Test
    public void equals_ABC6ToString_false() {
        assertFalse(pipeABC6.equals("[A, B, C]:6"));
    }

    @Test
    public void equals_ABC6ToABC10_false() {
        Pipe<String> p = initABC(10);
        assertFalse(pipeABC6.equals(p));
    }

    @Test
    public void equals_ABC6ToAB6_false() {
        Pipe<String> p = new ListPipe<>(6);
        p.append("A");
        p.append("B");
        assertFalse(pipeABC6.equals(p));
    }

    @Test
    public void equals_empty6ToEmpty6_true() {
        Pipe<String> p = new ListPipe<>(6);
        assertTrue(pipeEmpty6.equals(p));
    }

    @Test
    public void equals_empty6ToEmpty5_false() {
        Pipe<String> p = new ListPipe<>(5);
        assertFalse(pipeEmpty6.equals(p));
    }

    @Test
    public void equals_ABC6ToDEF6_false() {
        Pipe<String> p = new ListPipe<>(6);
        p.append("D");
        p.append("E");
        p.append("F");
        assertFalse(pipeABC6.equals(p));
    }
    
    @Test
    public void equals_ABC6ListToABC6Array_true() {
        // TODO: 3/8/21
    }

    private Pipe<String> initABC(int capacity) {
        Pipe<String> p = new ListPipe<>(capacity);
        p.append("A");
        p.append("B");
        p.append("C");
        return p;
    }

    @Test
    public void hashCode_ABC6ToABC6_true() {
        Pipe<String> p = initABC(6);

        assertTrue(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ToABC10_false() {
        Pipe<String> p = initABC(10);
        assertFalse(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ToAB6_false() {
        Pipe<String> p = new ListPipe<>(6);
        p.append("A");
        p.append("B");
        assertFalse(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ToDEF6_false() {
        Pipe<String> p = new ListPipe<>(6);
        p.append("D");
        p.append("E");
        p.append("F");
        assertFalse(pipeABC6.hashCode() == p.hashCode());
    }

    @Test
    public void hashCode_ABC6ListToABC6Array_true() {
        // TODO: 3/8/21
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
        Pipe<String> p = new ListPipe<>(2);
        p.append("D");
        p.append("E");

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

    @Test
    public void appendAll_DEArrayToABCList_ABCDE() {
        // TODO: 3/8/21  
    }
}