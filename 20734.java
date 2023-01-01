public class Handler2{
	static final int XsequentialThreshold = 15;
	
	public Handler2(){ register(this); }
	
	public void compute(Changed rest, EulerianNumber f1, EulerianNumber f2){
		int x = f2.x;
		int y = f2.y;
		if( x <= XsequentialThreshold || y == 1 || y == x){
			f2.result = seq_eul_num(x, y);
			return;
		}
		EulerianNumber t1 = new EulerianNumber( x-1, y-1 );
		EulerianNumber t2 = new EulerianNumber( x-1, y );

		announce Changed(t1, t2);
		f2.result = (x-y)*t1.result + (y+1)*t2.result;
	}

	when Changed do compute;

	static long seq_eul_num (int x, int y)  {
		if( x == 1 ){
			return 1;
		}
		if( y == 0 || y == x - 1 ){
			return 1;
		}
		return (x-y)*seq_eul_num(x-1, y-1) + (y+1)*seq_eul_num(x-1, y); 
	}
}

