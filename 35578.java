import java.awt.Color;
import java.util.Scanner;

public class SeeingDouble {

    public static void main(String[] args) {
        ColoredRectangle r = new ColoredRectangle();
        System.out.println("Enter when ready");
        Scanner stdin = new Scanner(System.in);
        stdin.nextLine();
        r.paint();
        r.x = 10;
        r.y = 30;
        r.color = Color.GREEN;
        r.paint();
        r.x = 290;
        r.y = 30;
        r.color = Color.BLACK;
        r.paint();
        r.x = 290;
        r.y = 340;
        r.color = Color.RED;
        r.paint();
    }
}
