public class testhandler2 {
	public void init() {
		register(this);
	}

	public void handler(testEvent2 eventBody) throws Throwable {
		invoke(eventBody);
		System.out.println("hi");
	}

	when testEvent2 do handler;
}
