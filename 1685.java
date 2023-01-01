import java.awt.*;
import java.util.*;

/**
* Keeps track of all game objects and updates them according to information 
* received from udp.
*/
public class GameManager extends Manager {

    /**
        Object responsible for getting udp data
    */
    protected Receiver receiver;

    protected TileMap map;

    protected ViewPort viewPort;

    protected String nick;

    protected GameFrame gameFrame;

    /**
        Creates GameManager with reference to current TileMap
    */
    public GameManager(TileMap map, ViewPort viewPort, int port, GameFrame gameFrame) {
        super();
        this.map = map;
        this.gameFrame = gameFrame;
        this.viewPort = viewPort;
        viewPort.setManager(this);
        receiver = new Receiver(port);
        receiver.start();
    }

    /**
        Updates game objects. First, it updates animation; then reads last data from udp
        and updates objects positions, directions, etc.        
    */
    public synchronized void update(long elapsedTime) {
        Iterator i = gameObjects.values().iterator();
        while (i.hasNext()) {
            GameObject obj = (GameObject) i.next();
            obj.updateAnimation(elapsedTime);
        }
        HashSet set = receiver.receive();
        if (set == null) {
            return;
        }
        i = set.iterator();
        while (i.hasNext()) {
            ValueObject valueObject = (ValueObject) i.next();
            String ID = valueObject.getID();
            Object object = gameObjects.get(ID);
            if (object == null) {
                if (valueObject instanceof RoachValueObject) {
                    Roach roach = new Roach(map, valueObject);
                    roach.setID(ID);
                    if (getID().equals(ID)) {
                        roach.setViewPort(viewPort);
                    }
                    addObject(ID, roach);
                } else if (valueObject instanceof FoodValueObject) {
                    SimpleFood food = new SimpleFood(valueObject);
                    food.setID(ID);
                    if (getID().equals(ID)) {
                        food.setViewPort(viewPort);
                    }
                    addObject(ID, food);
                }
            } else {
                GameObject obj = (GameObject) object;
                if (obj.isValid()) obj.setValueObject(valueObject);
            }
        }
        String chat = receiver.getChat();
        if (!chat.equals("")) {
            gameFrame.addChat(chat);
        }
        viewPort.update(elapsedTime);
    }

    public void setID(String nick) {
        this.nick = nick;
    }

    public String getID() {
        return nick;
    }
}
