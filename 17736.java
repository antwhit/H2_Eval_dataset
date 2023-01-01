import java.io.Reader;
import java.io.Writer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import com.anthonyeden.lib.config.Configuration;
import com.anthonyeden.lib.config.Dom4jConfiguration;
import com.anthonyeden.lib.config.MutableConfiguration;

public class AddressBook {

    public AddressBook() {
        addresses = new TreeMap();
    }

    public Map getAddresses() {
        return addresses;
    }

    public Address getAddress(String id) {
        return (Address) addresses.get(id);
    }

    public synchronized void addAddress(Address address) {
        address.setId(Long.toString(System.currentTimeMillis()));
        addresses.put(address.getId(), address);
    }

    public synchronized void deleteAddress(String id) {
        addresses.remove(id);
    }

    /**	Load the address book entries from the given XML stream.
	
		@param in The InputStream
	*/
    public void load(InputStream in) throws Exception {
        load(new InputStreamReader(in));
    }

    public void load(Reader in) throws Exception {
        addresses.clear();
        Configuration configuration = new Dom4jConfiguration(in);
        Iterator addressElements = configuration.getChildren("address").iterator();
        while (addressElements.hasNext()) {
            Configuration addressElement = (Configuration) addressElements.next();
            Address address = new Address();
            address.setId(addressElement.getAttribute("id"));
            address.setFirstName(addressElement.getChildValue("firstName"));
            address.setLastName(addressElement.getChildValue("lastName"));
            address.setEmail(addressElement.getChildValue("email"));
            addresses.put(address.getId(), address);
        }
    }

    public void save(OutputStream out) throws Exception {
        new OutputStreamWriter(out);
    }

    public void save(Writer out) throws Exception {
        MutableConfiguration configuration = new Dom4jConfiguration("addressbook");
        Iterator addresses = getAddresses().values().iterator();
        while (addresses.hasNext()) {
            Address address = (Address) addresses.next();
            MutableConfiguration addressElement = configuration.addChild("address", null);
            addressElement.addAttribute("id", address.getId());
            addressElement.addChild("firstName", address.getFirstName());
            addressElement.addChild("lastName", address.getLastName());
            addressElement.addChild("email", address.getEmail());
        }
        configuration.save(out);
    }

    private TreeMap addresses;
}
