public class ReflectionTestSamePackageHelper {

    protected int protectedIntField;

    protected double protectedDoubleField;

    private int privateIntField;

    private double privateDoubleField;

    protected void protectedVoidMethod() {
        System.out.println("cvmtest.ReflectionTestSamePackageHelper.protectedVoidMethod()");
    }

    private void privateVoidMethod() {
        System.out.println("cvmtest.ReflectionTestSamePackageHelper.privateVoidMethod()");
    }
}
