Object event Event1{
	
}

public class Register1{
	
	public void m(){
		register(this);
	}
	
	public Object handler(Event1 next) throws Throwable {
        return null;
    }
    
    when Event1 do handler;
}