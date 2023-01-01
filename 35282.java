import org.nakedobjects.application.valueholder.TextString;

public class Weapon {

    private final TextString name = new TextString();

    public TextString getName() {
        return name;
    }

    public String toString() {
        return name.titleString();
    }
}
