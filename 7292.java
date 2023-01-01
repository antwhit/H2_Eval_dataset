import sale.Shop;
import java.awt.Rectangle;

public class MainClass {

    public static void main(String[] args) {
        SalesPointApplication spa = new SalesPointApplication();
        Shop.setTheShop(spa);
        spa.start();
        MySalesPoint msp = new MySalesPoint("Mein SalesPoint");
        msp.setSalesPointFrameBounds(new Rectangle(100, 200, 400, 300));
        spa.addSalesPoint(msp);
    }
}
