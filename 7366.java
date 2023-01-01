import java.io.*;
import java.util.*;

abstract class AbstractSink implements Sink {

    /** Override this */
    void prepare() {
    }

    /** Override this */
    void postpare() {
    }

    public final void output(World w) throws Exception {
        prepare();
        for (World.Type t : w.getTypes()) output(t);
        postpare();
    }

    abstract void output(World.Type t) throws Exception;

    protected final boolean isMutable(World.Decl d) {
        return (d.getModifiers() & World.Mods.MUTABLE) != 0;
    }

    protected final boolean isImmutable(World.Decl d) {
        return !isMutable(d) && !isSynthetic(d);
    }

    protected final boolean isSynthetic(World.Decl d) {
        return (d.getModifiers() & World.Mods.SYNTHETIC) != 0;
    }

    protected final boolean isAttribute(World.Decl d) {
        return !isMutable(d) && !isSynthetic(d);
    }
}
