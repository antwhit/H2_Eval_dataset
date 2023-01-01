package @app.package@;

import javax.servlet.ServletConfig;

import org.jzonic.init.Bootstrap;
import org.jzonic.init.BootstrapException;
import org.jzonic.web.ApplicationContext;

public class ApplicationStartup implements Bootstrap {

	public void init(ServletConfig servletConfig) throws BootstrapException {
		// register beans here
	}

	public void shutdown() throws BootstrapException {
	}

}