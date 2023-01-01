public class Skills {

    public Skills(mudclient mc) {
        this.client = mc;
        for (int i = 0; i < 30; i++) {
            level[i] = 1;
            xp[i] = 0;
        }
    }

    public int getXP(int level) {
        int points = 0;
        int output = 0;
        for (int lvl = 1; lvl <= level; lvl++) {
            points += Math.floor((double) lvl + 360.0 * Math.pow(2.0, (double) lvl / 8.0));
            if (lvl >= level) return output;
            output = (int) Math.floor(points / 8);
        }
        return 0;
    }

    public int getLevel(double xp) {
        int points = 0;
        int output = 0;
        for (int lvl = 1; lvl <= 150; lvl++) {
            points += Math.floor((double) lvl + 360.0 * Math.pow(2.0, (double) lvl / 8.0));
            output = (int) Math.floor(points / 8);
            if (output >= xp) return lvl;
        }
        return 0;
    }

    public int[] level = new int[30];

    public int[] xp = new int[30];

    public String skills[] = { "Life", "Wizard" };

    public mudclient client = null;
}
