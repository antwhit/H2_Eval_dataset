public class test {
	public int foo() {
		int i = 0;
		announce testEvent() {
			i = 20;
			announce testEvent2(i) {
				i = 5;
			};
		};
		return i;
	}
}
