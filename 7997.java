Object event Event2{
	
}

public class TestThunk{
    
    public void m1(Event2 myThunk){
	System.out.println("Method with thunk parameter");
    }
    
    public void m2(Object o){
	System.out.println("Method without thunk parameter");
    }
    
}