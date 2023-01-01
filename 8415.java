import javax.swing.JTabbedPane;
import javax.swing.JLabel;

/**
 * The center are of the game.
 *
 * This class creates a JTabbedPane and load it with the panels
 * {@link TableArea}, {@link NetworkArea}, {@link Lobby} and
 * {@link Summary}. It provides also methods for selecting the
 * current tab to shown.
 *
 * @author Peter Henschel
 * @version $Id: CenterArea.java 6 2008-04-05 15:20:02Z progi $
 */
public class CenterArea extends JTabbedPane {

    /**
     * Create the CenterArea.
     *
     * The argument must be the Client object. If the argument is null a
     * {@link NullPointerException}. The Client object is passed to the
     * subclasses descripted above and is used to register themselve
     * via {@link Client#setCenterArea} at the Client object.
     */
    public CenterArea(Client c) {
        super();
        if (null == c) {
            throw new NullPointerException("argument must not be null.");
        }
        this.add(Client.getL("GAME"), new TableArea(c));
        this.add(Client.getL("NETWORK"), new NetworkArea(c));
        this.add(Client.getL("LOBBY"), new Lobby(c));
        this.add(Client.getL("SUMMARY"), new Summary(c));
        c.setCenterArea(this);
    }

    /**
     * Show the summary tab.
     *
     * This method switchs the current view of the tabs to the summary tab.
     */
    public void showSummary() {
        this.setSelectedIndex(3);
    }
}
