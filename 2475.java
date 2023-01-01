import javax.swing.JOptionPane;

public class minus {

    public static void main(String[] args) {
        String[] opzioni = { "Maiuscolo", "Minuscolo" };
        String frase = JOptionPane.showInputDialog("Inserire frase:");
        int maius = JOptionPane.showOptionDialog(null, "Vuoi trasformare in maiscolo o in minuscolo?", "Maiuscolo o minuscolo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);
        String frasecambiata = "";
        if (maius == 1) {
            int leng = frase.length();
            int i = 0;
            while (i < leng) {
                char lettera = frase.charAt(i);
                int lett = lettera;
                if (lett <= 97 && lett >= 65) {
                    lett = lett + 32;
                    lettera = (char) lett;
                }
                frasecambiata = frasecambiata + lettera;
                i++;
            }
        } else {
            int leng = frase.length();
            int i = 0;
            while (i < leng) {
                char lettera = frase.charAt(i);
                int lett = lettera;
                if (lett >= 92) {
                    lett = lett - 32;
                    lettera = (char) lett;
                }
                frasecambiata = frasecambiata + lettera;
                i++;
            }
        }
        JOptionPane.showMessageDialog(null, frasecambiata);
    }
}
