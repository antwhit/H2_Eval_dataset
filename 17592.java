import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class JEditorPane2{

	public static void main(String[] args){
		
		String str = new String("This is a test.\nThis is Line 2!\nThis is Line 3!");
		JEditorPane editPane = new JEditorPane("text/plain",str);
		editPane.setEditable(false);

		JFrame f = new JFrame("JEditorPane2");
		f.setContentPane(new JScrollPane(editPane));
		f.setSize(200,250);
    		f.show(); 
    		f.addWindowListener(new WindowAdapter() {            
    		  public void windowClosing(WindowEvent e) {                    
    		 	  System.exit(0);            
    		  }        
    		});    
	}//end of main()
}//end of class JEditPane2