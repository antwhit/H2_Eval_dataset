public class ClassOpTest {

    static void test1() {
        Class c1 = String.class;
        Class c2 = Object.class;
        if (ClassOpTest.class == null) ;
        c1.getClass();
    }

    void test2() {
        Class c2 = Object.class;
        Class c3 = ClassOpTest.class;
        Class c4 = int[].class;
        Class c5 = Object[][].class;
        Class c6 = int.class;
    }
}
