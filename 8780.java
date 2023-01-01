
package @@PROJECT_PACKAGE_NAME@@;

import static org.makagiga.commons.UI._;

import org.makagiga.commons.swing.MLabel;
import org.makagiga.desktop.Widget;
import org.makagiga.plugins.PluginInfo;

public class Main extends Widget {

	Main(PluginInfo info) {
		super(info);
		setDefaultSize(250, 250);
		
		// TODO: Add content.
		MLabel content = new MLabel(_("Hello"));
		content.setHorizontalAlignment(MLabel.CENTER);
		content.setIconName("labels/emotion/happy");
		addCenter(content);
	}

	/**
	 * Read settings, load file, etc.
	 */
	@Override
	protected void onRestoreSession() throws Exception {
		// EXAMPLE:
		//Config config = getConfig();
		//String foo = config.read("x.foo", "default foo value");

		// HINT: Use getDataDirectory().resolve("file name")
		// to create a session data path.
	}

	/**
	 * Write settings, save file, etc.
	 */
	@Override
	protected void onSaveSession() {
		// EXAMPLE:
		//Config config = getConfig();
		//config.write("x.foo", "foo value");
	}

}
