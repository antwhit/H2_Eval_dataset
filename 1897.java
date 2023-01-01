public class MoneyBuffer {

    private double cash, credit;

    public MoneyBuffer() {
        cash = 0d;
        credit = 0d;
    }

    public void addCash(double amt) {
        if (amt > 0d) cash += amt;
    }

    public void addCredit(double amt) {
        if (amt > 0d) credit += amt;
    }

    public void setCash(double amt) {
        if (amt >= 0d) {
            cash = amt;
        }
    }

    public void setCredit(double amt) {
        if (amt >= 0d) {
            credit = amt;
        }
    }

    public double getCash() {
        return cash;
    }

    public double getCredit() {
        return credit;
    }

    public double getBufferTotal() {
        return (getCash() + getCredit());
    }

    public void flushCash() {
        cash = 0d;
    }

    public void flushCredit() {
        credit = 0d;
    }

    public void flushAll() {
        cash = 0d;
        credit = 0d;
    }
}
