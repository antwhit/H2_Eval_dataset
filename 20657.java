import java.io.File;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import address.TrackingOrder;

public class Main {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(TrackingOrder.class);
        Unmarshaller u = jc.createUnmarshaller();
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            TrackingOrder tOrder = (TrackingOrder) u.unmarshal(new File("src/trackingData.xml"));
            m.marshal(tOrder, System.out);
        } catch (javax.xml.bind.UnmarshalException e) {
            System.out.println("Main: " + e);
        }
    }
}
