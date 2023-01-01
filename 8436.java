import java.util.ArrayList;
import java.util.Hashtable;
public class Announce9{
	public void foo(){
		ArrayList list = new ArrayList();
		announce Event4(list){
			
		}
	}
	
	//Bind the wrong type into the event announcement.
	public void foo1(){
		Hashtable ht = new Hashtable();
		announce Event4(ht){}
	}
}