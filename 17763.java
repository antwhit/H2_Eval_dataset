public class test {
	public int foo() {
		int i = 0;
		announce testEvent() {
			i = 5000;
			announce testEvent2() {};
		};
		return i;
	}
}
