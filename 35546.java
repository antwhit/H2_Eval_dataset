import javax.ejb.*;
import java.rmi.*;
import javax.rmi.*;
import java.util.Properties;
import javax.naming.*;
import java.security.*;
import com.examples.BenchHome;
import com.examples.Bench;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.jboss.logging.XLevel;

public class client {

    public static void main(String[] args) throws Exception {
        System.out.println("Start Client !!!");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        BasicConfigurator.configure();
        Category root = Category.getRoot();
        root.setLevel(XLevel.TRACE);
        try {
            Properties properties = new Properties();
            InitialContext jndiContext = new InitialContext(properties);
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            properties.put(Context.PROVIDER_URL, "localhost:1099");
            InitialContext c = new InitialContext(properties);
            System.out.println("Got context !!!!");
            Object o = c.lookup("ejb/Bench_SSL");
            System.out.println("Bean Found !!!");
            BenchHome home = (BenchHome) PortableRemoteObject.narrow(o, BenchHome.class);
            Bench bean = home.create();
            long startTime;
            startTime = System.currentTimeMillis();
            System.out.println(bean.sayHello());
            System.out.println("Say Hello took ms: " + (System.currentTimeMillis() - startTime));
            startTime = System.currentTimeMillis();
            System.out.println(bean.getTime());
            System.out.println("get Time took ms: " + (System.currentTimeMillis() - startTime));
            startTime = System.currentTimeMillis();
            double[] result = bean.compute(5);
            System.out.println("Compute took ms: " + (System.currentTimeMillis() - startTime));
            for (int i = 0; i < result.length; i++) {
                System.out.println("results:" + result[i]);
            }
            bean.remove();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
