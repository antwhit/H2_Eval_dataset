import code.DiscountApplication;
import fitlibrary.SetUpFixture;

/**
 * 
 */
public class SetUpDiscounts extends SetUpFixture {

    private DiscountApplication app;

    public SetUpDiscounts(DiscountApplication app) {
        this.app = app;
    }

    public void futureValueMaxBalanceMinPurchaseDiscountPercent(String futureValue, double maxBalance, double minPurchase, double discountPercent) {
        app.addDiscountGroup(futureValue, maxBalance, minPurchase, discountPercent);
    }
}
