import Sale.SalesPoint;
import Sale.Transaction;

class Tagesabschluss extends Transaction {

    public Tagesabschluss(SalesPoint theSalesPoint) {
        super(theSalesPoint);
    }

    protected void executeTransaction() {
    }
}
