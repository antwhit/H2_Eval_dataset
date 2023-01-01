import hoverball.Session;
import FantasticThree.FantasticThree;
import TribalQueens.TribalQueens;

public class Start extends Session {

    public static void main(String[] args) {
        new Start(args);
    }

    public Start(String[] args) {
        super("Test Session", true, false, args);
        this.add(new FantasticThree("F3-1", 0xBB2222));
        this.add(new TribalQueens("TQ", 0x00FF00));
        this.controller.show();
        this.controller.follow(null);
        this.controller.screen.show();
        if (this.simulator == null) {
            return;
        }
        this.simulator.set("unit.vision", 1);
        this.simulator.state(1);
        this.simulator.state(2);
    }
}
