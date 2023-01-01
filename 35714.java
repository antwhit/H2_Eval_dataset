import java.lang.reflect.Method;

public class Test123 {

    public void testAnnotationLimiter() throws Exception {
        class SimpleBean {

            @ContextSpecificMethod
            public void hasAnnotation() {
            }

            public void doesNotHaveAnnotation() {
            }
        }
    }
}
