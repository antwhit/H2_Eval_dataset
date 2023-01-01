
package @@PROJECT_PACKAGE_NAME@@;

import org.makagiga.console.ConsoleCommand;
import org.makagiga.console.ConsolePlugin;

public class Plugin extends ConsolePlugin {
	
	@Override
	public ConsoleCommand create() throws Exception {
		return new Main(getInfo());
	}
	
}
