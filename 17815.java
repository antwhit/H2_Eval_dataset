public class Brick {

    public static int old_colors[] = { 0x9a9a44, 0x464699, 0x9a9a44, 0x469946, 0x464699, 0xad4e4e, 0x469946 };

    public static int colors[] = { 0xffd800, 0x0000ff, 0xffd800, 0x00c400, 0x0000ff, 0xe93100, 0x00c400 };

    private int types[][][][] = { { { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } } }, { { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } }, { { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } } }, { { { 0, 0, 1, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } }, { { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, { { 0, 1, 1, 1 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } }, { { 0, 0, 1, 0 }, { 0, 0, 1, 1 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } }, { { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, { { 0, 0, 0, 1 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } }, { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, { { 0, 1, 1, 1 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } } }, { { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, { { 0, 1, 1, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } }, { { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, { { 0, 1, 0, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } } }, { { { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, { { 0, 1, 1, 0 }, { 0, 0, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } } }, { { { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, { { 0, 0, 1, 1 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } } } };

    public int type;

    public int rotation;

    public int xoffset, yoffset;

    public Block[] blocks;

    public Brick(int type) {
        if (type < 0 || type > 6) {
            System.err.println("Invalid Type: " + type);
        }
        this.type = type;
        this.rotation = 0;
        this.xoffset = 0;
        this.yoffset = 0;
        blocks = new Block[4];
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block(colors[type], 0, 0);
        }
        updateBlocks();
    }

    public void rotate(boolean left) {
        int rotations = types[type].length;
        if (left) {
            rotation++;
            if (rotation >= rotations) {
                rotation = 0;
            }
        } else {
            rotation--;
            if (rotation < 0) {
                rotation = rotations - 1;
            }
        }
        updateBlocks();
    }

    public void step() {
        yoffset++;
        updateBlocks();
    }

    public void left() {
        xoffset--;
        updateBlocks();
    }

    public void right() {
        xoffset++;
        updateBlocks();
    }

    public void setPosition(int x, int y) {
        xoffset = x;
        yoffset = y;
        updateBlocks();
    }

    private void updateBlocks() {
        int i = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (types[type][rotation][y][x] == 1) {
                    if (i == blocks.length) {
                        break;
                    }
                    blocks[i].update(xoffset + x, yoffset + y);
                    i++;
                }
            }
        }
    }

    public Brick clone() {
        Brick b = new Brick(type);
        b.xoffset = xoffset;
        b.yoffset = yoffset;
        b.rotation = rotation;
        for (int i = 0; i < blocks.length; i++) {
            b.blocks[i] = this.blocks[i].clone();
        }
        return b;
    }
}
