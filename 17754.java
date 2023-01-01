import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.beans.*;
import javax.swing.table.*;
import com.sun.image.codec.jpeg.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.awt.datatransfer.*;

public class GApplicationState implements Serializable {

    private transient GUI gui;

    private transient File stateFile;

    private transient HashMap<String, Serializable> map;

    public GApplicationState(GUI gui, File stateFile) {
        this.gui = gui;
        this.stateFile = stateFile;
        map = new HashMap<String, Serializable>();
        if (!stateFile.exists()) {
            try {
                stateFile.createNewFile();
            } catch (Exception e) {
            }
        }
    }

    public GApplicationState(GUI gui, String stateFile) {
        this(gui, new File(stateFile));
    }

    public void update() {
        map.clear();
        map.put("windowbounds", gui.getBounds());
        int paneCount = gui.getTabbedPane().getTabCount();
        map.put("gpanes", new Integer(paneCount));
        for (int i = 0; i < paneCount; i++) {
            GPane pane = (GPane) gui.getTabbedPane().getComponentAt(i);
            map.put("center[" + i + "]", pane.getCenter());
            map.put("zoom[" + i + "]", new Integer(pane.getZoom()));
            map.put("title[" + i + "]", gui.getTabbedPane().getTitleAt(i));
        }
    }

    public void set() {
        gui.getTabbedPane().removeAll();
        Rectangle windowBounds = (Rectangle) map.get("windowbounds");
        if (windowBounds != null) {
            gui.setBounds(windowBounds);
        }
        Integer paneCountInteger = (Integer) map.get("gpanes");
        if (paneCountInteger == null) return;
        int paneCount = paneCountInteger.intValue();
        for (int i = 0; i < paneCount; i++) {
            GPane pane = new GPane(gui);
            String title = (String) map.get("title[" + i + "]");
            if (title == null) continue;
            gui.getTabbedPane().add(pane, title);
            GPhysicalPoint center = (GPhysicalPoint) map.get("center[" + i + "]");
            if (center == null) continue;
            pane.setCenter(center);
            Integer zoomInteger = (Integer) map.get("zoom[" + i + "]");
            if (zoomInteger == null) continue;
            int zoom = zoomInteger.intValue();
            pane.setZoom(zoom);
        }
    }

    public void restoreState() {
        HashMapWrapper wrapper = (HashMapWrapper) LibGUI.openStateFromFile(stateFile);
        map = wrapper.wrappedMap;
        set();
    }

    public void saveState() {
        update();
        HashMapWrapper wrapper = new HashMapWrapper();
        wrapper.wrappedMap = map;
        LibGUI.saveStateToFile(stateFile, wrapper);
    }

    class HashMapWrapper implements Serializable {

        HashMap<String, Serializable> wrappedMap;
    }
}
