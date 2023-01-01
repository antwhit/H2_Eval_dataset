import java.lang.reflect.*;

class Visualizer {

    static void visualize(String name, Class type, Object value) {
        SystemOut.println("===");
        try {
            vis(name, type, value, "");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void vis(String name, Class type, Object value, String indent) throws Exception {
        if (value == null) {
            prt(indent + name + ": null");
        } else if (type.isPrimitive()) {
            if (type == Boolean.TYPE) prt(indent + name + ": " + ((Boolean) value).booleanValue()); else if (type == Character.TYPE) prt(indent + name + ": " + ((Character) value).charValue()); else if (type == Byte.TYPE) prt(indent + name + ": " + ((Byte) value).byteValue()); else if (type == Short.TYPE) prt(indent + name + ": " + ((Short) value).shortValue()); else if (type == Integer.TYPE) prt(indent + name + ": " + ((Integer) value).intValue()); else if (type == Long.TYPE) prt(indent + name + ": " + ((Long) value).longValue()); else if (type == Float.TYPE) prt(indent + name + ": " + ((Float) value).floatValue()); else if (type == Double.TYPE) prt(indent + name + ": " + ((Double) value).doubleValue()); else throw new IllegalArgumentException();
        } else if (type.isArray()) {
            Class componentType = type.getComponentType();
            prt(indent + name + ": " + componentType.getName() + "[]");
            for (int i = 0, n = Array.getLength(value); i < n; ++i) vis("" + i, componentType, Array.get(value, i), indent + "   ");
        } else if (type.getName().equals("java.lang.String")) {
            prt(indent + name + ": \"" + (String) value + "\"");
        } else {
            Field[] fields = type.getFields();
            if (fields.length == 0) {
                prt(indent + name + ": declared as " + type.getName() + " actually is " + (value == null ? "null" : value.getClass().getName()));
            } else {
                prt(indent + name + ": " + type.getName());
                for (int i = 0, n = fields.length; i < n; ++i) {
                    Field f = fields[i];
                    if (Modifier.isStatic(f.getModifiers())) vis(f.getName(), f.getType(), f.get(value), indent + "   static "); else vis(f.getName(), f.getType(), f.get(value), indent + "   ");
                }
            }
        }
    }

    private static void prt(String s) {
        SystemOut.println(s);
    }
}

class Foo {

    class Bar {

        Bar(int i) {
            this.i = i;
        }

        int i;
    }

    ;

    public boolean z = true;

    public byte b = 1;

    public char c = '2';

    public short s = 3;

    public int i = 4;

    public long j = 5;

    public float f = 6;

    public double d = 7;

    public boolean[] az = { true, false };

    public byte[] ab = { 1, 2 };

    public char[] ac = { '3', '4' };

    public short[] as = { 5, 6 };

    public int[] ai = { 7, 8 };

    public long[] aj = { 9, 10 };

    public float[] af = { 11, 12 };

    public double[] ad = { 13, 14 };

    public Object o = new Bar(1);

    public Object[] ao = { new Bar(1), new Bar(2) };

    public Object[][] aao = { { new Bar(1), new Bar(2) }, { new Bar(3), new Bar(4) } };

    public boolean[][] aaz = { { true, false }, { false, true } };

    public String str = "abc";
}

class TestReflection {

    public static void main(String args[]) {
        runTest();
    }

    public static void runTest() {
        SystemOut.println("TestReflection");
        Foo foo = new Foo();
        Visualizer.visualize("foo", foo.getClass(), foo);
    }
}
