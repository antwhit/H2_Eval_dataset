import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.util.HashMap;

class Importer extends DefaultHandler {

    private String eName;

    private HashMap<String, StoredCard> database;

    private StoredCard currentCard;

    public Importer() {
        try {
            database = new HashMap<String, StoredCard>(500);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public HashMap<String, StoredCard> getDatabase() {
        return database;
    }

    public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException {
        eName = sName;
        if ("".equals(eName)) eName = qName;
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                String localName = attrs.getLocalName(i);
                String localValue = attrs.getValue(i);
                if (localName.equals("id")) {
                    if (currentCard != null) {
                        database.put(currentCard.getID(), currentCard);
                    }
                    currentCard = new StoredCard(localValue);
                } else if (localName.equals("type")) {
                    currentCard.setType(localValue);
                } else if (localName.equals("edition")) {
                    currentCard.setImageEdition(localValue);
                }
            }
        }
    }

    public void characters(char buf[], int offset, int len) throws SAXException {
        String s = new String(buf, offset, len);
        if (!s.trim().equals("")) {
            if (eName.equals("name")) {
                currentCard.setName(s);
            } else if (eName.equals("image")) {
                currentCard.setImageLocation(s);
            } else if (eName.equals("edition")) {
                currentCard.setEdition(s);
            } else if (eName.equals("legal")) {
                currentCard.setLegal(s);
            } else if (eName.equals("text")) {
                currentCard.setText(s);
            } else if (eName.equals("cost")) {
                currentCard.setCost(s);
            } else if (eName.equals("focus")) {
                currentCard.setFocus(s);
            } else if (eName.equals("clan")) {
                currentCard.setClan(s);
            } else if (eName.equals("province_strength")) {
                currentCard.setProvinceStrength(s);
            } else if (eName.equals("gold_production")) {
                currentCard.setGoldProduction(s);
            } else if (eName.equals("starting_honor")) {
                currentCard.setStartingHonor(s);
            } else if (eName.equals("force")) {
                currentCard.setForce(s);
            } else if (eName.equals("chi")) {
                currentCard.setChi(s);
            } else if (eName.equals("personal_honor")) {
                currentCard.setPersonalHonor(s);
            } else if (eName.equals("honor_req")) {
                currentCard.setHonorReq(s);
            }
        }
    }
}
