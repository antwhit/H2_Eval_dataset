import java.util.Iterator;

public abstract class XMLSerializeSimple implements XMLSerialize {

    protected abstract void handleTag(int i, XMLElement curElement);

    protected abstract String[] getTags();

    public void fromXML(XMLElement inXML) {
        String[] infoTags = getTags();
        Iterator infoFields = inXML.getChildren();
        while (infoFields.hasNext()) {
            XMLElement fieldStep = (XMLElement) infoFields.next();
            String curField = fieldStep.getTagName();
            for (int i = 0; i < infoTags.length; i++) {
                if (infoTags[i].equals(curField)) {
                    handleTag(i, fieldStep);
                    break;
                }
            }
        }
    }

    public abstract XMLElement toXML();
}
