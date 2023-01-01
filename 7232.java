import java.util.EventObject;

public class AnimalEvent extends EventObject {

    /**
   * Constructs an AnimalEvent object with the specified soource object.
   *
   * @param source the object where the event originated
   * @param id the type of event
  */
    public AnimalEvent(Object source, int id) {
        super(source);
    }
}
