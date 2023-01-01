class VM_BlockControl implements VM_Constants {

    static final int Size = 44 + SCALAR_HEADER_SIZE;

    int baseAddr;

    int slotsize;

    byte[] mark;

    byte[] alloc;

    int nextblock;

    byte[] Alloc1;

    byte[] Alloc2;

    boolean live;

    boolean sticky;

    int alloc_size;

    int allocCount;
}
