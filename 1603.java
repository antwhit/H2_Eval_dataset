import org.aspectj.testing.Tester;

class A extends C {

    A() {
        super("");
        Tester.event("Called standard A constructor");
    }
}

class C {

    C(String x) {
        Tester.event("Called string C constructor");
    }
}

class B extends C {

    B() {
        super("");
        Tester.event("Called standard B constructor");
    }
}

public class BinaryClasses2 {

    public static void main(String[] args) {
    }
}
