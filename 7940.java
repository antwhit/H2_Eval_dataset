
package @@PROJECT_PACKAGE_NAME@@;

import static org.makagiga.commons.UI._;

import org.makagiga.commons.MLabel;
import org.makagiga.desktop.SessionEvent;
import org.makagiga.desktop.SessionListener;
import org.makagiga.desktop.Widget;
import org.makagiga.plugins.PluginInfo;

public class Main extends Widget implements SessionListener {

	Main(PluginInfo info) {
		super(info);
		addSessionListener(this);
		
		// TODO: Add content.
		MLabel content = new MLabel(_("Hello"));
		content.setHorizontalAlignment(MLabel.CENTER);
		content.setIconName("labels/emotion/happy");
		content.setStyle("font-size: larger; font-weight: bold");
		addCenter(content);

		// Feel free to experiment with the below properties...
		setBackgroundDraggable(false);
		setResizable(true);
		setShaped(false);
		setDefaultSize(250, 250);
	}

	// SessionListener interface
	
	/**
	 * Read settings, load file, etc.
	 */
	public void restoreSession(final SessionEvent e) {
		// EXAMPLE: 
		// Config config = getConfig();
		// String foo = config.read("x.foo", "default foo value");
		// System.err.println(foo);
	}

	/**
	 * Write settings, save file, etc.
	 */
	public void saveSession(final SessionEvent e) {
		// EXAMPLE: 
		// Config config = getConfig();
		// config.write("x.foo", "foo value");
	}

}
