public class test
{
	public static void main(String[] args) {
		new testHandler();
		new test().foo();
	}
	
	public void foo() {
		announce testEvent1() { }
		announce testEvent2() { }
	}
}
