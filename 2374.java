import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the BetaTown pay station with progressive rate policy.
    Author: (c) Henrik B�rbak Christensen 2006 */
public class TestProgressiveRate {

    PayStation ps;

    /** Fixture for pay station testing. */
    @Before
    public void setUp() {
        ps = new PayStationImpl(new LinearRateStrategy());
    }

    /** Test a single hour parking */
    @Test
    public void testOneHour() throws IllegalCoinException {
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        assertEquals(60, ps.readDisplay());
    }
}
