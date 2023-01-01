import junit.framework.TestCase;

public class TreeMapRandomTest extends TestCase {

    public void testRemove() {
        TreeMap treemap = new TreeMap();
        treemap.put(1);
        treemap.put(2);
        treemap = Values.randoop(sketch.examples.TreeMap.class);
        treemap.remove(3);
    }
}
