/**
 * A program f�oszt�lya
 */
public class TrickyMouse {

    public EditField ef;

    /**
	 * Konstruktor
	 */
    public TrickyMouse() {
        createEditField();
    }

    /**
	 * L�trehoz egy EditField objektumot
	 */
    public void createEditField() {
        ef = new EditField();
    }
}
