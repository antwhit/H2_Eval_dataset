import System.*;
import dotNag.*;
import dotNag.Web.*;

public class TestScript implements IScript {

    public Object Execute(IDaemon daemon, IBrowser browser) {
        System.Xml.XmlDocument xml = new System.Xml.XmlDocument();
        browser.echo("<html><body><b>J#: Hello, World! " + xml.ToString() + "</b>" + "</body></html>");
        browser.set_ContentType("text/html");
        return null;
    }
}
