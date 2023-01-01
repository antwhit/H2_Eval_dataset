/**
 * java.io.PrintStream java.io.PrintStream.append(char)
 *
 * overrides
 *
 * java.lang.Appendable java.lang.Appendable.append(char)
 *
 * Yet javac should allow extending PrintStream, as long as the user
 * doesn't directly override a covariant method in -source 1.4.
 **/
public class ExtendCovariant1 extends java.io.PrintStream {

    ExtendCovariant1() throws java.io.IOException {
        super("");
    }
}
