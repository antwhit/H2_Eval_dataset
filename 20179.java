import org.jikesrvm.compilers.common.RecompilationManager;
import org.jikesrvm.runtime.DynamicLinker;
import org.jikesrvm.runtime.MathConstants;
import org.jikesrvm.runtime.Reflection;
import org.jikesrvm.scheduler.greenthreads.VMProcess;

/**
 * Dummy class containing enough references to force java compiler
 * to find every class comprising the vm, so everything gets recompiled
 * by just compiling "Dummy.java".
 * <p/>
 * The minimal set has to be discovered by trial and error. Sorry.
 */
class Dummy {

    static org.jikesrvm.VM a;

    static org.jikesrvm.classloader.TableBasedDynamicLinker b;

    static DynamicLinker c;

    static org.jikesrvm.jni.JNIFunctions d;

    static Reflection e;

    static VMProcess f;

    static org.vmmagic.pragma.SaveVolatile i;

    static org.jikesrvm.memorymanagers.mminterface.MM_Interface l;

    static RecompilationManager o;

    static org.jikesrvm.ArchitectureSpecific.MultianewarrayHelper r;

    static org.vmmagic.unboxed.Address s;

    static MathConstants t;

    static org.vmmagic.unboxed.WordArray x;

    static org.vmmagic.unboxed.OffsetArray y;

    static org.vmmagic.unboxed.ExtentArray z;
}
