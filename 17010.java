import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author Justus Rogowski
 * 
 */
public class ASMTest {

    /**
	 * Simple main to execute
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        JarFile jFile = null;
        try {
            jFile = new JarFile("junit.jar");
            Vector<JarEntry> classes = getJarClassEntries(jFile);
            Enumeration<JarEntry> classEnum = classes.elements();
            int i = 1;
            while (classEnum.hasMoreElements()) {
                InputStream iStream = jFile.getInputStream(classEnum.nextElement());
                ClassReader cr = new ClassReader(iStream);
                ClassNode aClass = new ClassNode();
                cr.accept(aClass, true);
                System.out.println("---------------------------------------");
                System.out.println("Nr: " + i++);
                System.out.println("Name: " + aClass.name);
                System.out.println("SuperClass: " + aClass.superName);
                System.out.println("MethodCount: " + aClass.methods.size());
                Iterator<MethodNode> iter = aClass.methods.iterator();
                while (iter.hasNext()) {
                    MethodNode mNode = iter.next();
                    System.out.println("\tMethodname: " + mNode.name);
                }
                System.out.println("FieldCount: " + aClass.fields.size());
                Iterator<FieldNode> iter2 = aClass.fields.iterator();
                while (iter2.hasNext()) {
                    FieldNode fNode = iter2.next();
                    System.out.println("\tFields: " + fNode.name);
                }
                System.out.println("AccessType : " + aClass.access);
            }
            jFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
	 * Filters a given Jar File for all Class-JarEntries
	 * 
	 * @param jFile
	 * @return
	 */
    public static Vector<JarEntry> getJarClassEntries(JarFile jFile) {
        Vector<JarEntry> result = new Vector<JarEntry>();
        Enumeration<JarEntry> enumeration1 = jFile.entries();
        while (enumeration1.hasMoreElements()) {
            JarEntry entry = enumeration1.nextElement();
            if (entry.getSize() > 0) {
                String name = entry.getName();
                if (name.substring(name.length() - 6, name.length()).equals(".class") || name.substring(name.length() - 6, name.length()).equals(".CLASS")) {
                    result.add(entry);
                }
            }
        }
        return result;
    }
}
