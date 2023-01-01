public class BoomBrick {

    private int x;

    private int y;

    private int anim;

    private int anim2;

    public BoomBrick(int x, int y) {
        this.x = x;
        this.y = y;
        anim = 0;
        anim2 = 0;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int anim() {
        anim2++;
        if (anim2 == 5) {
            anim++;
            anim2 = 0;
        }
        return anim;
    }
}
