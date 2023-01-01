import annot.*;
import annot.annot2.*;

@MySimple("value")
@MyMarker
@AnnotMarker
@AnnotSimple("foo")
@AnnotMarker2
@AnnotSimple2("bar")
public class ClassAnnotations {

    static double d;

    public void foo() {
        return;
    }

    private double bar(int baz) {
        @AnnotShangri_la int local = 0;
        return (double) baz;
    }

    static class NestedClass {

        protected int field;
    }
}
