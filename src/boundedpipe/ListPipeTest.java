package boundedpipe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListPipeTest {

    private Pipe<String> pipeABC6;
    private Pipe<String> pipeEmpty;

    @Before
    public void setUp() {
        pipeABC6 = new ListPipe<>(6);
        pipeABC6.append("A");
        pipeABC6.append("B");
        pipeABC6.append("C");

        pipeEmpty = new ListPipe<>(6);
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
    public void toString_Empty() {
        assertEquals("[]:6", pipeEmpty.toString());
    }
}