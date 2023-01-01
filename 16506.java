import gaming.*;

public class monopolium {

    private int numberOfPlayers;

    @SuppressWarnings("unused")
    private monopolium() {
        ;
    }

    public monopolium(int numberOfPlayers) {
        log("Setting the number of players to " + numberOfPlayers + "...");
        setNumberOfPlayers(numberOfPlayers);
        log("Set the number of players to " + getNumberOfPlayers() + "...");
        log("Instantiating an array of player objects...");
        player[] player = new player[getNumberOfPlayers()];
        log("Creating the array of player objects...");
        for (int i = 0; i <= getNumberOfPlayers() - 1; i++) {
            log("\t...Creating player #" + (i + 1));
            player[i] = new player();
        }
        log("Creating die to roll...");
        die die = new die();
        log("Die created successfully...");
        log("Creating dice representation...");
        int dieroll1, dieroll2;
        log("Dice representation creation complete...");
        log("Beginning play loop...");
        while (hasAnyoneWon(player)) {
            for (int i = 0; i <= player.length; i++) {
                log("It is now player " + (i + 1) + "'s turn.");
                if (player[i].getIsBankrupt() == true) {
                    log("Player #" + (i + 1) + " is bankrupt and can no longer play.");
                    break;
                } else if (player[i].getIsInJail() == true) {
                    log("Player #" + (i + 1) + "is in jail.");
                }
                player[3].setIsBankrupt(true);
                player[2].setIsInJail(true);
            }
        }
        ;
        log("Play loop is concluded. Game is over...");
    }

    private void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    private int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    private void log(String string) {
        System.out.println(string);
    }

    private boolean hasAnyoneWon(player player[]) {
        int howManyLeft = 0;
        for (int i = 0; i <= getNumberOfPlayers() - 1; i++) {
            if (player[i].getIsBankrupt() == false) {
                howManyLeft++;
            }
        }
        if (howManyLeft > 1) {
            return true;
        } else {
            return false;
        }
    }
}
