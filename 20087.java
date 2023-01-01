/**
 * This class encodes an elementary quantum gate. It is the central class for the design of
 * quantum circuits and is used to determine the sequence of quantum operations.
 *
 * @author  Andreas de Vries
 * @version $Id: QuantumGate.java,v 1.1 2004-06-04 20:54:23 vriesa Exp $
 */
public class QuantumGate implements java.io.Serializable {

    /** specifies the name of the quantum gate. It is uniquely related to a quantum operation.*/
    String name;

    /** specifies the index of qubits on which the gate operates. 
     *  For a controlled gate, the last entry always is the target qubit.
     */
    int[] qubits;

    /** specifies if the gate operates on the y-register. If not, the x-register is acted on.*/
    boolean yRegister;

    /** the parsed function for the function evaluation gate U_f. It is null for all other gates.*/
    FunctionParser function = null;

    /** axis of rotation.*/
    String axis;

    /** rotation angle, as the integral part of pi. More accurately: angle = pi/phi.*/
    int phiAsPartOfPi;

    /** Standard constructor.*/
    public QuantumGate(String name, int[] qubits, boolean yRegister) {
        this.name = name;
        this.qubits = qubits;
        this.yRegister = yRegister;
    }

    /** Constructor for a quantum gate evaluating a function.*/
    public QuantumGate(String name, int[] qubits, FunctionParser function, boolean yRegister) {
        this.name = name;
        this.qubits = qubits;
        this.yRegister = yRegister;
        this.function = function;
    }

    /** Constructor for a rotation operation.*/
    public QuantumGate(String name, int[] qubits, String axis, int phiAsPartOfPi, boolean yRegisterChosen) {
        this.name = name;
        this.qubits = qubits;
        this.axis = axis;
        this.phiAsPartOfPi = phiAsPartOfPi;
    }
}
