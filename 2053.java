import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import java.util.*;

public class TitleBar extends JToolBar {

    JLabel titleLabel;

    public TitleBar(JDesktopPane desktop, JPanel parent, Server server) {
        super();
        setFloatable(false);
        ToolBarObject closeButton = new ToolBarObject(new ImageIcon(server.LoadResourceFile("close.png")));
        titleLabel = new JLabel();
        titleLabel.setOpaque(false);
        add(new Box.Filler(new Dimension(4, 0), new Dimension(4, 0), new Dimension(4, 0)));
        add(titleLabel);
        add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(Short.MAX_VALUE, 0)));
        add(closeButton);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}
