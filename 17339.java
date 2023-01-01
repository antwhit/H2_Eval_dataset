
package @@PROJECT_PACKAGE_NAME@@;

import java.util.Locale;

import org.makagiga.plugins.LocalePlugin;

// TODO: Copy spell dictionary file to the "resources" directory.
//
// You can download "Jazzy" dictionaries for your locale
// from the following project page:
// http://sourceforge.net/projects/jazzydicts/
//
// Dictionaries are free and licensed under the terms
// of the GNU General Public License (GPL).

public class Plugin extends LocalePlugin {

	public Plugin() {
		// TODO: Set locale for this plugin.
		// EXAMPLE: super(new Locale("pl", "PL"));
		super(Locale.ENGLISH);
	}

}
