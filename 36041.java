import gnu.beanfactory.*;

public class TestBean {

    String name;

    public TestBean() {
        System.err.println(this + ".TestBean()");
    }

    public void preInit() {
        System.err.println(this + ".preInit()");
    }

    public void postInit() {
        System.err.println(this + ".postInit()");
    }

    public String getMyProperty() {
        return name;
    }

    public void setMyProperty(String s) {
        System.err.println(this + ".setMyProperty(" + s + ")");
        name = s;
    }

    public static void main(String[] args) throws BeanFactoryException {
        Container.getBeanContext();
        System.err.println("-- Before 1st Lookup --");
        Container.lookup("bean:/TestBean");
        System.err.println("-- After  1st Lookup --");
        System.err.println("-- Before 2nd Lookup --");
        Container.lookup("bean:/TestBean");
        System.err.println("-- After 2nd Lookup  --");
    }
}
