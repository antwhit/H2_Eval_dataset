/**
 * Object Layout Conventions
 *
 * @author Bowen Alpern
 * @author Derek Lieber
 */
interface VM_ObjectLayoutConstants {

    static final int OBJECT_HEADER_OFFSET = (VM.BuildWithRedirectSlot ? -16 : (VM.BuildForConcurrentGC ? -16 : (VM.CompileForGCTracing ? -24 : -12)));

    static final int OBJECT_TIB_OFFSET = (VM.BuildWithRedirectSlot ? -16 : (VM.BuildForConcurrentGC ? -16 : (VM.CompileForGCTracing ? -24 : -12)));

    static final int OBJECT_STATUS_OFFSET = (VM.BuildWithRedirectSlot ? -12 : (VM.BuildForConcurrentGC ? -12 : (VM.CompileForGCTracing ? -20 : -8)));

    static final int OBJECT_REFCOUNT_OFFSET = (VM.BuildForConcurrentGC ? -8 : 0);

    static final int OBJECT_REDIRECT_OFFSET = (VM.BuildWithRedirectSlot ? -8 : 0);

    static final int OBJECT_GC_BYTE_OFFSET = (VM.BuildWithRedirectSlot ? -9 : (VM.BuildForConcurrentGC ? -9 : (VM.CompileForGCTracing ? -17 : -5)));

    static final int OBJECT_OID_OFFSET = (VM.CompileForGCTracing ? -12 : 0);

    static final int OBJECT_LINK_OFFSET = (VM.CompileForGCTracing ? -16 : 0);

    static final int OBJECT_DEATH_OFFSET = (VM.CompileForGCTracing ? -8 : 0);

    static final int TRACE_HEADER_EXTRA = (VM.CompileForGCTracing ? 12 : 0);

    static final int SCALAR_HEADER_SIZE = (VM.BuildWithRedirectSlot ? 12 : (VM.BuildForConcurrentGC ? 12 : (VM.CompileForGCTracing ? 20 : 8)));

    static final int ARRAY_HEADER_SIZE = (VM.BuildWithRedirectSlot ? 16 : (VM.BuildForConcurrentGC ? 16 : (VM.CompileForGCTracing ? 24 : 12)));

    static final int ARRAY_LENGTH_OFFSET = -4;

    static final int ARRAY_ELEMENT_OFFSET = 0;

    public static final int NEEDS_DYNAMIC_LINK = 0;

    static final int OBJECT_FAT_LOCK_MASK = 0x80000000;

    static final int OBJECT_LOCK_ID_MASK = 0x7ffff000;

    static final int OBJECT_THREAD_ID_MASK = 0x7ffc0000;

    static final int OBJECT_LOCK_COUNT_MASK = 0x0003f000;

    static final int OBJECT_HASHCODE_MASK = 0x000003fc;

    static final int OBJECT_BARRIER_MASK = 0x00000002;

    static final int OBJECT_GC_MARK_MASK = 0x00000001;

    static final int OBJECT_UNUSED_MASK = 0x00000c00;

    static final int OBJECT_UNLOCK_MASK = ~(OBJECT_FAT_LOCK_MASK | OBJECT_LOCK_ID_MASK);

    static final int OBJECT_HASHCODE_MARK_MASK = (OBJECT_HASHCODE_MASK | OBJECT_GC_MARK_MASK);

    static final int OBJECT_HASHCODE_UNIT = 4;

    static final int OBJECT_LOCK_COUNT_SHIFT = 12;

    static final int OBJECT_LOCK_COUNT_UNIT = 0x00001000;

    static final int OBJECT_THREAD_ID_SHIFT = 18;

    static final int OBJECT_LOCK_ID_SHIFT = 12;

    static final int IMT_METHOD_SLOTS = VM.BuildForIMTInterfaceInvocation ? 29 : 0;

    static final int TIB_INTERFACE_METHOD_SLOTS = VM.BuildForEmbeddedIMT ? IMT_METHOD_SLOTS : 0;

    static final int TIB_TYPE_INDEX = 0;

    static final int TIB_SUPERCLASS_IDS_INDEX = TIB_TYPE_INDEX + (VM.BuildForFastDynamicTypeCheck ? 1 : 0);

    static final int TIB_IMPLEMENTS_TRITS_INDEX = TIB_SUPERCLASS_IDS_INDEX + (VM.BuildForFastDynamicTypeCheck ? 1 : 0);

    static final int TIB_ARRAY_ELEMENT_TIB_INDEX = TIB_IMPLEMENTS_TRITS_INDEX + (VM.BuildForFastDynamicTypeCheck ? 1 : 0);

    static final int TIB_TYPE_CACHE_TIB_INDEX = TIB_ARRAY_ELEMENT_TIB_INDEX + (!VM.BuildForFastDynamicTypeCheck ? 1 : 0);

    static final int TIB_ITABLES_TIB_INDEX = TIB_TYPE_CACHE_TIB_INDEX + (VM.BuildForITableInterfaceInvocation ? 1 : 0);

    static final int TIB_IMT_TIB_INDEX = TIB_ITABLES_TIB_INDEX + (VM.BuildForIndirectIMT ? 1 : 0);

    static final int TIB_FIRST_INTERFACE_METHOD_INDEX = TIB_IMT_TIB_INDEX + 1;

    static final int TIB_FIRST_VIRTUAL_METHOD_INDEX = TIB_FIRST_INTERFACE_METHOD_INDEX + TIB_INTERFACE_METHOD_SLOTS;

    static final int UNRESOLVED_INTERFACE_METHOD_OFFSET = -1;
}
