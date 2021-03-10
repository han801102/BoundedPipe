package boundedpipe;

public class LinkedPipeTest extends BasePipeTest {

    @Override
    public Pipe<String> initPipe(int capacity, String... args) {
        Pipe<String> p = new LinkedPipe<>(capacity);
        for (String s : args) {
            p.append(s);
        }
        return p;
    }
}