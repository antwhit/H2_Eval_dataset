/**
 * Klasa definij�ca zmienna typu logicznego
 * 
 * Definiion of boolean varible
 */
public class BooleanVarible extends Varible {

    private boolean value;

    public BooleanVarible(String name) {
        super(name, WordType.WARTOSC_BOOLEAN);
        value = false;
    }

    public BooleanVarible(String name, boolean value) {
        super(name, WordType.WARTOSC_BOOLEAN);
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
