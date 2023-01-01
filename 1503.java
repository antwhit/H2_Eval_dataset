import java.util.*;
import java.awt.*;

/**
 * Source javadoc
 * set super interfaces
 * set this comment
 * set these flags
 * set this comment
 */
private class EchoSoapBindingImpl extends echo.EchoBindingImpl implements echo.EchoSource1, EchoSource2 {

    /**
   * source javadoc Non-existing fields in target
   */
    public static final double firstNotInTarget = Math.PI;

    public int splitN1 = Math.abs(-1);

    public int splitN2 = 3;

    public int splitN3;

    /**
   * source javadoc: Non-existing field with flags in reversed order
   * (starts with volatile)
   */
    private static final transient volatile double reversedFlagsNotInTarget = 0;

    public static final transient volatile int noFlagsInTarget;

    protected final int manyFlagsInTarget = 0;

    private int split1 = 1;

    private int split2 = 2;

    private int split3 = 3;

    private int split5 = 5;

    public int split4 = 4, split6 = 6;

    Object noFlagsInTargetT;

    private static final transient volatile double manyFlagsInTargetT = 0;

    public long splitT1 = 1;

    public long splitT2 = 2;

    public long splitT3 = 3;

    public long splitT6 = 6;

    public int splitT4 = 4, splitT5 = 5;

    String stringT = new String();

    java.lang.String stringLiteralT = "test";

    double[] simpleArrayT = { 1, 2, 3 };

    /**
   * Source initializer comment
   * set this comment
   * set these flags
   */
    static {
        System.out.println("Source class level initializer 1 line 1");
        System.out.println("Target class level initializer 1 line 2");
        System.out.println("Source class level initializer 1 line 3");
    }

    int noFlagsInsourceI = 1;

    int noFlagsInTargetI;

    private static final transient volatile int manyFlagsInTargetI;

    public int splitI1 = 100, splitI2 = 200, splitI3 = 300;

    /**
   *  source javadoc: split I5 since it has a comment
   */
    public int splitI5 = 500;

    /**
   *  target javadoc: split due to setComment
   *  set this comment
   */
    public int splitI4 = 4, splitI6;

    Object stringI = "1" + "2" + "3";

    java.lang.Object stringLiteralI = new String("test");

    int[] simpleArrayI1 = { 1, 2, 3 };

    int[] simpleArrayI2 = { 1, 2, 4 };

    /**
   * Note: JDOM removes final keyword from parameter
   * 
   * target comment
   * @param name
   * @throws java.rmi.RemoteException
   * @generated
   */
    public void hello(java.lang.Source sourceName1, final int[][][] sourceTripleArrayWithSourceName, String paramOnNewLine) throws java.rmi.RemoteException, BadAttributeValueExpException, NullPointerException, IllegalArgumentException, TestS {
        System.out.println("Source user code 1");
        System.out.println("Source code 2");
        return name;
    }

    /**
   * Method same as hello(..) but has different parameters
   * 
   * source method comment 1
   * @param sourceName1
   * @throws java.rmi.RemoteException
   * @throws BadAttributeValueExpException
   * @throws NullPointerException
   * @throws IllegalArgumentException
   * @throws TestS
   * 
   */
    public void hello(java.lang.Source sourceName1) throws java.rmi.RemoteException, BadAttributeValueExpException, NullPointerException, IllegalArgumentException, TestS {
        System.out.println("Source user code 3");
        System.out.println("Source code 4");
        return name;
    }

    /**
   * source method comment 2
   * 
   * @param name
   * @throws java.rmi.RemoteException
   */
    public void hello1(java.lang.String name) throws java.rmi.RemoteException {
        return name;
    }

    /**
   * target comment 3
   * @param name
   * @throws java.rmi.RemoteException
   */
    public void hello2(java.lang.String name) throws java.rmi.RemoteException {
        return name;
    }
}
