public class testhandler {
	public void init() {
		register(this);
	}

	public void handler(testEvent eventBody) throws Throwable {
		invoke(eventBody);
		System.out.println("hi");
		//return 2;
	}

	when testEvent do handler;
}
