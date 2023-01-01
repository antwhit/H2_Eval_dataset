import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import iwork.patchpanel.*;

public class MappingDelegate extends JPanel implements Selection {

    MappingModel model;

    JList mappingList;

    public void setSelection(int i) {
        mappingList.getSelectionModel().setSelectionInterval(i, i);
    }

    public int getSelection() {
        return mappingList.getSelectionModel().getAnchorSelectionIndex();
    }

    public void addSelectionListener(ListSelectionListener lsl) {
        mappingList.getSelectionModel().addListSelectionListener(lsl);
    }

    MappingDelegate(MappingModel mm) {
        super(new BorderLayout());
        model = mm;
        mappingList = new JList(new MappingListModel(model));
        mappingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane mappingListPane = new JScrollPane(mappingList);
        ButtonPanel buttons = new ButtonPanel();
        buttons.newButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.add(new Mapping());
            }
        });
        buttons.copyButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int i = getSelection();
                model.copy(i);
            }
        });
        buttons.delButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int i = getSelection();
                if (i != 0) {
                    setSelection(i - 1);
                }
                model.remove(i);
            }
        });
        this.add(mappingListPane, BorderLayout.CENTER);
    }
}
