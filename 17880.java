import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JComboBox5
{
    String[] s = {"西瓜","苹果","草莓","香蕉","葡萄"};
    ImageIcon[] icons = new ImageIcon[5];;
    
    public JComboBox5()
    {
        JFrame f = new JFrame("JComboBox");
        Container contentPane = f.getContentPane();
        ItemObj[] obj = new ItemObj[5];
        
        for(int i=0; i < 5; i++)
        {
            icons[i] = new ImageIcon(".\\icons\\fruit"+(i+1)+".jpg");
            obj[i] = new ItemObj(s[i],icons[i]);
        }
        
        JComboBox combo = new JComboBox(obj);
        combo.setBorder(BorderFactory.createTitledBorder("您喜欢吃哪些水果？"));
        combo.setRenderer(new ACellRenderer1());
        combo.setMaximumRowCount(3);
        
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
        new JComboBox5();
    }
}

class ItemObj
{
    String name;
    ImageIcon icon;
    
    public ItemObj(String name, ImageIcon icon){
        this.name = name;
        this.icon = icon;
    }
}
    
class ACellRenderer1 extends JLabel implements ListCellRenderer
{
    ACellRenderer1()
    {
        setOpaque(true);
    }
    
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {
        if (value != null)
        {
            setText(((ItemObj)value).name);
            setIcon(((ItemObj)value).icon);
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }    
}

