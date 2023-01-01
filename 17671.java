/**
 * This interface holds constants for the Opt GC map code specific to PowerPC
 *
 * @author Michael Hind
 */
interface VM_OptGCMapIteratorConstants extends OPT_PhysicalRegisterConstants {

    static final int FIRST_GCMAP_REG = 1;

    static final int LAST_GCMAP_REG = NUM_GPRS - 1;
}
