public class SchweineTest {

    public SchweineTest() {
        System.out.println(Schwein.getAnzahl());
        Schwein piggy;
        piggy = new Schwein("Miss Piggy");
        System.out.println(piggy.toString());
        piggy.fressen();
        System.out.println(piggy.toString());
    }

    public static void main(String[] args) {
        new SchweineTest();
    }
}
