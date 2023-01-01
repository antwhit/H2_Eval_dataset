/**
*Calls a function computeMean implemented in Python using connection with C++ dynamic library called AmiCo with the help of JNI.
@author Sergey Karakovskiy

*/
public class JavaCallsPython {

    /**
     *
     * @return  This is a function from AmiCo library. Returns the result of computeMean.
     */
    private native int mean(int a, int b);

    /**
     * Calls computeMean and prints the result
     * @see main
     */
    public static void main(String[] args) {
        JavaCallsPython javaCallsPython = new JavaCallsPython();
        System.out.println(javaCallsPython.mean(2, 6));
    }

    /**
     * Loading AmiCo library that calls computeMean from our Python-module
     */
    static {
        System.out.println(System.getProperty("java.library.path"));
        System.out.println("Java: loading AmiCo...");
        System.loadLibrary("javacallspython");
        System.out.println("Java: AmiCo library has been successfully loaded!");
    }
}
