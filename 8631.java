class TestMetaclass extends Thread {

    static void createInstanceOf(String typeName) {
        SystemOut.println("Trying \"" + typeName + "\"");
        try {
            Class c = Class.forName(typeName);
            SystemOut.println("classForName: " + c.getName());
            Object o = c.newInstance();
            SystemOut.println("newInstance: " + o);
        } catch (Throwable e) {
            SystemOut.println(e);
        }
        SystemOut.println();
    }

    public static void main(String args[]) {
        runTest();
    }

    public static void runTest() {
        SystemOut.println("TestMetaclass");
        createInstanceOf("foobar");
        createInstanceOf("I");
        createInstanceOf("java.lang.Number");
        createInstanceOf("[Ljava.lang.String;");
        createInstanceOf("[I");
        createInstanceOf("java.lang.Integer");
        createInstanceOf("java.lang.String");
    }
}
