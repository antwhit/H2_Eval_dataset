
package @@PROJECT_PACKAGE_NAME@@;

import org.makagiga.desktop.Widget;
import org.makagiga.desktop.WidgetPlugin;

public class Plugin extends WidgetPlugin {

	/**
	 * Creates a new instance of the widget.
	 */
	@Override
	public Widget create() {
		return new Main(getInfo());
	}

}
