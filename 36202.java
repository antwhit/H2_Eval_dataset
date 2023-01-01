import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class TesteMulta extends TestCase {

    Multa multaLivro;

    @Before
    public void setUp() {
        multaLivro = new Multa(0.5, 2.0, 3.0, 5.0);
    }

    @Test
    public void testGetMulta() throws Exception {
        assertEquals("Erro de getMulta 1a2dias", 0.5, multaLivro.getMulta("1a2dias"));
        assertEquals("Erro de getMulta 3a14dias", 2.0, multaLivro.getMulta("3a14dias"));
        assertEquals("Erro de getMulta 15a30dias", 3.0, multaLivro.getMulta("15a30dias"));
        assertEquals("Erro de getMulta 31dias", 5.0, multaLivro.getMulta("31dias"));
    }

    @Test
    public void testSetMulta() throws Exception {
        multaLivro.setMulta("1a2dias", 1.5);
        assertEquals("Erro de getMulta 1a2dias", 1.5, multaLivro.getMulta("1a2dias"));
        multaLivro.setMulta("3a14dias", 2.5);
        assertEquals("Erro de getMulta 2a14dias", 2.5, multaLivro.getMulta("3a14dias"));
        multaLivro.setMulta("15a30dias", 3.5);
        assertEquals("Erro de getMulta 15a30dias", 3.5, multaLivro.getMulta("15a30dias"));
        multaLivro.setMulta("31dias", 4.5);
        assertEquals("Erro de getMulta 31dias", 4.5, multaLivro.getMulta("31dias"));
    }
}
