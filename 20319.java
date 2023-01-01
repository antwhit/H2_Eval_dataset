import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JTextArea2{

    public static void main(String args[])    {

    	JFrame f = new JFrame("JTextArea2");
    	Container contentPane = f.getContentPane();
    	contentPane.setLayout(new BorderLayout());

    	JPanel p1 = new JPanel();        
    	p1.setLayout(new GridLayout(1,1));        
    	p1.setBorder(BorderFactory.createTitledBorder("建构TextArea -使用GridLayout,加ScrollBar"));         
      
    	JTextArea t1 = new JTextArea(5,25);
    	t1.setTabSize(10);
    	t1.setFont(new Font("宋体", Font.BOLD, 16));
    	t1.setLineWrap(true);
    	t1.setWrapStyleWord(true);
       
    	p1.add(new JScrollPane(t1));
    	
    	contentPane.add(p1);        
    	f.pack();        
    	f.show();        
    	f.addWindowListener(new WindowAdapter() {            
    		public void windowClosing(WindowEvent e) {                    
    			System.exit(0);            
    		}        
    	});    
    }
}

