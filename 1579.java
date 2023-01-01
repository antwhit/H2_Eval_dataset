import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import globals.*;

/** FidoReadApplet.java v.2.0

This is the main file for the FidoCadJ reader applet.

<pre>

    This file is part of FidoCadJ.

    FidoCadJ is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FidoCadJ is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with FidoCadJ.  If not, see <http://www.gnu.org/licenses/>.

    Copyright march 2007- march 2010 by Davide Bucci
</pre>

@author Davide Bucci
*/
public class FidoCadApplet extends JApplet implements ActionListener {

    public void init() {
        Button fidoButton = new Button("Launch FidoCadJapplet");
        fidoButton.addActionListener(this);
        getContentPane().add(fidoButton);
        popFrame = new FidoFrame(false);
        FidoFrame.currentLocale = Locale.getDefault();
        try {
            Globals.messages = ResourceBundle.getBundle("MessagesBundle", FidoFrame.currentLocale);
        } catch (MissingResourceException mre) {
            try {
                Globals.messages = ResourceBundle.getBundle("MessagesBundle", new Locale("en", "US"));
                System.out.println("No locale available, sorry... interface will be in English");
            } catch (MissingResourceException mre1) {
                JOptionPane.showMessageDialog(null, "Unable to find language localization files: " + mre1);
                System.exit(1);
            }
        }
        Globals.useNativeFileDialogs = false;
        Globals.useMetaForMultipleSelection = false;
        if (System.getProperty("os.name").startsWith("Mac")) {
            Globals.shortcutKey = InputEvent.META_MASK;
            Globals.useMetaForMultipleSelection = true;
            Globals.useNativeFileDialogs = true;
        } else {
            Globals.shortcutKey = InputEvent.CTRL_MASK;
        }
        popFrame.init();
    }

    public void actionPerformed(ActionEvent evt) {
        if (popFrame.isVisible()) popFrame.setVisible(false); else popFrame.setVisible(true);
    }

    private FidoFrame popFrame;
}
