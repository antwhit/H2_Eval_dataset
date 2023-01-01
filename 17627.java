import edu.iastate.cs.ptolemy.runtime.*;

public class testhandler {

    @Register
    public void init() {
    }

    @When(testEvent.class)
    public int handler(testEvent eventBody) throws Throwable {
        invoke(eventBody);
        int i = 0;
        System.out.println("hi");
        return i + 2;
    }
}
