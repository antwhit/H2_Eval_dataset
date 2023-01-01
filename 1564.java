public class testhandler {
    public testhandler() {
	counter = 0;
	register(this);
    }

    public int counter;

    public void handler(testEvent1 eventBody) throws Throwable {
    	invoke(eventBody);
	counter++;
    }
    when testEvent1 do handler;
}
