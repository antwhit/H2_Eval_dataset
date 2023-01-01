import edu.iastate.cs.ptolemy.runtime.*;

public class testhandler {

    @Register
    public void init() {
    }

    @When(testEvent.class)
    public void handler(testEvent eventBody) throws Throwable {
        invoke(eventBody);
        System.out.println("hi");
    }
}
