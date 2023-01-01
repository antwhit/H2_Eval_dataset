import java.io.IOException;
import pl.wcislo.sbql4j.javadoc.*;

public class Main extends Tester.Doclet {

    private static final Tester tester = new Tester("Main", "pkg1");

    public static void main(String[] args) throws IOException {
        tester.run();
        tester.verify();
    }

    public static boolean start(RootDoc root) {
        try {
            for (PackageDoc p : root.specifiedPackages()) {
                for (AnnotationTypeDoc a : p.annotationTypes()) {
                    for (AnnotationTypeElementDoc e : a.elements()) {
                        tester.printAnnotationTypeElement(e);
                    }
                    tester.println();
                }
                for (ClassDoc e : p.enums()) {
                    for (FieldDoc ec : e.enumConstants()) {
                        tester.printField(ec);
                    }
                    tester.println();
                }
                for (ClassDoc cd : p.ordinaryClasses()) {
                    for (FieldDoc f : cd.fields()) {
                        tester.printField(f);
                    }
                    tester.println();
                    for (MethodDoc m : cd.methods()) {
                        tester.printMethod(m);
                    }
                    tester.println();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
