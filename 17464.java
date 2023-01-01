public class Handler1{

	public Handler1(){ register(this); }
	
	static double errorTolerance = Integrate.errorTolerance;
	
	public void compute(Changed rest, Integrate f1,	Integrate f2, Integrate f){
		double left = f1.left;
		double right = f1.right;
		double f_left = f1.f_left;
		double f_right = f1.f_right;
		double area = f1.area;
		Function fun = f1.fun;
			
		double center = 0.5 * (left + right);
		double f_center = fun.compute(center); 

		double leftArea  = 0.5 * (center - left)  * (f_left + f_center); 
		double rightArea = 0.5 * (right - center) * (f_center + f_right);
		double sum = leftArea + rightArea;

		double diff = sum - area;
		if (diff < 0) diff = -diff;
		f1.area =  sum;

		if (diff >= errorTolerance) { 
			Integrate q1 = new Integrate(left,   center, f_left,   f_center, leftArea, fun);
			Integrate q2 = new Integrate(center, right,  f_center, f_right,  rightArea, fun);

			announce Changed(q1, q2, f1);
		}
	}

	when Changed do compute;
}