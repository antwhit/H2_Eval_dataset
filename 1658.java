public class Account extends Object {

    public int amount;

    public int size() {
        return amount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
