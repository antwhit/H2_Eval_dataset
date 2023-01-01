public class Handler2{
	
	static int sequentialThreshold = 13;
	
	public Handler2(){register(this);}

	
	public void compute(Changed rest, Fib f1, Fib f2){
		int number = f2.number;
		if (number == 1) {
			f2.result = 1;
			return;
		}
		if ( number <= sequentialThreshold ) {
			f2.result = seqFib(number);
			return;
		}
			
		Fib f3 = new Fib(number - 1);
		Fib f4 = new Fib(number - 2);
		announce Changed(f3, f4);
		
		f2.result = f3.result + f4.result;
	}
	
	static long seqFib(int n) {
		if (n <= 1) return n;
		else return seqFib(n-1) + seqFib(n-2);
	}
	
	when Changed do compute;
}