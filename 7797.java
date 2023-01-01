/**
 * Doubled sub-class, form #2.
 */
public class DoubledExtend extends Base {

    public DoubledExtend() {
    }

    @Override
    public DoubledExtend getExtended() {
        return new DoubledExtend();
    }

    public String getStr() {
        return "DoubledExtend 2";
    }
}
