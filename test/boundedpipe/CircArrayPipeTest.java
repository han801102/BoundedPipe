package boundedpipe;

public class CircArrayPipeTest extends BasePipeTest {
    @Override
    public Pipe<String> initPipe(int capacity, String... args) {
        Pipe<String> p = new CircArrayPipe<>(capacity);
        for (String s : args) {
            p.append(s);
        }
        return p;
    }
}
