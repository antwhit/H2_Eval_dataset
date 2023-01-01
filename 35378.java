import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import java.util.UUID;
import net.emotivecloud.nuba.network.NUBANetwork;

public class NetComposer extends Grid {

    protected Textbox name;

    protected Textbox address;

    protected Textbox netmask;

    public NetComposer() {
        super();
        Rows rows = new Rows();
        rows.setParent(this);
        Row row = new Row();
        row.setParent(rows);
        new Label("Network name:").setParent(row);
        name = new Textbox("network0");
        name.setCols(40);
        name.setWidth("100%");
        name.setParent(row);
        row = new Row();
        row.setParent(rows);
        new Label("Address:").setParent(row);
        address = new Textbox("0.0.0.0");
        address.setCols(40);
        address.setWidth("100%");
        address.setParent(row);
        row = new Row();
        row.setParent(rows);
        new Label("Netmask:").setParent(row);
        netmask = new Textbox("0.0.0.0");
        netmask.setCols(40);
        netmask.setWidth("100%");
        netmask.setParent(row);
    }

    public NUBANetwork getNetwork() {
        if (name.getValue().contains("_")) {
            Messagebox.show("Name not allowed: " + name.getValue(), "Error", Messagebox.OK, Messagebox.ERROR);
            return null;
        }
        NUBANetwork network = new NUBANetwork();
        network.setName(name.getValue().toString());
        network.setBaseAddress(address.getValue().toString());
        network.setNetmask(netmask.getValue().toString());
        return network;
    }
}
