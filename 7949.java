import org.xml.sax.XMLReader;
import primer.PurchaseOrderType;
import primer.PurchaseOrders;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance("primer");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        final PurchaseOrders.Listener orderListener = new PurchaseOrders.Listener() {

            public void handlePurchaseOrder(PurchaseOrders purchaseOrders, PurchaseOrderType purchaseOrder) {
                System.out.println("this order will be shipped to " + purchaseOrder.getShipTo().getName());
            }
        };
        unmarshaller.setListener(new Unmarshaller.Listener() {

            public void beforeUnmarshal(Object target, Object parent) {
                if (target instanceof PurchaseOrders) {
                    ((PurchaseOrders) target).setPurchaseOrderListener(orderListener);
                }
            }

            public void afterUnmarshal(Object target, Object parent) {
                if (target instanceof PurchaseOrders) {
                    ((PurchaseOrders) target).setPurchaseOrderListener(null);
                }
            }
        });
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XMLReader reader = factory.newSAXParser().getXMLReader();
        reader.setContentHandler(unmarshaller.getUnmarshallerHandler());
        for (String arg : args) {
            reader.parse(new File(arg).toURI().toString());
        }
    }
}
