public class FooCorporation {

    public static void pay(double pay, double hours) {
        if (pay < 8) {
            System.out.println("Error: pay less than minimum wage ($8.00)");
        } else if (hours > 60) {
            System.out.println("Error: too much overtime");
        } else if (hours > 40) {
            hours = hours - 40;
            double overtime = hours * 1.5 * pay;
            double total = overtime + 40 * pay;
            System.out.println(total);
        } else {
            double total = hours * pay;
            System.out.println(total);
        }
    }

    public static void main(String[] arguments) {
        double pay_one = 7.5;
        double hours_one = 35;
        double pay_two = 8.2;
        double hours_two = 47;
        double pay_three = 10.0;
        double hours_three = 73;
        pay(pay_one, hours_one);
        pay(pay_two, hours_two);
        pay(pay_three, hours_three);
    }
}
