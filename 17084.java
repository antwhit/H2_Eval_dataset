import com.sun.javadoc.*;
import java.util.*;
import java.io.*;

public class JavaDocStats {

    private int publicObjects = 0;

    private int privateObjects = 0;

    private int protectedObjects = 0;

    private int interfaces = 0;

    private int classes = 0;

    private int totalObjects = 0;

    private int objectsWithDescription = 0;

    private Hashtable authors;

    private PrintWriter out;

    public JavaDocStats(PrintWriter out) {
        this.out = out;
    }

    public void process(RootDoc root) throws IOException {
        processPackages(root.specifiedPackages());
    }

    private void processPackages(PackageDoc[] pkgs) throws IOException {
        for (int i = 0; i < pkgs.length; i++) {
            publicObjects = 0;
            privateObjects = 0;
            protectedObjects = 0;
            interfaces = 0;
            classes = 0;
            authors = new Hashtable();
            totalObjects = 0;
            objectsWithDescription = 0;
            processClasses(pkgs[i].allClasses());
            out.println("Package " + pkgs[i].name());
            out.println("  Object types");
            out.println("    classes   : " + classes);
            out.println("    interfaces: " + interfaces);
            out.println("  Object accessibility");
            out.println("    public    : " + publicObjects);
            out.println("    private   : " + privateObjects);
            out.println("    protected : " + protectedObjects);
            out.println("  Object has description");
            out.println("    with      : " + objectsWithDescription);
            out.println("    without   : " + (totalObjects - objectsWithDescription));
            out.println("  Object authors stats");
            Enumeration authorNames = authors.keys();
            while (authorNames.hasMoreElements()) {
                String author = (String) authorNames.nextElement();
                out.println("    " + author + " : " + authors.get(author));
            }
        }
    }

    private void processClasses(ClassDoc[] classes) throws IOException {
        for (int i = 0; i < classes.length; i++) {
            totalObjects++;
            if (classes[i].isInterface()) this.interfaces++; else this.classes++;
            if (classes[i].isPublic()) this.publicObjects++;
            if (classes[i].isProtected()) this.protectedObjects++;
            if (classes[i].isPrivate()) this.privateObjects++;
            if (classes[i].commentText().length() > 0) {
                objectsWithDescription++;
            }
            Tag[] tags = classes[i].tags("author");
            for (int j = 0; j < tags.length; j++) {
                String author = tags[j].text();
                if (this.authors.containsKey(author)) {
                    this.authors.put(author, new Integer(((Integer) authors.get(author)).intValue() + 1));
                } else {
                    this.authors.put(author, new Integer(1));
                }
            }
        }
    }

    public static boolean start(RootDoc root) {
        try {
            PrintWriter out = new PrintWriter((Writer) new FileWriter("javadoc.stats"));
            JavaDocStats stats = new JavaDocStats(out);
            stats.process(root);
            out.flush();
            return true;
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
