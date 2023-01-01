public class testhandler2 {
	public void init() {
		register(this);
	}

	public void handler(testEvent2 next) throws Throwable {
		invoke(next);
		System.out.println("hi");
	}

	when testEvent2 do handler;
}
