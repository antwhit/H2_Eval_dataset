import java.util.Set;
import java.util.Map;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import static javax.lang.model.SourceVersion.*;
import javax.lang.model.element.*;
import javax.lang.model.util.*;
import static javax.lang.model.util.ElementFilter.*;
import static javax.tools.Diagnostic.Kind.*;
import static javax.tools.StandardLocation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Test basic functionality of the Filer.getResource method.  On the
 * first run of the annotation processor, write out a resource file
 * and on the second run read it in.
 */
@SupportedAnnotationTypes("*")
@SupportedOptions("phase")
public class TestGetResource extends AbstractProcessor {

    private Messager messager;

    private Filer filer;

    private Map<String, String> options;

    private static String CONTENTS = "Hello World.";

    private static String PKG = "";

    private static String RESOURCE_NAME = "Resource1";

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            if (!roundEnv.processingOver()) {
                String phase = options.get("phase");
                if (phase.equals("write")) {
                    PrintWriter pw = new PrintWriter(filer.createResource(CLASS_OUTPUT, PKG, RESOURCE_NAME).openWriter());
                    pw.print(CONTENTS);
                    pw.close();
                } else if (phase.equals("read")) {
                    String contents = filer.getResource(CLASS_OUTPUT, PKG, RESOURCE_NAME).getCharContent(false).toString();
                    if (!contents.equals(CONTENTS)) throw new RuntimeException("Expected \n\t" + CONTENTS + "\nbut instead got \n\t" + contents);
                    filer.createResource(CLASS_OUTPUT, PKG, RESOURCE_NAME);
                } else {
                    throw new RuntimeException("Unexpected phase: " + phase);
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return false;
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
        options = processingEnv.getOptions();
    }
}
