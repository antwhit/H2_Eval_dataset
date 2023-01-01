import javax.xml.bind.annotation.XmlRootElement;
import com.sun.xml.bind.CycleRecoverable;

@XmlRootElement
public class Person implements CycleRecoverable {

    public int id;

    public String name;

    public Person parent;

    public Person onCycleDetected(Context context) {
        Person replacement = new Person();
        replacement.id = this.id;
        return replacement;
    }
}
