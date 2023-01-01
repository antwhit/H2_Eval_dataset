import org.ist_spice.mdcs.rds.model.ModalityDevice;
import org.ist_spice.mdcs.sparql.SparqlEngine;

public class SparqlTest {

    public static void main(String[] args) {
        SparqlEngine engine = new SparqlEngine();
        ModalityDevice device = engine.getModalityDevice("http://ontology.ist.spice.org/mobile-ontology/0/10/dcs/0/tvexample.owl#ModalityDevice_TVLivingRoom");
        ModalityDevice device2 = engine.getModalityDevice("http://ontology.ist.spice.org/mobile-ontology/0/10/dcs/0/mobilephone.owl#ConsuelosMobilePhone");
    }
}
