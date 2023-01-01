
public class UsesAwait {
	
	String x = new String("10");
	Integer y = new Integer("20");
	
	//Cannot use @await on constructors
	@await(pre="")
	public UsesAwait() {
		
	}
	
	//This is the proper way to use @await
	@await(pre="x.equals(\"10\")")
	public void properAwait() {
		
	}
	
	//This is the proper use of @await but an invalid precondition
	@await(pre="Some random string")
	public void strangeAwait() {
	
	}
	
	//Cannot use @await with an assert
	public void incorrectAssert() {
		@await assert(x.equals("10"));
	}

	//Cannot use @await with things inside a method
	public void incorrectAwait() {
		@await(pre="y.intValue()==20")
		for(int i = 0; i < 5; i++) {
			
		}
	}
}
