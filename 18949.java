import jscript.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class TestIf implements IJScriptUnit {

    boolean testLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void test() {
        System.out.println("Test");
    }

    public Object run() throws Exception {
        return run(new ArgList());
    }

    public Object run(Object[] args) throws Exception {
        return run(new ArgList(args));
    }

    public Object run(String[] args) throws Exception {
        return run(new ArgList(args));
    }

    private Object run(ArgList args) throws Exception {
        if (true) {
            int year = 1989;
            if (testLeap(year)) System.out.println(year + " is a leap year."); else System.out.println(year + " is not a leap year.");
            year = 2000;
            int kkk = 1;
            boolean leap;
            if (year % 4 != 0) leap = false; else if (year % 100 != 0) leap = true; else if (year % 400 != 0) leap = false; else leap = true;
            if (leap == true) System.out.println(year + " is a leap year."); else System.out.println(year + " is not a leap year.");
            year = 2050;
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) leap = true; else leap = false;
                } else leap = false;
            } else leap = false;
            if (leap == true) System.out.println(year + " is a leap year."); else System.out.println(year + " is not a leap year.");
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        new TestIf().run(args);
    }
}
