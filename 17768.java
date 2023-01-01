import java.util.Scanner;

public class kalkulator {

    public static void main(String[] args) {
        int pil;
        double bil1, bil2, hasil;
        Scanner input = new Scanner(System.in);
        System.out.println("Kalkulator");
        System.out.println("================");
        System.out.println("1. Penambahan");
        System.out.println("2. Pengurangan");
        System.out.println("3. Perkalian");
        System.out.println("4. Pembagian");
        System.out.println("================");
        System.out.printf("Masukkan Pilihan Anda : ");
        pil = input.nextInt();
        System.out.printf("Masukkan Bilangan pertama : ");
        bil1 = input.nextDouble();
        System.out.printf("Masukkan Bilangan kedua : ");
        bil2 = input.nextDouble();
        switch(pil) {
            case 1:
                hasil = bil1 + bil2;
                System.out.printf("Hasil dari penambahan adalah  " + hasil);
                break;
            case 2:
                hasil = bil1 - bil2;
                System.out.printf("Hasil dari pengurangan adalah  " + hasil);
                break;
            case 3:
                hasil = bil1 * bil2;
                System.out.printf("Hasil dari perkalian adalah  " + hasil);
                break;
            case 4:
                hasil = bil1 / bil2;
                System.out.printf("Hasil dari pembagian adalah  " + hasil);
                break;
            default:
                System.out.println("Maaf, pilihan yang Anda Masukkan salah");
        }
    }
}
