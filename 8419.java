public class Yote {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        Moteur moteur = new Moteur(Integer.parseInt(args[0]), "localhost");
        moteur.start();
    }
}
