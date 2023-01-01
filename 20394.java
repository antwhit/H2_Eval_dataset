public abstract class Equipment {

    public abstract int getNum();

    public abstract int getWeight();

    public abstract String getName();
}

abstract class Weapon extends Equipment {

    public abstract int getDam();

    public abstract int getClipSize();

    public abstract int getMaxAmmo();

    public abstract int getRange();
}

class Knife extends Weapon {

    public int getNum() {
        return 1;
    }

    public int getDam() {
        return 100;
    }

    public int getClipSize() {
        return -1;
    }

    public int getMaxAmmo() {
        return -1;
    }

    public int getRange() {
        return 0;
    }

    public int getWeight() {
        return 1;
    }

    public String getName() {
        return "Dagger of Aardwolf";
    }
}
