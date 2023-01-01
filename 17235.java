
package @@PROJECT_PACKAGE_NAME@@;

import static org.makagiga.commons.UI._;

import org.makagiga.commons.*;
import org.makagiga.desktop.Widget;

public class Main extends Widget {

	public Main() {
		// TODO: title
		super(_("Widget Title"));

		// TODO: content
		addCenter(new MLabel("Hello, World!"));
	}
	
	@Override
        protected void onRestoreSession() {
		// TODO: read settings, load file, etc.
		// EXAMPLE:
		String foo = getConfig().read("x.foo", "default foo value");
		System.err.println(foo);
	}

	@Override
        protected void onSaveSession() {
		// TODO: write settings, save file, etc.
		// EXAMPLE:
		getConfig().write("x.foo", "foo value");
	}

}
