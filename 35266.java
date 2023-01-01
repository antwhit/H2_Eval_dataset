/**
 * This class holds constants that describe PowerPC register set.
 *
 * @author Mauricio J. Serrano
 * @author Stephen Fink
 * @modified Vivek Sarkar
 * @see OPT_RegisterAllocator
 */
interface OPT_PhysicalRegisterConstants extends VM_RegisterConstants {

    static final byte INT_VALUE = 0;

    static final byte DOUBLE_VALUE = 1;

    static final byte FLOAT_VALUE = 2;

    static final byte CONDITION_VALUE = 3;

    static final byte INT_REG = 0;

    static final byte DOUBLE_REG = 1;

    static final byte CONDITION_REG = 2;

    static final byte SPECIAL_REG = 3;

    static final byte NUMBER_TYPE = 4;

    static final int FIRST_INT = 0;

    static final int FIRST_DOUBLE = NUM_GPRS;

    static final int FIRST_CONDITION = NUM_GPRS + NUM_FPRS;

    static final int FIRST_SPECIAL = NUM_GPRS + NUM_FPRS + NUM_CRS;

    static final int NUMBER_INT_NONVOLAT = LAST_NONVOLATILE_GPR - FIRST_NONVOLATILE_GPR + 1;

    static final int NUMBER_DOUBLE_NONVOLAT = LAST_NONVOLATILE_FPR - FIRST_NONVOLATILE_FPR + 1;

    static final int FIRST_INT_PARAM = FIRST_VOLATILE_GPR + FIRST_INT;

    static final int NUMBER_INT_PARAM = LAST_VOLATILE_GPR - FIRST_VOLATILE_GPR + 1;

    static final int FIRST_DOUBLE_PARAM = FIRST_VOLATILE_FPR + FIRST_DOUBLE;

    static final int NUMBER_DOUBLE_PARAM = LAST_VOLATILE_FPR - FIRST_VOLATILE_FPR + 1;

    static final int FIRST_INT_RETURN = FIRST_VOLATILE_GPR + FIRST_INT;

    static final int NUMBER_INT_RETURN = 2;

    static final int FIRST_DOUBLE_RETURN = FIRST_VOLATILE_FPR + FIRST_DOUBLE;

    static final int NUMBER_DOUBLE_RETURN = 1;

    static final int XER = FIRST_SPECIAL + 0;

    static final int LR = FIRST_SPECIAL + 1;

    static final int CTR = FIRST_SPECIAL + 2;

    static final int CR = FIRST_SPECIAL + 3;

    static final int TU = FIRST_SPECIAL + 4;

    static final int TL = FIRST_SPECIAL + 5;
}
