import data.ooimpl.CatalogImpl;
import data.CatalogItem;

public class ApplicationCatalog extends CatalogImpl {

    public ApplicationCatalog(String name) {
        super(name);
    }

    public void add(CatalogItem item) {
        add(item, null);
    }

    public void remove(String name) {
        try {
            remove(name, null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void remove(CatalogItem item) {
        try {
            remove(item, null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
