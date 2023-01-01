
package @@PROJECT_PACKAGE_NAME@@;

import org.makagiga.console.ConsolePlugin;

public class Plugin extends ConsolePlugin {

	/**
	 * Creates a new instance of the console command.
	 */
	@Override
	public Main create() {
		return new Main();
	}

}
