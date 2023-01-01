import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import net.javaseminar.shopping.CartItem;
import net.javaseminar.shopping.Notebook;
import net.javaseminar.shopping.ShoppingCart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPDO {

    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("todos");
        entityManager = managerFactory.createEntityManager();
    }

    @Test
    public void test() {
        ShoppingCart cart = new ShoppingCart();
        CartItem item = new Notebook("Sony", "Vaio", new BigDecimal(990));
        cart.add(item);
        Assert.assertEquals(item, cart.getMostExpensive());
    }

    @After
    public void tearDown() throws Exception {
    }
}
