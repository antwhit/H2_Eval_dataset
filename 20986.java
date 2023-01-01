import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import game.Game;
import graphics.Settings;

public class CubesRock {

    private static class StateManager extends game.StateManager {

        public StateManager() {
            addState("the game", new TheGame());
            addState("start", new StartState());
            addState("after game", new AfterGameState());
        }
    }

    public static void main(String[] args) {
        if (false) {
            try {
                System.setErr(new PrintStream("stderr.txt"));
                System.setOut(new PrintStream("stdout.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Starting CubesRock ...");
        util.GlobalRandom.setRandom(new java.util.Random());
        StateManager manager = new StateManager();
        Settings settings = null;
        try {
            settings = new Settings(DirectoryManagement.getUserSpecificFile("settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            if (settings == null) return;
        }
        final Game game = new Game(manager, settings);
        game.addCommand(manager.switchState("start", new HashMap<String, Object>()));
        game.start();
    }
}
