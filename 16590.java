import java.util.*;
import instructionFormats.*;

/**
 * This phase puts the IR in SSA form and performs a set of simple
 * optimizations to clean up.  
 *
 * @author Stephen Fink
 */
final class OPT_SSATuneUp extends OPT_OptimizationPlanCompositeElement {

    /**
   * Build this phase as a composite of others.
   */
    OPT_SSATuneUp() {
        super("SSA Tune Up", new OPT_OptimizationPlanElement[] { new OPT_OptimizationPlanAtomicElement(new TuneUpPreparation()), new OPT_OptimizationPlanAtomicElement(new OPT_EnterSSA()), new OPT_OptimizationPlanAtomicElement(new OPT_Simple(true, true, false)), new OPT_OptimizationPlanAtomicElement(new FoldingDriver()) });
    }

    boolean shouldPerform(OPT_Options options) {
        return options.SSA;
    }

    /**
   * This class drives expression folding.
   */
    private static class FoldingDriver extends OPT_CompilerPhase {

        final boolean shouldPerform(OPT_Options options) {
            return options.SSA && options.EXPRESSION_FOLDING;
        }

        final String getName() {
            return "SSA Expression Folding";
        }

        final boolean printingEnabled(OPT_Options options, boolean before) {
            return false;
        }

        /**
     * Execute expression folding. 
     */
        public final void perform(OPT_IR ir) {
            OPT_DefUse.computeDU(ir);
            OPT_ExpressionFolding.perform(ir);
        }
    }

    /**
   * This class sets up the IR state prior to entering SSA.
   */
    private static class TuneUpPreparation extends OPT_CompilerPhase {

        final boolean shouldPerform(OPT_Options options) {
            return options.SSA;
        }

        final String getName() {
            return "SSA Tune UpPreparation";
        }

        final boolean printingEnabled(OPT_Options options, boolean before) {
            return false;
        }

        /**
     * register in the IR the SSA properties we need for simple scalar
     * optimizations
     */
        public final void perform(OPT_IR ir) {
            ir.desiredSSAOptions = new OPT_SSAOptions();
            ir.desiredSSAOptions.setScalarsOnly(true);
            ir.desiredSSAOptions.setBackwards(false);
            ir.desiredSSAOptions.setInsertUsePhis(false);
        }
    }
}
