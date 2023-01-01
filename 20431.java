import javax.microedition.lcdui.Graphics;

public class MainMenu {

    private Menu displayMenu = null;

    private Menu firstMenu = new Menu(new String[] { "Attrb/Lv Up", "Inventory", "Save/Exit" }, "");

    private Menu lvUpMenu = new Menu(new String[] { "Points left:  ", "Strength:  ", "Agility:  ", "Energy:  ", "Vitality:  " }, "*-Exit|0-Back|5-Up Att");

    private Menu invMenu = new Menu(new String[] { "Empty" }, "*-Exit|0-Back|#-Info|9-Drop");

    private M3GCharacter player = null;

    private boolean infoMode = false;

    private InfoPanel infPan = new InfoPanel();

    public MainMenu(M3GCharacter player) {
        displayMenu = firstMenu;
        this.player = player;
    }

    public void drawMenuSet(Graphics drawingGraph, int heigth, int width) {
        if (displayMenu != null) {
            if (displayMenu == lvUpMenu) {
                updateLvUpMenu();
            } else if (displayMenu == invMenu) {
                updateInvMenu();
            }
            displayMenu.drawMenu(drawingGraph, heigth, width);
            if (infoMode && (displayMenu == invMenu)) {
                infPan.drawPanel(drawingGraph, heigth, width);
            }
        }
    }

    public void nextItem() {
        displayMenu.nextItem();
        updateInfoPanel();
    }

    public void prevItem() {
        displayMenu.prevItem();
        updateInfoPanel();
    }

    public int selectItem(boolean dropSelect) {
        int selItem = displayMenu.selectItem();
        if (dropSelect) {
            if (displayMenu == invMenu) {
                if (selItem > 5) {
                    player.dropItem(selItem - 6);
                }
                updateInfoPanel();
            }
        } else {
            if (displayMenu == firstMenu) {
                if (selItem == 0) displayMenu = lvUpMenu; else if (selItem == 1) displayMenu = invMenu; else if (selItem == 2) return 1;
            } else if (displayMenu == lvUpMenu) {
                player.upgradeAttr(selItem - 1);
            } else if (displayMenu == invMenu) {
                if (selItem > 5) {
                    player.useItem(selItem - 6);
                } else {
                    player.unequipItem(selItem);
                }
                updateInvMenu();
                updateInfoPanel();
            }
        }
        return 0;
    }

    public void goBack() {
        if ((displayMenu == lvUpMenu) || (displayMenu == invMenu)) {
            displayMenu = firstMenu;
        }
    }

    private void updateLvUpMenu() {
        lvUpMenu.update(new String[] { "Points left:  " + player.distribPoints, "Strength:  " + player.strength, "Agility:  " + player.agility, "Mana:  " + player.mana, "Vitality:  " + player.vitality });
    }

    private void updateInvMenu() {
        invMenu.update(player.listItems());
    }

    public void infoToggle() {
        infoMode = (!infoMode);
        updateInfoPanel();
    }

    public void updateInfoPanel() {
        if (infoMode && (displayMenu == invMenu)) infPan.updatePanel(player.getItem(displayMenu.selectItem()));
    }
}
