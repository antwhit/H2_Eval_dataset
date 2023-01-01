import instructionFormats.*;
import java.util.Vector;
import java.util.Enumeration;

/** 
 *
 * OPT_InsertInstructionCounters.java
 *
 * The following OPT phase inserts counters on all instructions in the
 * IR.  It maintians one counter for each operand type, so it output
 * how many loads were executed, how many int_add's etc.  This is
 * useful for debugging and assessing the accuracy of optimizations.
 *
 * Note: The counters are added at the end of HIR, so the counts will
 * NOT reflect any changes to the code that occur after HIR.
 * 
 * @author Matthew Arnold 
 *
 **/
class OPT_InsertInstructionCounters extends OPT_CompilerPhase implements OPT_Operators, VM_Constants, OPT_Constants {

    static final boolean DEBUG = false;

    final boolean shouldPerform(OPT_Options options) {
        return options.INSERT_INSTRUCTION_COUNTERS;
    }

    final String getName() {
        return "InsertInstructionCounters";
    }

    /**
    * Insert a counter on every instruction, and group counts by
    * opcode type.  
    *
    * @param ir the governing IR
    */
    public final void perform(OPT_IR ir) {
        if (!ir.method.getDeclaringClass().isInterruptible() || ir.method.getDeclaringClass().isInBootImage() || !VM_Instrumentation.instrumentationEnabled()) return;
        VM_StringEventCounterData data = VM_AOSDatabase.instructionCounterData;
        Vector bbList = new Vector();
        for (OPT_BasicBlockEnumeration bbe = ir.getBasicBlocks(); bbe.hasMoreElements(); ) {
            OPT_BasicBlock bb = bbe.next();
            bbList.add(bb);
        }
        for (Enumeration e = bbList.elements(); e.hasMoreElements(); ) {
            OPT_BasicBlock bb = (OPT_BasicBlock) e.nextElement();
            Vector iList = new Vector();
            OPT_Instruction i = bb.firstInstruction();
            while (i != null && i != bb.lastInstruction()) {
                iList.add(i);
                i = i.nextInstructionInCodeOrder();
            }
            for (Enumeration instructions = iList.elements(); instructions.hasMoreElements(); ) {
                i = (OPT_Instruction) instructions.nextElement();
                if (i.operator() == LABEL || Prologue.conforms(i)) continue;
                if (i.isBranch() || i.operator() == RETURN) {
                    OPT_Instruction prev = i.prevInstructionInCodeOrder();
                    if (prev.isBranch()) {
                        OPT_BasicBlock newBlock = bb.splitNodeWithLinksAt(prev, ir);
                        bb.recomputeNormalOut(ir);
                    }
                    OPT_Instruction counterInst = data.getCounterInstructionForEvent(i.operator().toString());
                    i.insertBefore(counterInst);
                } else {
                    OPT_Instruction counterInst = data.getCounterInstructionForEvent(i.operator().toString());
                    i.insertBefore(counterInst);
                }
            }
        }
    }
}
