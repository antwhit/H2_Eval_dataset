import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import shoppingCart.KitchenWorldBasket;

public class Main {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(KitchenWorldBasket.class);
        Unmarshaller u = jc.createUnmarshaller();
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            KitchenWorldBasket kwBasket = (KitchenWorldBasket) u.unmarshal(new File("src/shoppingCartData.xml"));
            System.out.println(kwBasket.toString());
            m.marshal(kwBasket, System.out);
        } catch (javax.xml.bind.UnmarshalException e) {
            System.out.println("Main: " + e);
        }
    }
}
