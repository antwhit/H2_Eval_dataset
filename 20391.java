
package @@PROJECT_PACKAGE_NAME@@;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import org.makagiga.commons.ColorProperty;
import org.makagiga.commons.Config;
import org.makagiga.commons.MProperties;
import org.makagiga.commons.UI;
import org.makagiga.commons.preview.AbstractPreview;
import org.makagiga.commons.preview.DefaultPreview;
import org.makagiga.editors.Editor;
import org.makagiga.editors.EditorPlugin;
import org.makagiga.plugins.PluginException;
import org.makagiga.plugins.PluginInfo;

public class Plugin extends EditorPlugin {

	@Override
	public Editor<?> create() {
		return new Main();
	}

	@Override
	public void onInit(Config config, PluginInfo info) throws PluginException {
		super.onInit(config, info);

		// TODO: Replace "mgcolor" with your own file suffix/extension.
		setFileTypes(	
			new FileType("mgcolor")
		);
		
		// See the File|Import menu.
		setImportTypes(
			new FileType("mgcolor", getName())
		);
		
		// See the File|Export menu.
		// Used by the EditorExport interface.
		setExportTypes(	
			new FileType("mgcolor", getName()),
			new FileType("html", "HTML")
		);
		
		// EXAMPLE: Add thumbnail preview
		DefaultPreview.getInstance().addHandler("mgcolor", new AbstractPreview(false) {
			public Image getImage(File file, int width, MProperties properties) throws Exception {
				// Read file data
				MProperties p = new MProperties();
				p.loadUTF8(file);
				Color color = ColorProperty.parseColor(p.getProperty("color", "#000000"));
				
				// Set metadata properties (currently not used)
				properties.setPropertyValue("Color", ColorProperty.toString(color));
				
				// Create and return thumbnail image
				BufferedImage image = UI.createCompatibleImage(width, width, false);
				Graphics2D g = image.createGraphics();
				g.setColor(color);
				g.fillRect(0, 0, width, width);
				g.dispose();

				return image;
			}
		} );
	}

}
