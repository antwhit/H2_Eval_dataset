public class Point implements FigureElement {
	int x;
	int y;
	public Point(int x, int y) { this.x = x; this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int newX) {
		
		announce FEChanged(this) {
			x = newX;
		}
	}
	public void setY(int newY) {
		 
		announce FEChanged(this) {
			y = newY;
		}
	}

	public void draw(){ 
		System.out.println("Point (" + this.x + "," +   
				this.y + ")");	
	}

	public void moveBy(int dx, int dy){
		System.out.println("Before event FEChanged in Point.moveBy");
		     
		announce FEChanged(this) {	
			System.out.println("    Inside event FEChanged's body in Point.moveBy");
			x += dx;
			y += dy;
		}
		
		System.out.println("After event FEChanged in Point.moveBy");
	}

	public void makeEqual(Point other) {
		
		announce FEChanged(this) {
			other.x = x;
			other.y = y;
		}
	}
}
