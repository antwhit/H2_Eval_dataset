public class keyClass {

    public keyClass(int keyCode) {
        this.shifted = false;
        this.keyCode = keyCode;
    }

    public keyClass(int keyCode, boolean shifted) {
        this.shifted = shifted;
        this.keyCode = keyCode;
    }

    public boolean shifted;

    public int keyCode;
}
