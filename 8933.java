import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import net.sf.japaki.self.ParserBench;
import net.sf.japaki.text.ParsingException;

/**
 * Example how to create a parser for a standard java bean.
 * @version 1.0
 * @author Ralph Wagner
 */
public class ReflectionExample {

    public void runSample() {
        ParserBench bench = new ParserBench();
        try {
            bench.add("human", Human.class, "<firstname>;<lastname>;<birthYear>;\\n");
            ArrayList<Human> list = new ArrayList<Human>();
            bench.parse("human", "names.txt", list);
            System.out.println(list);
        } catch (Exception e) {
            bench.handleException(e);
        }
    }

    public static void main(String[] args) {
        ReflectionExample sample = new ReflectionExample();
        sample.runSample();
    }
}
