public class test_return_subtype_ok {

    public static void main(String[] args) {
        TestController ct = new TestController();
        TestLoader ld1 = new TestLoader(ClassLoader.getSystemClassLoader(), "ld1", ct);
        TestLoader ld2 = new TestLoader(ClassLoader.getSystemClassLoader(), "ld2", ct);
        ld1.addClassfile("Foo", "classes1/Foo.class");
        ld1.addClassfile("DerivedFoo", "classes2/DerivedFoo.class");
        ld1.addParentDelegation("java.lang.Object");
        ld1.addParentDelegation("java.lang.String");
        ld2.addClassfile("BarPassFoo", "classes2/BarPassFoo.class");
        ld2.addDelegation("Foo", ld1);
        ld2.addDelegation("DerivedFoo", ld1);
        ld2.addParentDelegation("java.lang.Object");
        ld2.addParentDelegation("java.lang.String");
        ct.expect("requested", ld2, "BarPassFoo");
        ct.expect("defined", ld2, "<BarPassFoo>");
        ct.expect("loaded", ld2, "<BarPassFoo>");
        Class cls = ct.loadClass(ld2, "BarPassFoo");
        ct.expectLoadFromSystem(ld2, "java.lang.Object");
        ct.expectDelegationAndDefinition(ld2, ld1, "DerivedFoo");
        ct.expect("requested", ld1, "Foo");
        ct.expect("defined", ld1, "<Foo>");
        ct.expectLoadFromSystem(ld1, "java.lang.Object");
        ct.checkStringGetter(cls, "getDerivedFoo", "no exception");
        ct.expectEnd();
        ct.expectDelegationAndFound(ld2, ld1, "Foo");
        ct.checkStringGetter(cls, "getDerivedFooAsFoo", "no exception");
        ct.exit();
    }
}
