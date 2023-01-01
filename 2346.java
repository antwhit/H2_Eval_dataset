import swarm.Globals;
import swarm.defobj.Zone;
import swarm.objectbase.SwarmObjectImpl;

public class SimpleBug extends SwarmObjectImpl {

    int worldXSize;

    int worldYSize;

    public int xPos;

    public int yPos;

    public int bugNumber;

    public SimpleBug(Zone aZone, int wXSize, int wYSize, int X, int Y, int bNum) {
        super(aZone);
        worldXSize = wXSize;
        worldYSize = wYSize;
        xPos = X;
        yPos = Y;
        bugNumber = bNum;
        System.out.println("SimpleBug number " + bugNumber + " has been created at " + xPos + ", " + yPos);
    }

    public void randomWalk() {
        xPos += Globals.env.uniformIntRand.getIntegerWithMin$withMax(-1, 1);
        yPos += Globals.env.uniformIntRand.getIntegerWithMin$withMax(-1, 1);
        xPos = (xPos + worldXSize) % worldXSize;
        yPos = (yPos + worldYSize) % worldYSize;
    }

    public void reportPosition() {
        System.out.println("Bug " + bugNumber + " is at " + xPos + ", " + yPos);
    }
}
