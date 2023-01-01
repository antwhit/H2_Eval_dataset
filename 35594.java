import swarm.Globals;
import swarm.defobj.Zone;
import swarm.space.Discrete2dImpl;

public class FoodSpace extends Discrete2dImpl {

    public FoodSpace(Zone aZone, int xSize, int ySize) {
        super(aZone, xSize, ySize);
        fastFillWithValue(0);
    }

    public void seedFoodWithProb(double seedProb) {
        int x, y;
        for (y = 0; y < getSizeY(); y++) for (x = 0; x < getSizeX(); x++) if (Globals.env.uniformDblRand.getDoubleWithMin$withMax(0.0, 1.0) <= seedProb) putValue$atX$Y(1, x, y);
    }
}
