import edu.neu.ccs.demeterf.lib.ListSet;

/** A quick test of the new "imperative" demfgen.lib.List Methods */
public class ListSetTest {

    java.util.Random r = new java.util.Random();

    public static void main(String[] args) {
        ListSet<Integer> s1 = ListSet.create(1, 3, 5, 3, 7, 5, 4, 9);
        ListSet<Integer> s2 = ListSet.create(1, 4, 3, 9, 5);
        System.out.println(" 1: " + s1);
        System.out.println(" 2: " + s2);
        System.out.println(" ==: " + s2.equals(s1));
        System.out.println(" ==: " + s1.equals(s2));
        System.out.println(" ==: " + s2.intersect(s1).equals(s2));
        System.out.println("  Hash: " + s1.hashCode());
        System.out.println("  Hash: " + s2.hashCode());
        System.out.println(" Union: " + s1.union(s2));
        System.out.println(" Inter: " + s1.intersect(s2));
        System.out.println("  Diff: " + s1.difference(s2));
    }
}
