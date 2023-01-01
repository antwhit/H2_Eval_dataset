import gnu.beanfactory.*;

public class TestBean {

    String name;

    public TestBean() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public static void main(String[] args) throws BeanFactoryException {
        String name = (String) Container.resolve("bean:/TestBean.name");
        System.out.println("Resolved name is: " + name);
    }
}
