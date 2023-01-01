import data.ooimpl.*;
import data.events.VetoException;

public class ApplicationStock extends CountingStockImpl {

    public ApplicationStock(String name, CatalogImpl cat) {
        super(name, cat);
    }

    /**
     * Liefert die Anzahl der Elemente eines CountingStockItems
     */
    public int getQuantity(String name) {
        return countItems(name, null);
    }

    /**
     * Setzt die Anzahl der Elemente eines CountingStockItems
     */
    public void setQuantity(String name, int quantity) {
        if (getQuantity(name) == quantity) {
            return;
        }
        if (getQuantity(name) < quantity) {
            add(name, quantity - getQuantity(name), null);
        } else {
            try {
                remove(name, getQuantity(name) - quantity, null);
            } catch (VetoException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
