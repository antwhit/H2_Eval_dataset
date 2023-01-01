import javax.swing.JOptionPane;

public class Opdracht1_3 {

    public Opdracht1_3() {
        System.out.println("Opdracht 1.3");
        int straal = Main.vraagInt("Geef een straal van een cirkel/bol.\ndan reken in de oppervlakte/inhoud uit.\n(int)");
        double omtrekCirkel = 2 * Math.PI * straal;
        double oppervlakteCirkel = Math.PI * (Math.pow(straal, 2));
        double oppervlakteBol = 4 * Math.PI * (Math.pow(straal, 2));
        double volumeBol = (4 / 3) * Math.PI * (Math.pow(straal, 3));
        JOptionPane.showMessageDialog(null, "Straal:" + straal + "\n\nCirkel\n---\nOmtrek: " + omtrekCirkel + "\nOppervlakte: " + oppervlakteCirkel + "\n\nBol\n---\nOppervlakte: " + oppervlakteBol + "\nVolume: " + volumeBol);
    }

    public static void main(String[] args) {
        new Opdracht1_3();
    }
}
