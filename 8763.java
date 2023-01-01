/**
 * @test @(#)WhichImplicitThis11.java	1.4 07/02/01
 * @bug 4825093
 * @summary code involving inner classes causes verify error
 *
 * @compile WhichImplicitThis11.java
 * @run main WhichImplicitThis11
 */
public class WhichImplicitThis11 {

    public class Inner extends WhichImplicitThis11 {

        Inner(String s) {
            this();
        }

        Inner() {
        }
    }

    public static void main(String[] args) {
        new WhichImplicitThis11().new Inner("");
    }
}
