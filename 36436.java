import java.util.Arrays;

public class TheHexagonsDivTwo {

    public long count(int n, int k) {
        int[] buckets = new int[k];
        for (int i = 0; i < k; i++) {
            buckets[i] = n / k;
        }
        for (int i = 1; i <= n % k; i++) {
            buckets[i]++;
        }
        long sum = 0;
        for (int i = 0; i < k; i++) {
            if (n - buckets[i] >= 6) {
                int[] buckets2 = Arrays.copyOf(buckets, buckets.length);
                buckets2[i] = 0;
                int[] cells = new int[6];
                Arrays.fill(cells, -1);
                sum += count(0, cells, buckets2) * buckets[i];
            }
        }
        return sum / 6;
    }

    private long count(int index, int[] cells, int[] buckets) {
        long sum = 0;
        for (int b = 0; b < buckets.length; b++) {
            int prev = (index + 5) % 6;
            int next = (index + 1) % 6;
            if (b != cells[prev] && b != cells[next] && buckets[b] > 0) {
                if (index < 5) {
                    int[] cells2 = Arrays.copyOf(cells, cells.length);
                    cells2[index] = b;
                    int[] buckets2 = Arrays.copyOf(buckets, buckets.length);
                    buckets2[b]--;
                    sum += count(index + 1, cells2, buckets2) * buckets[b];
                } else sum += buckets[b];
            }
        }
        return sum;
    }

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        System.out.println(new TheHexagonsDivTwo().count(20, 5));
    }
}
