import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class ServerGameplay extends Gameplay {

    private Map<String, Player> players;

    private Grid grid;

    private Random rand;

    public ServerGameplay(EventManager eventManager, Grid grid) {
        players = new HashMap<String, Player>();
        register(eventManager);
        this.grid = grid;
        rand = new Random();
    }

    public void receiveEvent(Event event) {
        switch(event.type()) {
            case Event.COMMAND_FORWARD:
            case Event.COMMAND_BACK:
            case Event.COMMAND_LEFT:
            case Event.COMMAND_RIGHT:
            case Event.COMMAND_JUMP:
                {
                    String id = event.getString("id");
                    Player player = players.get(id);
                    if (id != null) player.receiveEvent(event);
                }
                break;
            case Event.NEW_CONNECTION:
                {
                    String id = event.getString("id");
                    float red = randColorVal();
                    float green = randColorVal();
                    float blue = randColorVal();
                    players.put(id, new Player(id, eventManager, grid, red, green, blue));
                    for (Player player : players.values()) eventManager.send(Event.PLAYER_CREATED, "id", player.id(), "x", player.x(), "y", player.y(), "z", player.z(), "orientation", player.orientation(), "red", player.red(), "green", player.green(), "blue", player.blue());
                }
                break;
            case Event.CONNECTION_CLOSED:
                {
                    String id = event.getString("id");
                    players.remove(id);
                    eventManager.send(Event.PLAYER_DESTROYED, "id", id);
                }
                break;
        }
    }

    public void update(float tpf) {
        for (Player player : players.values()) player.update(tpf);
    }

    private float randColorVal() {
        return rand.nextFloat() * 0.7f + .2f;
    }
}
