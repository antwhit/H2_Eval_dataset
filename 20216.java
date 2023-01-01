public class WSDLOperation implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String operationName = null;

    private String operationInput = null;

    private String operationOutput = null;

    public WSDLOperation() {
        super();
    }

    public void setOperationName(String operation) {
        operationName = operation;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationInput(String input) {
        operationInput = input;
    }

    public String getOperationInput() {
        return operationInput;
    }

    public void setOperationOutput(String output) {
        operationOutput = output;
    }

    public String getOperationOutput() {
        return operationOutput;
    }
}
