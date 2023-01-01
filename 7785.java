import ac.hiu.j314.elmve.graph.*;

public class SpringLink extends Link {

    public Line getLine() {
        return new SpringLine((Node) head, (Node) tail);
    }

    public boolean isDirected() {
        return false;
    }
}
