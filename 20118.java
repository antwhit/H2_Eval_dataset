abstract class Tile{

	/*
	Pit = O
	Pole = ¤
	Liquid = ~
	Wall = #
	
	Walkable = '  '
	Door = D
	Stairs down = v
	Stairs up = ^
	
	*/
	protected char Form fm;
	protected int x;
	protected int y;
	
	public Tile(Form fm, int x, int y){
		this.fm=fm; this.x=x; this.y=y;
	}
	
	public char getForm(){ return fm; }
	public char getX(){ return x; }
	public char getY(){ return y; }
	
	public char setForm(char f){ fm=f; }
	public char setX(int c){ x=c; }
	public char setY(int c){ y=c; }
	
}