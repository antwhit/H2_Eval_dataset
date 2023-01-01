import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class JComboBox2
{
    String[] s = {"美国","日本","大陆","英国","法国","义大利","澳洲","韩国"};
    
    public JComboBox2()
    {
        JFrame f = new JFrame("JComboBox");
        Container contentPane = f.getContentPane();
        
        ComboBoxModel mode = new UserDefineComboBoxModel();
        JComboBox combo = new JComboBox(mode);
        combo.setBorder(BorderFactory.createTitledBorder("您最喜欢到哪个国家玩呢？"));
        
        contentPane.add(combo);
        f.pack();
        f.show();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    System.exit(0);
            }
        });
    }
    
    public static void main(String args[])
    {
        new JComboBox2();
    }
    
    class UserDefineComboBoxModel extends AbstractListModel implements ComboBoxModel
    {
        String item = null;
        
        public Object getElementAt(int index)
        {
            return s[index++];
        }
        
        public int getSize()
        {
            return s.length;
        }
        
        public void setSelectedItem(Object anItem)
        {
            item = (String)anItem;
        }
        
        public Object getSelectedItem()
        {
            return item;
        }
    }
}


