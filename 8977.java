public class prime {

    /**
	 * һ��������
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            int j;
            for (j = 2; j < (int) (Math.sqrt(i) + 1); j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j > (int) Math.sqrt(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
