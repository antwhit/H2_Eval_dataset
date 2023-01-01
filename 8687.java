public class err11 {

    public static void main(String args[]) {
        int i;
        short ary1[] = { 12, 23, 34, 45, 56, 67, 78, 89, 90, 111 };
        for (i = 0; i < 10; i++) {
            switch(ary1[i]) {
                case 111:
                    System.out.println("OK");
                default:
                    break;
            }
        }
    }
}
