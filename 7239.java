import edu.iastate.cs.ptolemy.runtime.*;

public class testhandler {

    @Register
    public testhandler() {
        counter = 0;
    }

    public int counter;

    @When(testEvent2.class)
    public void handler(testEvent2 eventBody) throws Throwable {
        invoke(eventBody);
        counter++;
    }
}
