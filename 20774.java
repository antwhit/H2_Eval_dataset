import java.awt.*;
import java.awt.event.*;

class SettingsWin extends Panel implements ItemListener {

    CFclient Parent;

    Panel server_panel;

    Panel map_panel;

    Choice server_delay;

    Choice map_delay;

    public static void errmsg(String str) {
        System.out.println("ERROR:" + str);
    }

    public SettingsWin(CFclient parent) {
        super(new BorderLayout());
        Parent = parent;
        server_delay = new Choice();
        server_delay.add("5");
        server_delay.add("10");
        server_delay.add("15");
        server_delay.add("20");
        server_delay.add("25");
        server_delay.add("30");
        server_delay.add("35");
        server_delay.add("40");
        server_delay.addItemListener(this);
        server_delay.select(Parent.params.get("polldelay.server"));
        server_panel = new Panel();
        server_panel.add(new Label("Server Delay(ms)"));
        server_panel.add(server_delay);
        add(server_panel, "North");
        map_delay = new Choice();
        map_delay.add("5");
        map_delay.add("10");
        map_delay.add("15");
        map_delay.add("20");
        map_delay.add("25");
        map_delay.add("30");
        map_delay.add("35");
        map_delay.add("40");
        map_delay.addItemListener(this);
        map_delay.select(Parent.params.get("polldelay.map"));
        map_panel = new Panel();
        map_panel.add(new Label("Map Delay(ms)"));
        map_panel.add(map_delay);
        add(map_panel, "Center");
    }

    public void itemStateChanged(ItemEvent evt) {
        if (evt.getSource().equals(map_delay)) {
            Parent.mapwin.changeUpdateDelay(Integer.parseInt(map_delay.getSelectedItem()));
            Parent.params.setLegalValue("polldelay.map", map_delay.getSelectedItem());
        } else if (evt.getSource().equals(server_delay)) {
            Parent.loopcheck.setSleepDelay(Integer.parseInt(map_delay.getSelectedItem()));
            Parent.params.setLegalValue("polldelay.map", map_delay.getSelectedItem());
        }
        Parent.focusGained(null);
    }
}
