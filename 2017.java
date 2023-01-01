/**
 * Iterator for stack frames inserted at the transition from Java to
 * JNI Native C.  It will report JREFs associated with the executing
 * C frames which are in the "JREFs stack" attached to the executing
 * Threads JNIEnvironment.  It will update register location addresses
 * for the non-votatile registers to point to the register save area
 * in the transition frame.
 *
 * If GC happens, the saved non-volatile regs may get modified (ex. a ref
 * to a live object that gets moved), and a restore flag in the frame is
 * set to cause the returning Native code to restore those registers from
 * this save area.  If GC does not occur, the Native C code has restored
 * these regs, and the transition return code does not do the restore.
 *
 * @author Steve Smith
 */
final class VM_JNIGCMapIterator extends VM_GCMapIterator implements VM_BaselineConstants {

    int[] jniRefs;

    int jniNextRef;

    int jniFramePtr;

    int jniSavedProcessorRegAddr;

    int jniSavedReturnAddr;

    VM_JNIGCMapIterator(int[] registerLocations) {
        this.registerLocations = registerLocations;
    }

    void newStackWalk(VM_Thread thread) {
        super.newStackWalk(thread);
        VM_JNIEnvironment env = this.thread.getJNIEnv();
        if (env != null) {
            this.jniRefs = env.JNIRefs;
            this.jniNextRef = env.JNIRefsTop;
            this.jniFramePtr = env.JNIRefsSavedFP;
            this.jniSavedProcessorRegAddr = 0;
        }
    }

    void setupIterator(VM_CompiledMethod compiledMethod, int instructionOffset, int framePtr) {
        this.framePtr = framePtr;
        int callers_fp = VM_Magic.getMemoryWord(this.framePtr);
        jniSavedProcessorRegAddr = callers_fp - JNI_PR_OFFSET;
        jniSavedReturnAddr = callers_fp - JNI_PROLOG_RETURN_ADDRESS_OFFSET;
        VM_Magic.setMemoryWord(callers_fp - JNI_GC_FLAG_OFFSET, 1);
    }

    int getNextReferenceAddress() {
        int nextFP;
        int ref_address;
        if (jniNextRef > jniFramePtr) {
            ref_address = jniNextRef + VM_Magic.objectAsAddress(jniRefs);
            jniNextRef = jniNextRef - 4;
            return ref_address;
        }
        if (jniSavedProcessorRegAddr != 0) {
            ref_address = jniSavedProcessorRegAddr;
            jniSavedProcessorRegAddr = 0;
            return ref_address;
        }
        if (jniFramePtr > 0) {
            jniFramePtr = jniRefs[jniFramePtr >> 2];
            jniNextRef = jniNextRef - 4;
        }
        int registerLocation = VM_Magic.getMemoryWord(this.framePtr) - JNI_RVM_NONVOLATILE_OFFSET;
        for (int i = LAST_NONVOLATILE_GPR; i >= FIRST_NONVOLATILE_GPR - 1; --i) {
            registerLocations[i] = registerLocation;
            registerLocation -= 4;
        }
        return 0;
    }

    int getNextReturnAddressAddress() {
        int ref_address;
        if (jniSavedReturnAddr != 0) {
            ref_address = jniSavedReturnAddr;
            jniSavedReturnAddr = 0;
            return ref_address;
        }
        return 0;
    }

    void reset() {
    }

    void cleanupPointers() {
    }

    int getType() {
        return VM_GCMapIterator.JNI;
    }
}
