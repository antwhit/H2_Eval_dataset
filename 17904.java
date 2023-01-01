public class PartitionKO1 {

    static int partition(int[] a, int i, int j) {
        int pivot = a[i];
        int p = i;
        int k = i + 1;
        while (k <= j) {
            if (a[k] < pivot) {
                int tmp = a[p];
                a[p] = a[k];
                a[k] = tmp;
            }
            k = k + 1;
        }
        int tmp2 = a[p];
        a[p] = a[i];
        a[i] = tmp2;
        return p;
    }
}
