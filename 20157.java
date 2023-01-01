import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JMenuItem1 extends JFrame{

	JTextArea theArea = null;

	public JMenuItem1(){
	  
	  super("JMenuItem1");
	  theArea = new JTextArea();
	  theArea.setEditable(false);
	  getContentPane().add(new JScrollPane(theArea));
	  JMenuBar MBar = new JMenuBar();
	  MBar.setOpaque(true);
	  
	  JMenu mfile = buildFileMenu();

	  MBar.add(mfile);  
	  setJMenuBar(MBar);
	}//end of JMenuItem1()

	public JMenu buildFileMenu() {
  	
	  JMenu thefile = new JMenu("File");

	  JMenuItem newf = new JMenuItem("New");
	  JMenuItem open = new JMenuItem("Open");
	  JMenuItem close= new JMenuItem("Close");    
	  JMenuItem quit = new JMenuItem("Exit");

	  thefile.add(newf);	  	
	  thefile.add(open);
	  thefile.add(close);        
	  thefile.addSeparator();
	  thefile.add(quit);

	  return thefile;
	}//end of buildFileMenu()
	
	public static void main(String[] args){

	  JFrame F = new JMenuItem1();
	  F.setSize(400,200);
	  F.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
	      System.exit(0); 
	    }
	  });//end of addWindowListener
	  F.setVisible(true); 
	} // end of main
}//end of class JMenuItem1