public class FarmWithIndustrialPigTest {

    public static void main(String[] args) {
        IndustrialPig pig7of9 = new IndustrialPig(20f, "7of9");
        Pig pig8of9 = new IndustrialPig(20f, "8of9");
        Pig p = new IndustrialPigFactory().createPig();
        System.out.println(pig7of9.toString());
        Pig ourLittleHousePig = new Pig();
        Farmer farmer = new Farmer();
        farmer.feedPig(pig7of9);
        farmer.feedPig(p);
        farmer.feedPig(ourLittleHousePig);
    }
}
