public class Opdracht2_6 {

    public Opdracht2_6() {
        double ans = 0;
        double ansmin = 1.0;
        double ansplus = 0;
        for (double t = -1000; t < 1000; t++) {
            ans = sigmoide(t);
            System.out.println("sigmoide(" + t + ") = " + ans + " <-------------");
            if (ansmin > ans) {
                ansmin = ans;
            }
            if (ansplus < ans) {
                ansplus = ans;
            }
        }
        System.out.println("maximale waarde = " + ansplus);
        System.out.println("minimale waarde = " + ansmin);
    }

    public static double sigmoide(double t) {
        double ans = 0;
        ans = (1 / (1 + Main.eMacht(-t)));
        return ans;
    }

    public static void main(String[] args) {
        new Opdracht2_6();
    }
}
