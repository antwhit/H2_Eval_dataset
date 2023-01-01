import javax.swing.UIManager;
import org.columba.core.gui.themes.plugin.AbstractThemePlugin;

/**
 * @author frd
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class HippoLookAndFeelPlugin extends AbstractThemePlugin {

    /**
	 *  
	 */
    public HippoLookAndFeelPlugin() {
        super();
    }

    public void setLookAndFeel() throws Exception {
        UIManager.setLookAndFeel(new se.diod.hippo.plaf.HippoLookAndFeel());
        UIManager.getLookAndFeelDefaults().put("ClassLoader", getClass().getClassLoader());
    }
}
