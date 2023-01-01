import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;
import com.sun.mirror.util.*;
import java.util.Collection;
import java.util.Set;
import java.util.Arrays;
import static java.util.Collections.*;
import static com.sun.mirror.util.DeclarationVisitors.*;

public class MemberOrderApf implements AnnotationProcessorFactory {

    private static final Collection<String> supportedAnnotations = unmodifiableCollection(Arrays.asList("*"));

    private static final Collection<String> supportedOptions = emptySet();

    public Collection<String> supportedAnnotationTypes() {
        return supportedAnnotations;
    }

    public Collection<String> supportedOptions() {
        return supportedOptions;
    }

    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new MemberOrderAp(env);
    }

    private static class MemberOrderAp implements AnnotationProcessor {

        private final AnnotationProcessorEnvironment env;

        MemberOrderAp(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        private void verifyOrder(Collection<? extends Declaration> decls) {
            int count = 0;
            for (Declaration decl : decls) {
                VisitOrder order = decl.getAnnotation(VisitOrder.class);
                if (order.value() <= count) throw new RuntimeException("Out of order declarations");
                count = order.value();
            }
        }

        public void process() {
            for (TypeDeclaration td : env.getSpecifiedTypeDeclarations()) {
                verifyOrder(td.getFields());
                verifyOrder(td.getMethods());
            }
        }
    }
}
