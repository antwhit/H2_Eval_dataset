import agents.IAgent;
import agents.LoneAgent;
import mazes.*;
import referees.OnePlayerReferee;
import algorithms.*;

public class ChristianCrossMaze {

    public static void main(String args[]) throws Exception {
        int taille = 20;
        AliceMaze cnossos = new AliceMaze(taille, taille);
        int design[][] = new int[taille][];
        for (int i = 0; i < taille; i++) {
            design[i] = new int[taille];
            for (int j = 0; j < taille; j++) design[i][j] = 0;
        }
        for (int i = 0; i < taille / 4; i++) for (int j = 0; j < taille / 4; j++) design[i][j] = 1;
        for (int i = 0; i < taille / 4; i++) for (int j = 3 * taille / 4; j < taille; j++) design[i][j] = 1;
        for (int i = 3 * taille / 4; i < taille; i++) for (int j = 0; j < taille / 4; j++) design[i][j] = 1;
        for (int i = 3 * taille / 4; i < taille; i++) for (int j = 3 * taille / 4; j < taille; j++) design[i][j] = 1;
        for (int i = taille / 4; i < taille; i++) for (int j = 1 + (7 * taille / 16); j < 9 * taille / 16; j++) design[i][j] = 1;
        for (int i = 1 + (7 * taille / 16); i < 9 * taille / 16; i++) for (int j = (taille / 4) - 3; j < (3 * taille / 4) + 3; j++) design[i][j] = 1;
        cnossos.setDesign(design);
        WatkinsSelector sql = new WatkinsSelector(0.9);
        double epsilon = 0.5;
        sql.setEpsilon(epsilon);
        sql.setAlphaDecayPower(0.5);
        IAgent zero07 = new LoneAgent(cnossos, sql);
        OnePlayerReferee arbitre = new OnePlayerReferee(zero07);
        arbitre.setMaxIter(300);
        double reward = 0;
        int tailleEpisode = 100;
        for (int episode = 1; episode < 100000; episode++) {
            if (episode == 50000) arbitre.setGraphical();
            cnossos.randomInitialState();
            arbitre.episode(cnossos.defaultInitialState());
            reward += arbitre.getRewardForEpisode();
            if ((episode % tailleEpisode == 0) && (episode != 0)) {
                System.out.println(episode + " " + (reward / (0.0 + tailleEpisode)) + " " + epsilon);
                reward = 0.0;
            }
            epsilon *= 0.9999;
            sql.setEpsilon(epsilon);
        }
    }
}
