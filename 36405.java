/***************************
 * First Example
 **/

Point event Changed{
 Point p;
 Display d;
 
 provides p!=null
 requires{
  invoke(next);
  d.update();
 }
}


class Point{
    int x;
    
  void setX(int x){
    announce Changed(this)
    {
      this.x = x; 
    }
   
  }
}

public class Update{
 when Changed do update;
  
 Display d;
 
 public void update(thunk Point rest, Point p){
   invoke(rest);
   d.update();
  }
 
}

public class Display extends Object{
  public void update(){}
}




