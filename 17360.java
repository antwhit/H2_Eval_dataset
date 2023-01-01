public class MUHex {

    public char terrain;

    public int elevation;

    public MUHex() {
        terrain = '?';
        elevation = 0;
    }

    public MUHex(char terrain, int elevation) {
        this.terrain = terrain;
        this.elevation = elevation;
    }

    public int absElevation() {
        return Math.abs(elevation);
    }
}
