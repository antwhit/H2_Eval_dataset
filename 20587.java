public class Point{
	int x=0;
	
	Object setX(int x){
		announce Changed(this){
			this.x = x;
			return this;
		}
	}
}