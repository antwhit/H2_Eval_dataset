public class Line implements FigureElement {
	Point p1;
	Point p2;

	public Line(Point p1, Point p2) { 
		this.p1 = p1; this.p2 = p2; 
	}

	public Point getP1() { return p1; }
	public Point getP2() { return p2; }
	public void setP1(Point p) { this.p1 = p; }
	public void setP2(Point p) { this.p2 = p; } 

	public void draw() {
		System.out.println("Line between ");
		p1.draw();
		System.out.println(" and ");
		p2.draw();
	}
	public void moveBy(int dx, int dy){
		System.out.println("Before event FEChanged in Line.moveBy");
		     
		announce FEChanged(this) {	
			System.out.println("    Inside event FEChanged's body in Line.moveBy");
			p1.moveBy(dx, dy);
			p2.moveBy(dx, dy);
		}
		System.out.println("After event FEChanged in Line.moveBy");
	}
}
