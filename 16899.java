import sale.Shop;

public class MainClass {

    public static void main(String[] args) {
        SalesPointApplication spa = new SalesPointApplication();
        Shop.setTheShop(spa);
        spa.start();
    }
}
