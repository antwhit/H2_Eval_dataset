public class testhandler {
	public void init() {
		register(this);
	}

	public void handler(testEvent next) throws Throwable {
		invoke(next);
		System.out.println("hi");
	}

	when testEvent do handler;
}
