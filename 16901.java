import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.application.Action;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.PropertyStateListener;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class BeansBinding extends CustomDockable {

    private final class Mousie extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JPopupMenu jm = createMenu();
            jm.show((JComponent) e.getSource(), e.getX(), e.getY());
        }
    }

    private List<Fruit> al;

    private JTable jt;

    JTableBinding jt2;

    public BeansBinding() {
        super("Bind");
        al = ObservableCollections.observableList(new ArrayList<Fruit>());
        jt = new JTable();
        jt2 = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, al, jt);
        jt2.addColumnBinding(BeanProperty.create("number"));
        jt2.addColumnBinding(BeanProperty.create("fp"));
        jt2.bind();
        p(jt2.getColumnBindings().size());
        addFruit();
        jt.setFillsViewportHeight(true);
        content.add(new JScrollPane(jt));
        content.addMouseListener(new Mousie());
        jt.addMouseListener(new Mousie());
        injectStuff();
        addActions();
        new Thread(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(2000);
                    p(jt.getColumnName(0));
                    jt.getColumn("org.jdesktop.beansbinding.BeanProperty[fp]").setCellRenderer(new TestRenderer());
                    jt.updateUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                al.get(0).setNumber(2134);
                p("Now");
            }
        }).start();
    }

    @Action
    public void addFruit() {
        al.add(new Fruit());
    }
}

class TestRenderer extends JComponent implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (JComponent) value;
    }

    public synchronized void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
