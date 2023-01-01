import java.util.HashMap;
import java.util.Map;

/** 		CSE 560.
 *  		Lab 3: W11-560 machine Assembler
 * 			@author The Epsilon	
 * 			Last Modified: 02-03-2011 
 * 			The Machine Table holds all the instructions supported by the W11-560.
 **/
public class MachineTable implements IMachineTable {

    /**
	 * machineTable is used for the instruction of our W11-560.
	 */
    private Map<String, String> machineTable;

    /**
	 * Default constructor with the operations and opcodes.
	 */
    public MachineTable() {
        machineTable = new HashMap<String, String>();
        machineTable.put("LD", "0");
        machineTable.put("LDI", "1");
        machineTable.put("ST", "2");
        machineTable.put("ADD", "3");
        machineTable.put("SUB", "4");
        machineTable.put("MUL", "5");
        machineTable.put("DIV", "6");
        machineTable.put("OR", "7");
        machineTable.put("AND", "8");
        machineTable.put("SHL", "9");
        machineTable.put("SHR", "A");
        machineTable.put("IO", "B");
        machineTable.put("BR", "C");
        machineTable.put("BRZ", "D");
        machineTable.put("BRN", "E");
        machineTable.put("BRS", "F");
    }

    /**
	 * Checks if the passed argument is a defined instruction of the W11-560.
	 * @param inst instruction to be checked if it is defined
	 * @return true if the instruction is defined
	 */
    public final boolean isDefined(String inst) {
        return machineTable.containsKey(inst);
    }

    /**
	 * Return the opcode of the machine instruction.
	 * @param inst is the instruction for which the opcode is needed
	 * @return a string representing the opcode of the instruction
	 */
    public final String getOpCode(String inst) {
        return machineTable.get(inst);
    }
}
