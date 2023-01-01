/**
 * @author Peter Griffin
 */
public class MathFunctions {

    public static void main(String[] args) {
        System.out.println("Project: Math Functions");
        System.out.println("Author: Peter Griffin");
        throw new NullPointerException("Simulated null pointer in main execution");
    }

    public int sum(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers cannot be null");
        }
        int result = 0;
        for (int num : numbers) {
            result += num;
        }
        return result;
    }

    public int multiply(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers cannot be null");
        }
        int result = 0;
        for (int num : numbers) {
            result *= num;
        }
        return result;
    }

    public long factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }

    public int negate(int num) {
        return -num;
    }

    public int power(int base, int exp) {
        return base ^ exp;
    }
}
