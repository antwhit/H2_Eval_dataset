import java.awt.Dimension;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.io.File;

public class JLabelFile extends JLabel {

    private String text;

    private String fileLocation;

    private boolean selected;

    private int position;

    static final Color evenLine = new Color(255, 255, 255);

    static final Color oddLine = new Color(240, 240, 240);

    static final Color highlighted = new Color(160, 160, 160);

    static final int labelHeight = 16;

    public JLabelFile() {
        super();
        this.selected = false;
        this.position = 0;
    }

    public JLabelFile(String text) {
        super(text);
        this.text = text;
        this.selected = false;
        this.position = 0;
    }

    public JLabelFile(String text, int position) {
        super(text);
        this.text = text;
        this.selected = false;
        this.position = position;
        reColor();
    }

    public JLabelFile(String text, int position, String fileLocation) {
        super(text);
        this.text = text;
        this.selected = false;
        this.position = position;
        reColor();
        this.setFileLocation(fileLocation);
    }

    public JLabelFile(ImageIcon icon, String text, int position, String fileLocation) {
        super(text, icon, JLabel.LEFT);
        this.text = text;
        this.selected = false;
        this.position = position;
        reColor();
        this.setFileLocation(fileLocation);
    }

    public void select() {
        if (selected == false) {
            selected = true;
            this.setOpaque(true);
            this.setBackground(highlighted);
            System.out.println("JLableFile Selected " + fileLocation);
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void deselect() {
        selected = false;
        reColor();
    }

    public void reColor() {
        this.setOpaque(true);
        if (selected == true) {
            this.setBackground(highlighted);
        } else if ((this.position % 2) == 0) {
            this.setBackground(evenLine);
        } else {
            this.setBackground(oddLine);
        }
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return this.fileLocation;
    }

    public String getFullLocation() {
        String fullPath = new String();
        if (fileLocation.endsWith(text)) {
            return fileLocation;
        } else if (fileLocation.endsWith(File.separator)) {
            fullPath = fileLocation + text;
        } else {
            System.out.println("JLableFile adding extra File.separator");
            fullPath = fileLocation + File.separator + text;
        }
        return fullPath;
    }

    public void setWidth(int width) {
        setMinimumSize(new Dimension(width, labelHeight));
        setMaximumSize(new Dimension(width, labelHeight));
        setPreferredSize(new Dimension(width, labelHeight));
    }

    public int getIndex() {
        return position;
    }

    public void paint(Graphics g) {
        super.paint(g);
        validate();
    }
}
