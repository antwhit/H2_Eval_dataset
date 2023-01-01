import gnu.beanfactory.*;
import java.util.*;

public class TestBean {

    String myString;

    int myInt;

    double[] myDoubleArray;

    Object[] myObjectArray;

    Vector myVector;

    Hashtable myHashtable;

    public TestBean() {
        System.err.println(this + ".TestBean()");
    }

    public void preInit() {
        System.err.println(this + ".preInit()");
    }

    public void postInit() {
        System.err.println(this + ".postInit()");
    }

    public String getStringProperty() {
        return myString;
    }

    public void setStringProperty(String s) {
        System.err.println(this + ".setStringProperty(" + s + ")");
        myString = s;
    }

    public int getIntProperty() {
        return myInt;
    }

    public void setIntProperty(int i) {
        System.out.println(this + ".setIntProperty(" + i + ")");
        myInt = i;
    }

    public void setDoubleArray(double[] d) {
        System.out.println(this + ".setDoubleArray(" + d + ")");
        myDoubleArray = d;
    }

    public double[] getDoubleArray() {
        return myDoubleArray;
    }

    public void setVectorProperty(Vector v) {
        System.out.println(this + ".setVectorProperty(" + v + ")");
        myVector = v;
    }

    public Vector getVectorProperty() {
        return myVector;
    }

    public Object[] getObjectArrayProperty() {
        return myObjectArray;
    }

    public void setObjectArrayProperty(Object[] a) {
        System.out.println(this + ".setObjectArrayProperty(" + a + ")");
        myObjectArray = a;
    }

    public Hashtable getHashtableProperty() {
        return myHashtable;
    }

    public void setHashtableProperty(Hashtable h) {
        System.out.println(this + ".setHashtableProperty(" + h + ")");
        myHashtable = h;
    }

    public static void main(String[] args) throws BeanFactoryException {
        Container.getBeanContext();
        System.err.println("-- Before Lookup --");
        Container.lookup("bean:/TestBean");
        System.err.println("-- After  Lookup --");
    }
}
