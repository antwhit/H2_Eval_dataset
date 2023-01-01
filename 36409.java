import java_pragmas.*;

public class java_pragmas_runme {

    public static void main(String argv[]) {
        long int_pointer = java_pragmasJNI.get_int_pointer();
        java_pragmas.added_function("hello");
    }
}
