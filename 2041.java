/**
	MovableObjects are objects that can be seen and moved (eg. ships)!
	@author Jim Sproch
	@version 0.1a beta
*/
public interface MovableObject extends DisplayableObject {

    public void move();

    public int getID();

    public int getDirection();

    public void setField(AForceEnv tempfiled);

    public Boundary relaventBoundary(int direction);
}
