public abstract class Stat implements Levelable {

    private int level;

    private int maxlevel;

    private int experience;

    private int expToLevelUp;

    private int maxexperience;

    public int getLevel() {
        return level;
    }

    public boolean levelUp() {
        if (experience < expToLevelUp || level == maxlevel) return false; else {
            level++;
            return true;
        }
    }

    public int getExperience() {
        return experience;
    }

    public boolean addExperience(int gained) {
        if (experience == maxexperience) return false; else {
            experience += gained;
            while (experience > maxexperience) experience--;
            return true;
        }
    }

    public int getExpToNextLevel() {
        return expToLevelUp;
    }
}
