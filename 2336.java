import gnu.beanfactory.*;
import java.util.*;

public class TestBean {

    String myString;

    int myInt;

    public String getStringProperty() {
        return myString;
    }

    public void setStringProperty(String s) {
        myString = s;
    }

    public int getIntProperty() {
        return myInt;
    }

    public void setIntProperty(int i) {
        myInt = i;
    }

    public String toString() {
        return "stringProperty=" + getStringProperty() + ", intProperty=" + getIntProperty();
    }

    public static void main(String[] args) throws BeanFactoryException {
        TestBean parent = (TestBean) Container.lookup("bean:/Parent");
        TestBean child = (TestBean) Container.lookup("bean:/Child");
        System.out.println("Parent : " + parent);
        System.out.println("Child  : " + child);
    }
}
