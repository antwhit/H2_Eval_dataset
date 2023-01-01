import pl.wcislo.sbql4j.mirror.apt.*;
import pl.wcislo.sbql4j.mirror.declaration.*;
import pl.wcislo.sbql4j.mirror.type.*;
import pl.wcislo.sbql4j.mirror.util.*;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;

public class Misc implements AnnotationProcessorFactory {

    static class MiscCheck implements AnnotationProcessor {

        AnnotationProcessorEnvironment ape;

        MiscCheck(AnnotationProcessorEnvironment ape) {
            this.ape = ape;
        }

        public void process() {
            Collection<Declaration> decls = ape.getDeclarationsAnnotatedWith((AnnotationTypeDeclaration) ape.getTypeDeclaration("Marker"));
            for (Declaration decl : decls) {
                if (!decl.getSimpleName().startsWith("marked")) throw new RuntimeException();
            }
        }
    }

    static Collection<String> supportedTypes;

    static {
        String types[] = { "*" };
        supportedTypes = Collections.unmodifiableCollection(Arrays.asList(types));
    }

    Collection<String> supportedOptions = Collections.unmodifiableCollection(new HashSet<String>());

    public Collection<String> supportedOptions() {
        return supportedOptions;
    }

    public Collection<String> supportedAnnotationTypes() {
        return supportedTypes;
    }

    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment ape) {
        return new MiscCheck(ape);
    }
}
