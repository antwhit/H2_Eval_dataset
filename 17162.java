import java.util.EventListener;

public interface AnimalListener extends EventListener {

    public void animalRemoved(AnimalEvent e);
}
