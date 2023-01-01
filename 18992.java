class SwitchTest {

    public static void switchTest(int value) {
        System.out.println(value);
        switch(value) {
            case 2:
                System.out.println("Two");
                break;
            case 3:
                System.out.println("Three");
                break;
            default:
                System.out.println("Not two or three");
                break;
        }
        switch(value) {
            case 2:
                System.out.println("Two again");
                break;
            case 102:
                System.out.println("One hundred and two");
                break;
            default:
                System.out.println("Not 2 or 102");
                break;
        }
        switch(value) {
            case 3:
                System.out.println("Three again");
                break;
            case 103:
                System.out.println("One hundred and three");
                break;
            case -1000000003:
                System.out.println("Minus one billion and three");
                break;
            default:
                System.out.println("Not 3, 103, or -1000000003");
                break;
        }
    }

    public static void main(String[] args) {
        switchTest(1);
        switchTest(2);
        switchTest(3);
        switchTest(4);
        switchTest(100);
        switchTest(102);
        switchTest(103);
        switchTest(1000);
        switchTest(-1000000003);
    }
}
