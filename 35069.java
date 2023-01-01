public class Inventory {

    boolean Gameboy = false;

    boolean GoldenWood = false;

    boolean GoldenSail = false;

    boolean PlasticShovel = false;

    boolean Lantern = false;

    public boolean Gameboy(boolean mutator) {
        Gameboy = mutator;
        return Gameboy;
    }

    public boolean GoldenWood(boolean mutator) {
        GoldenWood = mutator;
        return GoldenWood;
    }

    public boolean GoldenSail(boolean mutator) {
        GoldenSail = mutator;
        return GoldenSail;
    }

    public boolean PlasticShovel(boolean mutator) {
        PlasticShovel = mutator;
        return PlasticShovel;
    }

    public boolean Lantern(boolean mutator) {
        Lantern = mutator;
        return Lantern;
    }
}
