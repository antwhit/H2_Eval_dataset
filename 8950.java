import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * The "Settings" menu of the game.
 * @author Joni Toivanen (jomiolto@gmail.com)
 */
public class SettingsMenu extends JMenu implements MenuListener {

    /**
	 * A sub-menu for choosing the style, or Look and Feel, of the application.
	 */
    private JMenu styleMenu;

    /**
	 * A sub-menu for choosing the color scheme.
	 */
    private JMenu schemeMenu;

    /**
	 * "Preferences" menu item.
	 */
    private JMenuItem preferences;

    /**
	 * Default constructor.
	 */
    public SettingsMenu() {
        super("Settings");
        styleMenu = new StyleMenu();
        schemeMenu = new SchemeMenu();
        preferences = new JMenuItem("Preferences");
        preferences.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new PreferencesWindow();
            }
        });
        this.setMnemonic(KeyEvent.VK_S);
        this.add(styleMenu);
        this.add(schemeMenu);
        this.add(preferences);
        this.addMenuListener(this);
        this.setVisible(true);
    }

    /**
	 * The file menu has been deselected, transfer the focus back to the previous component that had it.
	 * @param e the menu event (not used)
	 */
    public void menuDeselected(MenuEvent e) {
        this.transferFocus();
    }

    /**
	 * The file menu has been canceled, transfer the focus back to the previous component that had it. 
	 * @param e the menu event (not used)
	 */
    public void menuCanceled(MenuEvent e) {
        this.transferFocus();
    }

    /**
	 * The file menu has been selected, not used.
	 * @param e the menu event (not used)
	 */
    public void menuSelected(MenuEvent e) {
    }
}
