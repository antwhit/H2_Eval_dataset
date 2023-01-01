import com.google.common.collect.Iterables;
import com.mycila.inject.internal.Reflect;
import java.io.File;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Drafts {

    public static void main(String[] args) throws Exception {
        perfTestMembers();
    }

    private static void perfTestMembers() throws Exception {
        List<Class<?>> classes = new LinkedList<Class<?>>();
        JarFile jarFile = new JarFile(new File("/usr/lib/jvm/java-6-sun-1.6.0.20/jre/lib/rt.jar"));
        Enumeration<JarEntry> enums = jarFile.entries();
        while (enums.hasMoreElements()) {
            JarEntry entry = enums.nextElement();
            if (entry.getName().endsWith(".class")) {
                if (entry.getName().startsWith("javax/swing") || entry.getName().startsWith("java/awt") || entry.getName().startsWith("java/awt")) classes.add(Class.forName(entry.getName().replace('/', '.').substring(0, entry.getName().length() - 6)));
            }
        }
        System.out.println("classes: " + classes.size());
        long time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            for (Class<?> c : classes) {
                Iterables.filter(Reflect.findMethods(c), Reflect.annotatedBy(Deprecated.class));
            }
        }
        long end = System.nanoTime();
        System.out.println((end - time) + "ns = " + ((end - time) / 1000000) + "ms");
    }
}
