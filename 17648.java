import com.sun.javadoc.*;
import java.util.*;
import java.io.*;

/**
 * This class is used to make the files cdk/src/*.javafiles .
 */
public class MakeJavaFilesFilesDoclet {

    private final String javaDocModuleTag = "cdk.module";

    private final String javaDocRequireTag = "cdk.require";

    private Hashtable cdkPackages;

    public MakeJavaFilesFilesDoclet() {
        cdkPackages = new Hashtable();
    }

    private String toAPIPath(String className) {
        StringBuffer sb = new StringBuffer();
        className = className;
        for (int i = 0; i < className.length(); i++) {
            if (className.charAt(i) == '.') {
                sb.append('/');
            } else {
                sb.append(className.charAt(i));
            }
        }
        return sb.toString();
    }

    private String toEntity(String className) {
        return "<listitem>&" + className.substring(20) + ";</listitem>";
    }

    public void process(RootDoc root) throws IOException {
        processPackages(root.specifiedPackages());
        Enumeration keys = cdkPackages.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            PrintWriter outJava = new PrintWriter((Writer) new FileWriter(key + ".javafiles"));
            PrintWriter outClass = new PrintWriter((Writer) new FileWriter(key + ".classes"));
            PrintWriter entList = new PrintWriter((Writer) new FileWriter(key + ".entitylist"));
            Vector packageClasses = (Vector) cdkPackages.get(key);
            Enumeration classes = packageClasses.elements();
            while (classes.hasMoreElements()) {
                String packageClass = (String) classes.nextElement();
                outJava.println(toAPIPath(packageClass) + ".java");
                outClass.println(toAPIPath(packageClass) + "*.class");
                entList.println(toEntity(packageClass));
            }
            outJava.flush();
            outJava.close();
            outClass.flush();
            outClass.close();
            entList.flush();
            entList.close();
        }
    }

    private void processPackages(PackageDoc[] pkgs) throws IOException {
        for (int i = 0; i < pkgs.length; i++) {
            processClasses(pkgs[i].allClasses());
        }
    }

    private void addClassToCDKPackage(String packageClass, String cdkPackageName) {
        Vector packageClasses = (Vector) cdkPackages.get(cdkPackageName);
        if (packageClasses == null) {
            packageClasses = new Vector();
            cdkPackages.put(cdkPackageName, packageClasses);
        }
        packageClasses.addElement(packageClass);
    }

    private void processClass(ClassDoc classDoc) throws IOException {
        String className = classDoc.qualifiedName();
        Tag[] tags = classDoc.tags(javaDocModuleTag);
        String cdkPackage = "extra";
        if (tags.length > 0) {
            cdkPackage = tags[0].text();
        }
        addClassToCDKPackage(className, cdkPackage);
        String restriction = null;
        tags = classDoc.tags(javaDocRequireTag);
        for (int i = 0; i < tags.length; i++) {
            String cdkRequirement = tags[i].text();
            addClassToCDKPackage(className, cdkRequirement);
        }
    }

    private void processClasses(ClassDoc[] classes) throws IOException {
        for (int i = 0; i < classes.length; i++) {
            ClassDoc doc = classes[i];
            processClass(doc);
        }
    }

    public static boolean start(RootDoc root) {
        try {
            MakeJavaFilesFilesDoclet doclet = new MakeJavaFilesFilesDoclet();
            doclet.process(root);
            return true;
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
            return false;
        }
    }
}
