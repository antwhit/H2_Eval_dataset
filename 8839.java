import rsswaba.persistence.RssChannelHomePersistence;
import superwaba.ext.xplat.util.props.Properties;
import waba.sys.Convert;
import waba.sys.Settings;
import waba.ui.Button;
import waba.ui.Check;
import waba.ui.Edit;
import waba.ui.Event;
import waba.ui.Label;
import waba.ui.Window;

/**
 * @author xp
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class RssProxyWindow extends Window {

    private Edit proxyAddress;

    private Edit proxyPort;

    private Edit defaultBauds;

    private Check proxyUse;

    private Properties properties;

    private Button okB;

    private RssChannelHomePersistence rssChannelHome;

    public RssProxyWindow(RssChannelHomePersistence rsh) {
        super("Rss Channels", ROUND_BORDER);
        rssChannelHome = rsh;
        setRect(CENTER, CENTER, Settings.screenWidth * 150 / 160, Settings.screenHeight * 100 / 180);
        proxyUse = new Check("Use Proxy");
        this.add(proxyUse, SAME + 5, AFTER + 2);
        this.add(new Label("Proxy Address"), SAME, AFTER);
        proxyAddress = new Edit("                                        ");
        this.add(proxyAddress, SAME, AFTER);
        this.add(new Label("Proxy Port"), SAME, AFTER);
        proxyPort = new Edit("               ");
        this.add(proxyPort, SAME, AFTER);
        this.add(new Label("Bauds"), SAME, AFTER);
        defaultBauds = new Edit("               ");
        this.add(defaultBauds, SAME, AFTER);
        okB = new Button("   OK   ");
        this.add(okB, SAME, AFTER + 3);
        this.refreshProperties();
    }

    public void refreshProperties() {
        proxyUse.setChecked(rssChannelHome.proxyUse());
        proxyPort.setText(Convert.toString(rssChannelHome.proxyPort()));
        defaultBauds.setText(Convert.toString(rssChannelHome.defaultBauds()));
        proxyAddress.setText(rssChannelHome.proxyAddress());
    }

    public void onEvent(Event event) {
        if ((event.target) == okB) this.onOkBEvent(event);
    }

    /**
	 * @param event
	 */
    private void onOkBEvent(Event event) {
        rssChannelHome.setProxyUse(proxyUse.getChecked());
        rssChannelHome.setProxyPort(Convert.toInt(proxyPort.getText()));
        rssChannelHome.setProxyAddress(proxyAddress.getText());
        rssChannelHome.setDefaultBauds(Convert.toInt(defaultBauds.getText()));
        rssChannelHome.saveConfig();
        unpop();
    }
}
