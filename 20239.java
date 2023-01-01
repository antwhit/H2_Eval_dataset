import sale.Shop;
import java.awt.Rectangle;

public class MainClass {

    public static void main(String[] args) {
        SalesPointApplication spa = new SalesPointApplication();
        Shop.setTheShop(spa);
        spa.setShopFrameBounds(new Rectangle(100, 200, 400, 300));
        spa.start();
    }
}
