public class Bubblesort extends SortierAlgorithmus {

    @Override
    public void sortiereArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (vergleicheKleinerGleich(arr[j + 1], arr[j])) {
                    vertausche(arr, j, j + 1);
                }
            }
        }
    }
}
