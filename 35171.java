import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class is wraps the Java utility Linked List to create a list of bots.
 * It is used in GameApplet to keep track of the bots.
 * @author Sam
 *
 */
public class BotList {

    private LinkedList<Bot> botList;

    public BotList() {
        botList = new LinkedList<Bot>();
    }

    public void addBot(Bot b) {
        botList.addFirst(b);
    }

    public boolean deleteBot(Bot b) {
        return botList.remove(b);
    }

    public Bot getNextBot() {
        return botList.peekLast();
    }

    public Bot useNextBot(Terrain[][] map) {
        Bot b = botList.removeLast();
        b.act(map);
        botList.addFirst(b);
        return b;
    }

    public boolean isEmpty() {
        return botList.isEmpty();
    }

    public Bot botAt(int x, int y) {
        ListIterator<Bot> botIter = botList.listIterator();
        Bot b;
        while (botIter.hasNext()) {
            b = botIter.next();
            if (b.getXLoc() == x && b.getYLoc() == y) return b;
        }
        return null;
    }
}
