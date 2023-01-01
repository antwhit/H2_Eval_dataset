import java.util.Random;

/**
 * @author nnelson
 *
 */
public class Player {

    byte intelligence, strength, speed, wisdom, constitution, agility = 8;

    int xPos, yPos = 0;

    byte currentLevel = 1;

    Player() {
        Random statGen = new Random();
        intelligence += statGen.nextInt(6);
        strength += statGen.nextInt(6);
        speed += statGen.nextInt(6);
        wisdom += statGen.nextInt(6);
        constitution += statGen.nextInt(6);
        agility += statGen.nextInt(6);
    }

    int AP = 0;

    public int move(int x, int y) {
        return 0;
    }

    public void myTurn() {
        AP = this.speed;
    }
}
