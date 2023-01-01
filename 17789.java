import java.util.ArrayList;
import java.util.List;
import ch.fusun.baron.core.injection.Inject;
import ch.fusun.baron.player.Player;
import ch.fusun.baron.player.api.PlayerService;
import ch.fusun.baron.turn.TurnTask;

/**
 * Removes nobles that are dead and don't have any children
 */
public class RemoveUnimportantNoblesTurnTask implements TurnTask {

    @Inject
    private transient PlayerService playerService;

    /**
	 * Kryo
	 */
    public RemoveUnimportantNoblesTurnTask() {
    }

    @Override
    public void execute() {
        List<Player> playersToRemove = new ArrayList<Player>();
        for (Player player : playerService.getAllPlayers()) {
            if (player.isDead() && !player.hasChildren()) {
                playersToRemove.add(player);
            }
        }
        playerService.removePlayers(playersToRemove);
    }
}
