import func.func;

public class prove {

    public static boolean isPresente(char s, String S) {
        int leng = S.length();
        int i = 0;
        String verofalso = "";
        while (i < leng - 1) {
            if (S.charAt(i) == s) {
                verofalso = "true";
            }
        }
        if (verofalso.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static int rand(int num, int num2) {
        int rand = 0;
        if (num < num2) {
            double random = 0;
            while (random <= num) {
                random = Math.random() * (num2 + 1);
            }
            rand = (int) random;
        } else {
            double random = 0;
            while (random <= num2) {
                random = Math.random() * (num + 1);
            }
            rand = (int) random;
        }
        return rand;
    }

    public static String controllaPacco(String regione, String[] regionipremi, String[] premi) {
        String premio = "";
        for (int i = 0; i < regionipremi.length; i++) {
            if (regionipremi[i].equalsIgnoreCase(regione)) {
                premio = premi[i];
            }
        }
        return premio;
    }

    public static void main(String[] args) {
        String[] premi = { "0.01 €", "0.02 €", "0.20 €", "0.50 €", "1 €", "2 €", "5 €", "20 €", "50 €", "100 €", "Una camicia", "Un KG di cemento", "1.000 €", "5.000 €", "10.000 €", "50.000 €", "75.000 €", "100.000 €", "250.000 €", "500.000 €" };
        String[] regioni = { "Valle d'Aosta", "Piemonte", "Lombardia", "Trentino", "Veneto", "Friuli", "Liguria", "Toscana", "Emilia romagna", "Marche", "Lazio", "Abruzzo", "Molise", "Umbria", "Campania", "Puglia", "Calabria", "Basilicata", "Sicilia", "Sardegna" };
        String[] regionipremi = new String[20];
        String[] numeri = new String[20];
        for (int i = 0; i < 20; i++) {
            int rand = func.rand(19);
            while (func.isNumInStringArray(rand, numeri)) {
                rand = func.rand(19);
            }
            numeri[rand] = rand + "";
            regionipremi[i] = regioni[rand];
        }
        String premio = premi[3];
        int num = 0;
        for (int i = 0; i < premi.length; i++) {
            if (premi[i].equals(premio)) {
                num = i;
            }
        }
        System.out.println(num);
    }
}
