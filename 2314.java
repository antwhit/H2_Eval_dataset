import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.cayenne.jpa.Provider;

public class CreateSchema {

    public static void main(String[] args) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(Provider.CREATE_SCHEMA_PROPERTY, "true");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default", properties);
        entityManagerFactory.close();
    }
}
