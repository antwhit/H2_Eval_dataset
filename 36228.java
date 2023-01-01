import java.util.Random;

public class Dummy {

    public void test() {
    }

    public boolean equals(Object o) {
        return (this == o);
    }

    public static void main(String args[]) {
        int attacker_loss_1d = 0, defender_loss_1d = 0, attacker_loss_2d = 0, defender_loss_2d = 0;
        int attacker2_loss_1d = 0, attacker2_loss_2d = 0, defender2_loss_1d = 0, defender2_loss_2d = 0;
        int attacker1_loss_1d = 0, attacker1_loss_2d = 0, defender1_loss_1d = 0, defender1_loss_2d = 0;
        int _32_20 = 0, _32_11 = 0, _32_02 = 0;
        int _22_20 = 0, _22_11 = 0, _22_02 = 0;
        Random rand = new Random();
        int i;
        int runs = (args.length == 0) ? 1000000 : Integer.valueOf(args[0]).intValue();
        for (i = 0; i < runs; i++) {
            int d1, d2, d3, d4, d5;
            d1 = rand.nextInt(6);
            d2 = rand.nextInt(6);
            d3 = rand.nextInt(6);
            d4 = rand.nextInt(6);
            d5 = rand.nextInt(6);
            int high_a1, high_a2, high_d1, high_d2, high1_a2, high2_a2;
            high_d1 = Math.max(d4, d5);
            high_d2 = Math.min(d4, d5);
            high_a1 = Math.max(Math.max(d1, d2), d3);
            high_a2 = (d1 >= d2 && d1 <= d3) ? d1 : (d2 >= d3 && d2 <= d1) ? d2 : d3;
            high1_a2 = Math.max(d1, d2);
            high2_a2 = Math.min(d1, d2);
            int cur = 0;
            if (high_a1 > high_d1) {
                cur = 1;
                defender_loss_2d++;
            } else attacker_loss_2d++;
            if (high_a2 > high_d2) {
                cur++;
                defender_loss_2d++;
            } else attacker_loss_2d++;
            if (cur == 2) _32_20++; else if (cur == 1) _32_11++; else _32_02++;
            cur = 0;
            if (high_a1 > d4) defender_loss_1d++; else attacker_loss_1d++;
            if (high1_a2 > high_d1) {
                cur = 1;
                defender2_loss_2d++;
            } else attacker2_loss_2d++;
            if (high2_a2 > high_d2) {
                cur++;
                defender2_loss_2d++;
            } else attacker2_loss_2d++;
            if (cur == 2) _22_20++; else if (cur == 1) _22_11++; else _22_02++;
            if (high1_a2 > d4) defender2_loss_1d++; else attacker2_loss_1d++;
            if (d1 > high_d1) defender1_loss_2d++; else attacker1_loss_2d++;
            if (d1 > d4) defender1_loss_1d++; else attacker1_loss_1d++;
        }
        System.out.println("Attacker loss, 1 defender: " + attacker_loss_1d);
        System.out.println("Defender loss, 1 defender: " + defender_loss_1d);
        System.out.println("Ration: " + (float) attacker_loss_1d / ((float) i));
        System.out.println("");
        System.out.println("Attacker loss, 2 defender: " + attacker_loss_2d);
        System.out.println("Defender loss, 2 defender: " + defender_loss_2d);
        System.out.println("2:0=" + _32_20 + "  1:1=" + _32_11 + "  0:2=" + _32_02);
        System.out.println("Ration: " + (float) attacker_loss_2d / ((float) 2 * i));
        System.out.println("");
        System.out.println("2 Attacker loss, 1 defender: " + attacker2_loss_1d);
        System.out.println("2 Defender loss, 1 defender: " + defender2_loss_1d);
        System.out.println("Ration: " + (float) attacker2_loss_1d / ((float) i));
        System.out.println("");
        System.out.println("2 Attacker loss, 2 defender: " + attacker2_loss_2d);
        System.out.println("2 Defender loss, 2 defender: " + defender2_loss_2d);
        System.out.println("2:0=" + _22_20 + "  1:1=" + _22_11 + "  0:2=" + _22_02);
        System.out.println("Ration: " + (float) attacker2_loss_2d / ((float) 2 * i));
        System.out.println("");
        System.out.println("1 Attacker loss, 1 defender: " + attacker1_loss_1d);
        System.out.println("1 Defender loss, 1 defender: " + defender1_loss_1d);
        System.out.println("Ration: " + (float) attacker1_loss_1d / ((float) i));
        System.out.println("");
        System.out.println("1 Attacker loss, 2 defender: " + attacker1_loss_2d);
        System.out.println("1 Defender loss, 2 defender: " + defender1_loss_2d);
        System.out.println("Ration: " + (float) attacker1_loss_2d / ((float) i));
    }
}
