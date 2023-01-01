import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class HibernateSessionTest {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Test
    public void hibernateSessionShouldNotBeNull() {
        Assert.assertNotNull("La session Hibernate ne doit pas �tre null", sessionFactory);
    }
}
