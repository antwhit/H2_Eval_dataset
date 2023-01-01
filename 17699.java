public class Handler2{
	static final int XsequentialThreshold = 15;
	
	public Handler2(){ register(this); }
	
	public void compute(Changed rest, PascalTriangle f1, PascalTriangle f2, PascalTriangle f){
		int x = f2.x;
		int y = f2.y;
		if( x <= XsequentialThreshold || y == 1 || y == x){
			f2.result = seq_pas_tri(x, y);
			return;
		}
		PascalTriangle t1 = new PascalTriangle( x-1, y-1 );
		PascalTriangle t2 = new PascalTriangle( x-1, y );

		announce Changed(t1, t2, f2);
	}

	when Changed do compute;

	static long seq_pas_tri (int x, int y)  {
		if( x == 1 ){
			return 1;
		}
		if( y == 1 || y == x ){
			return 1;
		}
		return seq_pas_tri(x-1, y-1) + seq_pas_tri(x-1, y); 
	}
}

