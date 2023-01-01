public class test {
	public int foo() {
		int i = 0;
		announce testEvent() {
			i = 5000;
		    System.out.println("In event body!");
		}
		return i;
	}
}
