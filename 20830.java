/**
 * Dummy class containing enough references to force java compiler
 * to find every class comprising the vm, so everything gets recompiled
 * by just compiling "Dummy.java".
 *
 * The minimal set has to be discovered by trial and error. Sorry.
 *
 * @author Derek Lieber
 */
class Dummy {

    static VM a;

    static VM_TableBasedDynamicLinker b;

    static VM_DynamicLinker c;

    static VM_Runtime d;

    static VM_Reflection e;

    static java.lang.Void g;

    static java.io.InputStreamReader h;

    static java.io.StreamTokenizer i;

    static java.net.PlainSocketImpl j;

    static java.net.ServerSocket k;

    static VM_WriteBarrier l;

    static VM_JNIFunctions m;

    static VM_JNIStartUp n;

    static VM_RecompilationManager o;

    static VM_RCBuffers p;

    static VM_OptRCWriteBarrier q;

    static VM_MultianewarrayHelper r;

    static VM_Address s;
}
