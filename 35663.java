import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author Jason Colles, Seth Mielke
 * 
 * @modified 1-25-11
 * 
 */
public class OpSub extends Operation {

    public OpSub(Word[] register, Word[] memory, FileReader programIn, FileWriter programOut) {
        super(register, memory, programIn, programOut);
    }

    /**
	 * Subtracts the contents of memory address S(x) from register r and stores
	 * it in r.
	 * 
	 * @param pc
	 *            is the program counter, which holds the location of the next
	 *            instruction to be executed
	 * @param traceTable
	 *            is the table that holds the values of of registers and memory
	 *            before and after each instruction execution
	 * @return integer value of the program counter which indicates the location
	 *         of instruction to execute next.
	 * @throws IOException
	 */
    public int exec(int pc, Map<String, Word> traceTable) throws IOException {
        int r = memory[pc].r();
        int sx = memory[pc].s() + memory[pc].x();
        if (memory[pc].u1() == 0 && memory[pc].u2() == 0) {
            if (sx > 255 || sx < 0) {
                programOut.write('\n' + "Out of bounds error at memory location " + String.format("%02x", pc) + '\n');
                System.err.println("ERROR: Address Range Error. ");
                return pc + 1;
            } else {
                traceTable.put("Before: Register #" + Integer.toString(r), register[r].copy());
                traceTable.put("Before: Memory Location " + String.format("%02x", sx).toUpperCase(), memory[sx].copy());
                int result = register[r].get() - memory[sx].get();
                register[r].set(result << 12 >> 12);
                traceTable.put("After: Register #" + Integer.toString(r), register[r]);
                traceTable.put("After: Memory Location " + String.format("%02x", sx).toUpperCase(), memory[sx]);
                return pc + 1;
            }
        } else {
            programOut.write('\n' + "U1 or U2 not valid at " + String.format("%02x", pc) + " resulting in a no-op." + '\n');
            return pc + 1;
        }
    }
}
