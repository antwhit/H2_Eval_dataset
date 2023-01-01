import java.util.Scanner;

public class EulerianNumber{

	public static void main(String[] args) {
		new EulerianNumberRunner(Integer.parseInt(args[0]));
	}

	/**
	* Inner class to get around the pesky problem of not being able to announce events
	* from a static method.
	*/
	private static class  EulerianNumberRunner{

		public EulerianNumberRunner(int num){
			doRun( num, (num-1)/2 );
		}

		private void doRun(int x, int y){
			Handler1 h1 = new Handler1();
			Handler2 h2 = new Handler2();
		
			EulerianNumber f1 = new EulerianNumber( x-1, y-1 );
			EulerianNumber f2 = new EulerianNumber( x-1, y );
			EulerianNumber f = new EulerianNumber( x, y );
		
			announce Changed(f1, f2, f);
			System.out.println("EulerianNumber(" + x + "," + y + ")=" + f.result);	
		}
	}

	public final int x;
	public final int y;
	public long result;

	EulerianNumber(int x, int y) { 
		this.x = x;
		this.y = y;
	}
}

