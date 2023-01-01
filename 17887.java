
package @@PROJECT_PACKAGE_NAME@@;

import org.makagiga.commons.MDate;
import org.makagiga.commons.TK;
import org.makagiga.console.Console;
import org.makagiga.console.ConsoleCommand;
import org.makagiga.plugins.PluginInfo;

/**
 * See org.makagiga.console package (Makagiga source)
 * for more examples.
 */
public class Main extends ConsoleCommand {

	Main(PluginInfo info) {
		// TODO: change command name ("date")
		super("date", info.shortDescription.get());
	}
	
	@Override
	public Object onCommand(Console console, String... args) {
		// EXAMPLE:
		if (args.length == 0) {
			onHelp(console);

			return null;
		}
		else {
			String result = MDate.now().format(TK.join(args, " "));

			return result;

			// TIP: You can also print result using "io.printLine" and return null:
			// Console.IO io = console.getIO();
			// io.printLine(result);
			// return null;
		}
	}
	
	@Override
	public boolean onHelp(Console console) {
		Console.IO io = console.getIO();
		io.printLine("Usage: date [format]");
		io.printLine("Example 1: date HH:mm");
		io.printLine("Example 2: date yyyy-MM-dd HH:mm:ss");
	
		return true;
	}

}
