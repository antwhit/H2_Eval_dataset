public class test {
	public void foo() {
		announce testEvent1() {};
		announce testEvent2() {};
	}

    public static void main(String args[]) {
      test t = new test();
      testhandler th = new testhandler();
      t.foo();
      if(th.counter != 2) { 
        System.out.println("TEST FAILED"); 
        return;
      }
      System.out.println("TEST PASSED");
    }
}
