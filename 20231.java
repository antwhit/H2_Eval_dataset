import com.marce.simulador.equipo.PoderIndividual;
import junit.framework.TestCase;

public class PoderIndividualTest extends TestCase {

    public PoderIndividualTest() {
        super("Poder Individual");
    }

    public void testPoder() {
        PoderIndividual poder = new PoderIndividual();
        poder.addPunto(5f);
        poder.addPunto(2f);
        poder.addPunto(20f);
        assertEquals(97f, poder.getPoder());
        assertEquals(20f, poder.getPoderes().get(0));
        assertEquals(2f, poder.getPoderes().get(1));
        assertEquals(5f, poder.getPoderes().get(2));
        assertEquals(10f, poder.getPoderes().get(3));
    }
}
