import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ComponentInfo {

    String name;

    String disp_name;

    String data;

    Component comp;

    String[] pins;

    Point location;

    ComponentInfo(String name, String disp_name, String data, String[] pins) {
        this.name = name;
        this.disp_name = disp_name;
        this.data = data;
        this.pins = pins;
    }

    ComponentInfo(String name, String data) {
        this.name = name;
        this.data = data;
    }

    ComponentInfo(Project project, String name, String data) {
        this.name = name;
        this.data = data;
        try {
            this.comp = (Component) Class.forName(name).newInstance();
            this.comp.init(project, data);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(Workbench.mainframe, "Couldn't find " + name + ".class", "Class not found", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        }
        disp_name = comp.getName();
        pins = comp.getPinNames();
    }

    public String getName() {
        return name;
    }

    public String getDispName() {
        return disp_name;
    }

    public String[] getPins() {
        return pins;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
