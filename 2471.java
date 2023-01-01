import java.awt.Component;
import java.awt.TextComponent;
import java.awt.event.MouseEvent;
import java.util.EventListener;

public interface XMLTextListener extends EventListener {

    public abstract void xmlTextValueChanged(XMLTextEvent xmltextevent);

    public abstract void xmlTag(String s, int i, int j);

    public abstract void clearXmlAttributes();

    public abstract void resetXmlAttributes(XMLTextArea xmltextarea, AttributeModel attributemodel);

    public abstract void addXmlAttribute();

    public abstract void doPopup(Component component, MouseEvent mouseevent);

    public abstract void setUndo(TextComponent textcomponent);

    public abstract void elementOptions(String s);
}
