public class problem21 {

    public static int sumOfDivisors(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int start = 1;
        int end = 10000;
        int sum = 0;
        for (int i = start; i < end; i++) {
            int sum1 = sumOfDivisors(i);
            int sum2 = sumOfDivisors(sum1);
            if (i == sum2 && i != sum1) {
                System.out.println("d(" + i + ")=" + sum1 + " and d(" + sum1 + ")=" + sum2);
                sum += sum1;
            }
        }
        System.out.println(sum);
    }
}
