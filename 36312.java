import TitanGeo.Arc;

public class Disruptor extends weapon {

    public Disruptor(ship p, Arc a) {
        super("Disruptor", a);
        parent = p;
        damage = 4;
        setRange(6);
    }

    public Disruptor(ship p, Arc a, int d) {
        super("Disruptor", a);
        parent = p;
        damage = d;
        setRange(6);
    }
}
