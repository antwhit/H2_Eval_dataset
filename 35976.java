import javax.microedition.lcdui.Graphics;

public class InfoPanel {

    M3GCharacter object;

    String[] displayString = null;

    public InfoPanel() {
    }

    public void drawPanel(Graphics mainGraph, int heigth, int width) {
        if (displayString != null) for (int i = 0; i < displayString.length; i++) {
            int rectH = 10;
            int panX = width / 2;
            int panW = width / 2;
            int color = 0xF68C80;
            mainGraph.setColor(color);
            mainGraph.fillRect(panX, (i * rectH) + 2, panW, rectH);
            String elem = displayString[i];
            mainGraph.setColor(0x000000);
            mainGraph.drawString(elem, panX, (i * rectH) + 2, 0);
        }
    }

    public void updatePanel(M3GCharacter object) {
        this.object = object;
        if (object != null) displayString = new String[] { "  " + object.name, "Strength: " + object.strength, "Agility: " + object.agility, "Mana: " + object.mana, "Vitality: " + object.vitality, "Level: " + object.level, "Min Dam: " + object.minDam(), "Max Dam: " + object.maxDam(), "Defense: " + object.defense(), "Life: " + object.life }; else displayString = new String[] { "Nothing to", "info about" };
    }
}
