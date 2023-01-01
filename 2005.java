import javax.swing.UIManager;
import org.columba.core.gui.themes.plugin.AbstractThemePlugin;

/**
 * @author frd
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MetouiaLookAndFeelPlugin extends AbstractThemePlugin {

    /**
	 * 
	 */
    public MetouiaLookAndFeelPlugin() {
        super();
    }

    public void setLookAndFeel() throws Exception {
        UIManager.setLookAndFeel(new net.sourceforge.mlf.metouia.MetouiaLookAndFeel());
        UIManager.getLookAndFeelDefaults().put("ClassLoader", getClass().getClassLoader());
    }
}
