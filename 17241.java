public class Handler3{
	
	public Handler3(){ register(this); }
	
	public void compute(Changed rest, PascalTriangle f1, PascalTriangle f2, PascalTriangle f){
		f.result = f1.result + f2.result;
	}

	when Changed do compute;
}

