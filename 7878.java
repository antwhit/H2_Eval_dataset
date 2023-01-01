/***************************
 * Third Example (Order of refine and invoke statement changed)
 * It is a test of how order is taken care of while doing refinement
 * 
 * This test should pass without any problem
 */

Point event Changed{
	Point p;
	provides p!=null
	requires{
		preserves p==old(p);
		invoke(next);
	}
}

class Point extends Object{
    int x;
	Point setX(int x){
		announce Changed(this)
		{
			this.x = x;
		} 
		return this;
	}
}

class Update extends Object{
	when Changed do update;
	Display d;
	Point update(Point rest, Point p){
		refining preserves p==old(p){
			d.update();
		}
		return invoke(rest);
	}
}

class Display extends Object{
	void update(){}
}

