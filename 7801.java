public class startup {

    public static void main(String[] args) {
        log("This is where you put the code to start the program.");
        log("How total players?: ");
        log("How many players are a human?: ");
        log("Here, you would roll the dice.");
        log("This is where you launch the constructor for the game.");
        @SuppressWarnings("unused") monopolium game = new monopolium(4);
    }

    private static void log(String str) {
        System.out.println(str);
    }

    @SuppressWarnings("unused")
    private static void log(int str) {
        System.out.println(str);
    }
}
