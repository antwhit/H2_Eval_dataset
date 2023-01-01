/**
 * @author Dick Attanasio
 * @author Stephen Smith
 */
interface VM_GCConstants {

    static final int WORDSIZE = 4;

    static final int LG_WORDSIZE = 2;

    static final int GC_SIZES = 12;

    static final int[] GC_SIZEVALUES = { 12, 16, 20, 32, 64, 84, 128, 256, 512, 524, 1024, 2048 };

    static final int GC_MAX_SMALL_SIZE = 2048;

    static final int GC_BLOCKSIZE = 16 * 1024;

    static final int LOG_GC_BLOCKSIZE = 14;

    static final int GC_BLOCKALIGNMENT = GC_BLOCKSIZE;

    static final int GC_STEPS = 2;

    static final int GC_OLD = GC_STEPS - 1;

    static final int GC_THRESHHOLD = 2 * GC_OLD - 1;

    static final int GC_REMEMBERED_COHORTS = GC_OLD;

    static final int GC_INITIALOLDOBJECTS = 1024;

    static final int GC_NEW_BLOCK_DEPTH = 10;

    static final int GC_INITIAL_LARGE_SPACE_PAGES = 100;

    static final int GC_LARGE_SIZES = 20;
}
