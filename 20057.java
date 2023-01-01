/**
 * Abstraction for runtime structures that are simple arrays.  Funneling all 
 * allocation through these routines allows the necessary special handling
 * for unusual array layouts.
 *
 * @author David F. Bacon
 */
class VM_RuntimeStructures implements VM_Constants {

    /**
     * Allocate an array of instructions
     * @param n The number of instructions to allocate
     * @return The instruction array
     */
    static INSTRUCTION[] newInstructions(int n) {
        VM_Magic.pragmaInline();
        if (VM.BuildForRealtimeGC) {
            return VM_SegmentedArray.newInstructions(n);
        }
        return new INSTRUCTION[n];
    }

    /**
     * Allocate a stack array
     * @param n The number of stack slots to allocate
     * @return The stack array
     */
    static int[] newStack(int n) {
        VM_Magic.pragmaInline();
        if (VM.BuildForRealtimeGC) {
            return VM_SegmentedArray.newStack(n);
        }
        return new int[n];
    }
}
