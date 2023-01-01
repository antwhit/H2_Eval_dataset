import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {

    public static void main(String[] args) throws Exception {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("context.xml"));
        System.out.println(factory.getBean("Mybean"));
    }
}

class Bean extends BeanSupport {

    private String company;

    public void setCompany(String company) {
        System.out.println("setCompany ...");
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("%s : \"%s\"", this.company, getValue());
    }
}

class BeanSupport implements InitializingBean {

    private String value;

    public final void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }

    public final void setValue(String value) {
        System.out.println("setValue ...");
        this.value = value;
    }

    protected final String getValue() {
        System.out.println("getValue ...");
        return this.value;
    }
}
