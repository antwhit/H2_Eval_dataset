import junit.framework.TestCase;

public class CalcTest extends TestCase {

    public void testReturnOne() {
        Calc calc = new Calc();
        assertEquals(1, calc.returnOne());
    }

    public void testReturnTwo() {
        Calc calc = new Calc();
        assertEquals(2, calc.returnTwo());
    }
}
