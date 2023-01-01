import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import model.DemoTableModel;
import renderer.ColorRenderer;
import renderer.IconRenderer;
import renderer.PercentageRenderer;
import renderer.SimpleColumnBackgroundRenderer;
import renderer.SpinnerRenderer;

/**
 * @Description
 * @Author zhangzuoqiang
 * @Date 2012-1-8 | 下午8:10:33
 */
public class SpinnerRendererDemo extends JFrame {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {
        UIManager.put("Table.alternateRowColor", new Color(0xF3FFFF));
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SpinnerRendererDemo();
            }
        });
    }

    public SpinnerRendererDemo() {
        JTable table = new JTable(new DemoTableModel(100));
        TableCellRenderer defaultRenderer = table.getDefaultRenderer(Object.class);
        TableCellRenderer backgroundRenderer = new SimpleColumnBackgroundRenderer(defaultRenderer);
        TableCellRenderer percentageRenderer = new PercentageRenderer(backgroundRenderer);
        TableCellRenderer iconRenderer = new IconRenderer(percentageRenderer);
        table.setDefaultRenderer(Double.class, iconRenderer);
        TableCellRenderer colorRenderer = new ColorRenderer(backgroundRenderer);
        table.setDefaultRenderer(Color.class, colorRenderer);
        TableCellRenderer spinnerRenderer = new SpinnerRenderer(backgroundRenderer);
        table.setDefaultRenderer(Float.class, spinnerRenderer);
        add(new JScrollPane(table));
        setTitle(this.getClass().getSimpleName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
