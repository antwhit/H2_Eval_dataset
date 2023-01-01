import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RefTest {

    public static void main(String[] args) {
        try {
            Class c = (Class) Class.forName("testRef");
            testRef t = (testRef) c.newInstance();
            System.out.println("============= Super Class ===============");
            System.out.println("Super class:" + c.getSuperclass().getCanonicalName());
            System.out.println("============= Interfaces ===============");
            Class[] interfaces = c.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                System.out.println(interfaces[i].getCanonicalName());
            }
            Field[] field = c.getFields();
            System.out.println("============= Fields ===============");
            for (int i = 0; i < field.length; i++) {
                System.out.println(field[i]);
            }
            System.out.println("============= Methods ===============");
            Method methods[] = c.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i]);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class testRef {

    public int int_test = 12;

    public String str_test = "test";

    public char char_test = 't';

    public long long_test = 100;

    public float float_test = 10.1f;

    public double double_test = 10.5;

    public int getInt_test() {
        return int_test;
    }

    public void setInt_test(int int_test) {
        this.int_test = int_test;
    }

    public String getStr_test() {
        return str_test;
    }

    public void setStr_test(String str_test) {
        this.str_test = str_test;
    }

    public char getChar_test() {
        return char_test;
    }

    public void setChar_test(char char_test) {
        this.char_test = char_test;
    }

    public long getLong_test() {
        return long_test;
    }

    public void setLong_test(long long_test) {
        this.long_test = long_test;
    }

    public float getFloat_test() {
        return float_test;
    }

    public void setFloat_test(float float_test) {
        this.float_test = float_test;
    }

    public double getDouble_test() {
        return double_test;
    }

    public void setDouble_test(double double_test) {
        this.double_test = double_test;
    }
}
