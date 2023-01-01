import com.ibm.JikesRVM.*;

/**
 * Constants for Intel registers
 * @author Ton Ngo (3/5/01)
 */
interface registerConstants extends VM_RegisterConstants {

    static final int GPR0 = 0;

    static final int GPR7 = GPR0 + NUM_GPRS - 1;

    static final int EIP = 8;

    static final int IAR = EIP;

    static final int LR = 0;

    static final int FPR0 = 0;

    static final int FPR1 = 1;

    static final int FPR2 = 2;

    static final int FPR3 = 3;

    static final int FPR4 = 4;

    static final int FPR5 = 5;

    static final int FPR6 = 6;

    static final int FPR7 = 7;
}
