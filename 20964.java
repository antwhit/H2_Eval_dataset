import swarm.Globals;
import swarm.defobj.Zone;
import swarm.objectbase.SwarmObjectImpl;
import swarm.space.Grid2dImpl;

public class SimpleBug extends SwarmObjectImpl {

    public FoodSpace myFoodSpace;

    public Grid2dImpl myBugSpace;

    public int xPos;

    public int yPos;

    public int bugNumber;

    int worldXSize;

    int worldYSize;

    public boolean haveEaten;

    public SimpleBug(Zone aZone, FoodSpace fSpace, Grid2dImpl bSpace, int X, int Y, int bNum) {
        super(aZone);
        myFoodSpace = fSpace;
        myBugSpace = bSpace;
        worldXSize = myFoodSpace.getSizeX();
        worldYSize = myFoodSpace.getSizeY();
        xPos = X;
        yPos = Y;
        bugNumber = bNum;
        System.out.println("SimpleBug number " + bugNumber + " has been created at " + xPos + ", " + yPos);
    }

    public void randomWalk() {
        int newX, newY;
        haveEaten = false;
        newX = xPos + Globals.env.uniformIntRand.getIntegerWithMin$withMax(-1, 1);
        newY = yPos + Globals.env.uniformIntRand.getIntegerWithMin$withMax(-1, 1);
        newX = (newX + worldXSize) % worldXSize;
        newY = (newY + worldYSize) % worldYSize;
        if (myBugSpace.getObjectAtX$Y(newX, newY) == null) {
            myBugSpace.putObject$atX$Y(null, xPos, yPos);
            xPos = newX;
            yPos = newY;
            myBugSpace.putObject$atX$Y(this, xPos, yPos);
        }
        if (myFoodSpace.getValueAtX$Y(xPos, yPos) == 1) {
            myFoodSpace.putValue$atX$Y(0, xPos, yPos);
            haveEaten = true;
        }
    }

    public void reportPosition() {
        System.out.println("Bug " + bugNumber + " is at " + xPos + ", " + yPos);
    }

    public boolean reportIfEaten() {
        if (haveEaten) System.out.println("Bug " + bugNumber + " has found food at " + xPos + ", " + yPos);
        return haveEaten;
    }
}
