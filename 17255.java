public class Main {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        Attaquant gab = new Attaquant(79, 85, 80, 80, 65, 82, 80, "Gab", "RW", 50, "", 80, 28, 50000, 1, "Québec", 230, 177, 10);
        afficherMessPresentation();
        afficherMenu();
        int menu = saisirEtValiderChoix(4);
        switch(menu) {
            case 1:
                effectuerMenu1();
                break;
            case 2:
                effectuerMenu2();
                break;
            case 3:
                effectuerMenu3();
                break;
            case 4:
                effectuerMenu4();
                break;
            default:
                System.out.println("Erreur");
                break;
        }
    }

    public static void effectuerMenu1() {
        System.out.println("Menu 1 :");
    }

    public static void effectuerMenu2() {
        System.out.println("Menu 2 :");
    }

    public static void effectuerMenu3() {
        System.out.println("Menu 3 :");
    }

    public static void effectuerMenu4() {
        System.out.println("Menu 4 :");
    }

    public static void afficherMessPresentation() {
        System.out.println("Bienvenue dans le WebSim.");
        System.out.println("Une création de :");
        System.out.println("    ____        _       ");
        System.out.println("   /  __\\  _ _ | |__   ");
        System.out.println("   | | __ / _` | '_ \\  ");
        System.out.println("   | |_| | (_) | |_) |  ");
        System.out.println("   \\____/ \\__,_|_'__/ ");
        System.out.println();
    }

    public static void afficherMenu() {
        System.out.println("Que voulez vous faire?");
        System.out.println("1- Faire un tour du WebSim");
        System.out.println("2- Ajouter un attaquant (Avant ou défenseur)");
        System.out.println("3- Ajouter un gardien");
        System.out.println("4- Ajouter une équipe");
    }

    public static int saisirEtValiderChoix(int nbMax) {
        String messErreur = "Réponse invalide";
        boolean choixValide = false;
        int reponse = 0;
        while (choixValide == false) {
            System.out.println("Entrez votre choix (un nombre entier entre 1 et " + nbMax + ") :");
            try {
                reponse = Clavier.lireInt();
            } catch (NumberFormatException e) {
            }
            if (reponse <= nbMax && reponse >= 1) {
                choixValide = true;
            } else {
                System.out.println(messErreur);
            }
        }
        return reponse;
    }
}
