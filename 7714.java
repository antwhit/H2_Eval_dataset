import pkg1.E3;

public class Client{
    
    public Client(){
        //register(this);
    }
    
    public void m(E1 rest){}
    
    when E1 do m;
    
    public void foo(){
        announce E2();
    }

	public void bar(){
		announce E2();
	}

	public void baz(){
		announce E2();
		announce E1();
		announce E3("Hello from baz");
	}
	
	public void wooly(){
	    announce pkg1.E3("Hello from wooly");
	}
}
