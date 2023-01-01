/**
 *
 * @author mathyas
 */
public class ImprimeArray {

    public <T> void imprimir(T[] var) {
        for (T e : var) {
            System.out.print(" " + e);
        }
        T a = var[0];
        System.out.println(" a = " + a);
    }

    public static void main(String[] args) {
        ImprimeArray obj = new ImprimeArray();
        Integer[] arrayInt = { 1, 2, 3, 4, 5, 3, 7, 0, 2, 5, 6 };
        String[] arrayString = { "cleber", "henriques", "barni", "cohab", "zacarias" };
        Double[] arrayDouble = { 2.71, 3.14, 8.56, 231.22 };
        obj.imprimir(arrayInt);
        System.out.println("\n");
        obj.imprimir(arrayString);
        System.out.println("\n");
        obj.imprimir(arrayDouble);
        System.out.println("\n");
    }
}
