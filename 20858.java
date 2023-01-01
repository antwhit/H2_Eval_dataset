import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import example.*;

public class Main {

    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance("example");
            Unmarshaller u = jc.createUnmarshaller();
            Object fbe = u.unmarshal(new FileInputStream("example.xml"));
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(fbe, System.out);
        } catch (JAXBException je) {
            je.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
