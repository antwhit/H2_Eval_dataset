/**
 * @author David Bacon
 */
class VM_RCBarriers implements VM_BaselineConstants {

    static final boolean DONT_BARRIER_BLOCK_CONTROLS = true;

    private static final boolean TRACE_DYNAMIC_BARRIERS = false;

    private static void emitBufferStores(VM_Assembler asm, int spSaveAreaOffset, int Told, int Tnew, int Ttmp) {
        asm.emitL(Ttmp, VM_Entrypoints.incDecBufferTopOffset, PROCESSOR_REGISTER);
        asm.emitCMPI(Tnew, 0);
        asm.emitBEQ(+2);
        asm.emitSTU(Tnew, 4, Ttmp);
        asm.emitCMPI(Told, 0);
        asm.emitBEQ(+3);
        asm.emitCAL(Told, VM_RCBuffers.DECREMENT_FLAG, Told);
        asm.emitSTU(Told, 4, Ttmp);
        asm.emitST(Ttmp, VM_Entrypoints.incDecBufferTopOffset, PROCESSOR_REGISTER);
        asm.emitL(Told, VM_Entrypoints.incDecBufferMaxOffset, PROCESSOR_REGISTER);
        asm.emitCMP(Ttmp, Told);
        asm.emitBLE(VM_Assembler.CALL_INSTRUCTIONS + 3);
        asm.emitL(S0, VM_Entrypoints.processIncDecBufferOffset, JTOC);
        asm.emitMTLR(S0);
        asm.emitCall(spSaveAreaOffset);
    }

    static void compileArrayStoreBarrier(VM_Assembler asm, int spSaveAreaOffset) {
        asm.emitLWARX(T2, T0, T1);
        asm.emitSTWCXr(T3, T0, T1);
        asm.emitBNE(-2);
        emitBufferStores(asm, spSaveAreaOffset, T2, T3, T0);
    }

    static void compilePutfieldBarrier(VM_Assembler asm, int spSaveAreaOffset, int fieldOffset, VM_Method method, VM_Field field) {
        if (DONT_BARRIER_BLOCK_CONTROLS) {
            String className = field.getDeclaringClass().getDescriptor().toString();
            if (className.equals("LVM_BlockControl;")) {
                VM.sysWrite("Omitting barrier for method " + method + " field " + field + "\n");
                asm.emitST(T0, fieldOffset, T1);
                return;
            }
        }
        asm.emitCAL(T1, fieldOffset, T1);
        asm.emitLWARX(T2, 0, T1);
        asm.emitSTWCXr(T0, 0, T1);
        asm.emitBNE(-2);
        emitBufferStores(asm, spSaveAreaOffset, T2, T0, T1);
    }

    static void compilePutstaticBarrier(VM_Assembler asm, int spSaveAreaOffset, int jtocOffset) {
        asm.emitCALtoc(T1, jtocOffset);
        asm.emitLWARX(T2, 0, T1);
        asm.emitSTWCXr(T0, 0, T1);
        asm.emitBNE(-2);
        emitBufferStores(asm, spSaveAreaOffset, T2, T0, T1);
    }

    static void compileDynamicPutfieldBarrier(VM_Assembler asm, int spSaveAreaOffset, VM_Method method, VM_Field field) {
        if (DONT_BARRIER_BLOCK_CONTROLS) {
            String className = field.getDeclaringClass().getDescriptor().toString();
            if (className.equals("LVM_BlockControl;")) {
                VM.sysWrite("Omitting dynamic barrier for method " + method + " field " + field + "\n");
                asm.emitST(T0, 0, T1);
                return;
            }
        }
        if (TRACE_DYNAMIC_BARRIERS) VM.sysWrite(" REFCOUNTING for putfield - dynamic link from " + method + " to " + field + "\n");
        asm.emitLWARX(T2, 0, T1);
        asm.emitSTWCXr(T0, 0, T1);
        asm.emitBNE(-2);
        emitBufferStores(asm, spSaveAreaOffset, T2, T0, T1);
    }

    static void compileDynamicPutstaticBarrier(VM_Assembler asm, int spSaveAreaOffset, VM_Method method, VM_Field field) {
        if (true || TRACE_DYNAMIC_BARRIERS) VM.sysWrite(" REFCOUNTING for putstatic - dynamic link from " + method + " to " + field + "\n");
        asm.emitLWARX(T2, 0, T1);
        asm.emitSTWCXr(T0, 0, T1);
        asm.emitBNE(-2);
        emitBufferStores(asm, spSaveAreaOffset, T2, T0, T1);
    }
}
