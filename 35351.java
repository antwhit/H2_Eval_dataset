import java.util.Enumeration;

/**
 * This class provides utilities to record defs and uses of physical
 * registers by IR operators.
 *
 * @author Stephen Fink
 */
class OPT_PhysicalDefUse {

    static final int mask = 0x00;

    static final int maskC0 = 0x01;

    static final int maskXER = 0x02;

    static final int maskLR = 0x04;

    static final int maskJTOC = 0x08;

    static final int maskCTR = 0x10;

    static final int maskPR = 0x20;

    private static final int maskHIGH = 0x20;

    private static final int maskALL = 0x3F;

    static final int maskC0_XER = maskC0 | maskXER;

    static final int maskJTOC_LR = maskJTOC | maskLR;

    static final int maskJTOC_CTR = maskJTOC | maskCTR;

    static final int maskcallDefs = maskLR;

    static final int maskcallUses = maskJTOC;

    static final int maskIEEEMagicUses = maskJTOC;

    static final int maskTSPDefs = maskPR;

    static final int maskTSPUses = maskJTOC;

    /**
   * @return a string representation of the physical registers encoded by
   * an integer
   */
    static String getString(int code) {
        if (code == mask) return "";
        String s = "";
        if ((code & maskC0) != 0) s += " C0";
        if ((code & maskXER) != 0) s += " XER";
        if ((code & maskLR) != 0) s += " LR";
        if ((code & maskJTOC) != 0) s += " JTOC";
        if ((code & maskCTR) != 0) s += " CTR";
        if ((code & maskPR) != 0) s += " PR";
        return s;
    }

    /**
   * @param code an integer that encodes a set of physical registers
   * @param ir the governing IR
   * @return an enumeration of the physical registers embodied by a code
   */
    static PDUEnumeration enumerate(int code, OPT_IR ir) {
        return new PDUEnumeration(code, ir);
    }

    /**
   * @param ir the governing IR
   * @return an enumeration of all physical registers that code be 
   *         implicitly defed/used
   */
    static PDUEnumeration enumerateAllImplicitDefUses(OPT_IR ir) {
        return new PDUEnumeration(maskALL, ir);
    }

    /**
   * A class to enumerate physical registers based on a code.
   */
    static final class PDUEnumeration implements Enumeration {

        private int code;

        private int curMask;

        private OPT_PhysicalRegisterSet phys;

        PDUEnumeration(int c, OPT_IR ir) {
            phys = ir.regpool.getPhysicalRegisterSet();
            code = c;
            curMask = maskHIGH;
        }

        public boolean hasMoreElements() {
            return code != 0;
        }

        public Object nextElement() {
            while (true) {
                int curBit = code & curMask;
                code -= curBit;
                curMask = curMask >> 1;
                if (curBit != 0) return getReg(curBit, phys);
            }
        }

        private static OPT_Register getReg(int m, OPT_PhysicalRegisterSet phys) {
            switch(m) {
                case maskC0:
                    return phys.getConditionRegister(0);
                case maskXER:
                    return phys.getXER();
                case maskLR:
                    return phys.getLR();
                case maskJTOC:
                    return phys.getJTOC();
                case maskCTR:
                    return phys.getCTR();
                case maskPR:
                    return phys.getPR();
            }
            OPT_OptimizingCompilerException.UNREACHABLE();
            return null;
        }
    }
}
