
package @@PROJECT_PACKAGE_NAME@@;

import static org.makagiga.commons.UI._;

import org.makagiga.commons.*;
import org.makagiga.plugins.AbstractPlugin;
import org.makagiga.plugins.PluginException;
import org.makagiga.plugins.PluginInfo;
import org.makagiga.plugins.PluginMenu;

public class Plugin extends AbstractPlugin<Object> implements PluginMenu {

/*
	// TODO: OPTIONAL: plugin shut down; invoked on application exit
	@Override
	public void onDestroy() throws PluginException {	
	}
*/

/*
	// TODO: OPTIONAL: plugin initialization; read configuration here
	// NOTE: at this time the main window does not exist yet!
	@Override
	public void onInit(Config config, PluginInfo info) throws PluginException {
		super.onInit(config, info);
	}
*/

/*
	// TODO: OPTIONAL: plugin GUI initialization (e.g. modify main window components)
	@Override
	public void onPostInit() throws PluginException {	
	}
*/
	
	// OPTIONAL: plugin menu interface
	
	private MAction action;
	
	public void updateMenu(String type, MMenu menu) {
		if (type.equals(TOOLS_MENU)) {
			if (action == null)
				action = new MyAction();
			menu.add(action);
		}
	}
	
	public void updateToolBar(String type, MToolBar toolBar) {
		if (type.equals(MAIN_TOOL_BAR)) {
			if (action == null)
				action = new MyAction();
			toolBar.add(action);
		}
	}
	
	private class MyAction extends MAction {

		@Override
		public void onAction() {
			MMessage.info(null, _("This plugin is cool"));
		}
		
		private MyAction() {
			super(_("Hello, World"), "labels/emotion/love");
		}
	
	}

}
