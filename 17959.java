import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;
import com.sun.mirror.util.*;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;

public class OptionChecker implements AnnotationProcessorFactory {

    static class OptionCheck implements AnnotationProcessor {

        AnnotationProcessorEnvironment ape;

        OptionCheck(AnnotationProcessorEnvironment ape) {
            this.ape = ape;
        }

        public void process() {
            Map<String, String> options = ape.getOptions();
            if (options.containsKey("-Afoo") && options.containsKey("-Abar") && options.containsKey("-classpath")) {
                System.out.println("Expected options found.");
                return;
            } else {
                System.err.println("Unexpected options values: " + options);
                throw new RuntimeException();
            }
        }
    }

    static class HelloWorld implements AnnotationProcessor {

        AnnotationProcessorEnvironment ape;

        HelloWorld(AnnotationProcessorEnvironment ape) {
            this.ape = ape;
        }

        public void process() {
            java.io.PrintWriter pw;
            try {
                pw = ape.getFiler().createSourceFile("HelloWorld");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            pw.println("public class HelloWorld {");
            pw.println("  public static void main (String argv[]) {");
            pw.println("    System.out.println(\"Hello apt world.\");");
            pw.println("  }");
            pw.println("}");
        }
    }

    static Collection<String> supportedTypes;

    static {
        String types[] = { "*" };
        supportedTypes = Collections.unmodifiableCollection(Arrays.asList(types));
    }

    static Collection<String> supportedOptions;

    static {
        String options[] = { "-Afoo", "-Abar" };
        supportedOptions = Collections.unmodifiableCollection(Arrays.asList(options));
    }

    public Collection<String> supportedOptions() {
        return supportedOptions;
    }

    public Collection<String> supportedAnnotationTypes() {
        return supportedTypes;
    }

    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment ape) {
        if (atds.contains(ape.getTypeDeclaration("Marker"))) {
            System.out.println("Returning composite processor.");
            return AnnotationProcessors.getCompositeAnnotationProcessor(new OptionCheck(ape), new HelloWorld(ape));
        } else {
            System.out.println("Returning single processor.");
            return new OptionCheck(ape);
        }
    }
}
