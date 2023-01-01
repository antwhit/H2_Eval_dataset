/**
 * Iterator for stack frames inserted by hardware trap handler.
 * Such frames are purely used as markers.
 * They contain no object references or JSR return addresses.
 *
 * @author Derek Lieber
 * @date 02 Jun 1999 
 */
final class VM_HardwareTrapGCMapIterator extends VM_GCMapIterator {

    VM_HardwareTrapGCMapIterator(int[] registerLocations) {
        this.registerLocations = registerLocations;
    }

    void setupIterator(VM_CompiledMethod compiledMethod, int instructionOffset, int framePtr) {
        this.framePtr = framePtr;
    }

    int getNextReferenceAddress() {
        int registerLocation = VM_Magic.objectAsAddress(thread.hardwareExceptionRegisters.gprs);
        for (int i = 0; i < VM_Constants.NUM_GPRS; ++i) {
            registerLocations[i] = registerLocation;
            registerLocation += 4;
        }
        return 0;
    }

    int getNextReturnAddressAddress() {
        return 0;
    }

    void reset() {
    }

    void cleanupPointers() {
    }

    int getType() {
        return VM_GCMapIterator.TRAP;
    }
}
