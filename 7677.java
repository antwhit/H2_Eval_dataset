class SerializableNoFields2 {

    class A {

        String str = null;
    }

    class B implements java.io.Serializable {
    }

    class C extends B {

        int i = 3;

        transient int k;

        static int j;
    }

    class D extends C {

        transient int i = 4;

        static String str = null;
    }
}
