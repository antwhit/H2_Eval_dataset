
package @@PROJECT_PACKAGE_NAME@@;

import java.io.File;

import org.makagiga.fs.AbstractFS;
import org.makagiga.fs.FSException;
import org.makagiga.fs.FSNewFolder;
import org.makagiga.fs.MetaInfo;
import org.makagiga.plugins.PluginInfo;

/**
 * See org.makagiga.fs package (Makagiga source) for more examples...
 */
public class Main extends AbstractFS implements FSNewFolder {
	
	Main(PluginInfo info) throws FSException {
		super(
			info,
			info.getID(), // FS unique ID - see "~/.makagiga/vfs" directory
			info.toString(), // FS name - visible in the Tree
			info.getIcon() // FS icon - visible in the Tree
		);
		scan();
	}
	
	protected void processFile(MetaInfo parent, File file) {
		processDynamicFolder(parent, file);
	}

}
