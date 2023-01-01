
package @@PROJECT_PACKAGE_NAME@@;

import static org.makagiga.commons.UI._;

import java.awt.Dimension;

import org.glossitope.desklet.Desklet;
import org.glossitope.desklet.DeskletContainer;
import org.glossitope.desklet.DeskletContext;
import org.glossitope.desklet.test.DeskletTester;

import org.makagiga.commons.*;

public class MyDesklet extends Desklet {
	
	public MyDesklet() { }
	
	public void destroy() { }
	
	public void init() throws Exception {
		DeskletContext context = getContext();
		DeskletContainer container = context.getContainer();
		
		// TODO: set content
		container.setContent(new MLabel(_("Hello")));

		container.setBackgroundDraggable(false);
		container.setResizable(true);
		container.setShaped(false);
		container.setSize(new Dimension(250, 250));
		container.setVisible(true);
	}
	
	public void start() {
		// TODO: start thread or timer (if any)
	}
	
	public void stop() {
		// TODO: stop thread or timer (if any)
		getContext().notifyStopped();
	}
	
	// test
	
	public static void main(final String[] args) {
		DeskletTester.start(MyDesklet.class);
	}

}
