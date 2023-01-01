import org.wings.SForm;
import org.wings.SFrame;
import org.wings.SGridLayout;
import org.wings.SList;
import org.wings.SButton;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HelloWingS {

    public HelloWingS() {
        SGridLayout gridLayout = new SGridLayout(1);
        SForm panel = new SForm(gridLayout);
        gridLayout.setVgap(10);
        final DefaultListModel model = new DefaultListModel();
        for (int i = 1; i <= 10; i++) {
            model.addElement(new Integer(i));
        }
        final SList list = new SList(model);
        list.setSelectionMode(SList.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {
                System.out.println("event value is adjusting = " + event.getValueIsAdjusting());
                int firstIndex = event.getFirstIndex();
                System.out.println("first index = " + firstIndex);
                int lastIndex = event.getLastIndex();
                System.out.println("last index = " + lastIndex);
                Object selected = model.get(lastIndex);
                System.out.println(selected);
            }
        });
        panel.add(list);
        panel.add(new SButton("submit"));
        SFrame rootFrame = new SFrame();
        rootFrame.getContentPane().add(panel);
        rootFrame.setVisible(true);
    }
}
