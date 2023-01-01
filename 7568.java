import net.sf.colorer.*;
import net.sf.colorer.editor.*;
import net.sf.colorer.impl.*;
import java.util.*;

/**
 * Simple test routine, used to test Colorer library working.
 *
 * @author irusskih
 *
 */
class Test {

    public static void main(String[] arr) {
        ParserFactory pf = new ParserFactory();
        for (Enumeration e = pf.enumerateHRDInstances("rgb"); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            System.out.println(name + " - " + pf.getHRDescription("rgb", name));
        }
        BaseEditor be = new BaseEditorNative(pf, new LineSource() {

            public String getLine(int no) {
                return null;
            }

            ;
        });
        for (Enumeration e = pf.getHRCParser().enumerateFileTypes(); e.hasMoreElements(); ) {
            FileType type = (FileType) e.nextElement();
            System.out.println("loading: " + type.getDescription());
            String[] pars = type.getParameters();
            for (int i = 0; i < pars.length; i++) {
                String pname = pars[i];
                System.out.println("  param " + pname + " = " + type.getParameterValue(pname));
            }
            be.setFileType(type);
        }
        ;
    }

    ;
}
