import swarm.Globals;
import swarm.defobj.Zone;
import swarm.objectbase.SwarmObjectImpl;

public class SimpleBug extends SwarmObjectImpl {

    FoodSpace myFoodSpace;

    public int xPos;

    public int yPos;

    public int bugNumber;

    int worldXSize;

    int worldYSize;

    public SimpleBug(Zone aZone, FoodSpace fSpace, int X, int Y, int bNum) {
        super(aZone);
        myFoodSpace = fSpace;
        worldXSize = myFoodSpace.getSizeX();
        worldYSize = myFoodSpace.getSizeY();
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
        if (myFoodSpace.getValueAtX$Y(xPos, yPos) == 1) {
            myFoodSpace.putValue$atX$Y(0, xPos, yPos);
            System.out.println("Bug " + bugNumber + " has found food at " + xPos + ", " + yPos);
        }
    }

    public void reportPosition() {
        System.out.println("Bug " + bugNumber + " is at " + xPos + ", " + yPos);
    }
}
