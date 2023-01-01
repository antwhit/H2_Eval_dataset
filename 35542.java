public class IO {

    boolean[] state;

    boolean[] direction;

    public IO() {
    }

    public void setWidth(int width) {
        state = new boolean[width];
        direction = new boolean[width];
    }

    public void setDirection(int stateMask) {
        for (int i = 0; i < state.length; i++) {
            if ((stateMask & 1) == 1) direction[i] = true; else direction[i] = false;
            stateMask >>= 1;
        }
    }

    public void setDirection(int stateMask1, int stateMask2) {
        for (int i = 0; i < 8; i++) {
            if ((stateMask1 & 1) == 1) direction[i] = true; else direction[i] = false;
            stateMask1 >>= 1;
        }
        for (int i = 8; i < state.length; i++) {
            if ((stateMask2 & 1) == 1) direction[i] = true; else direction[i] = false;
            stateMask2 >>= 1;
        }
    }

    public boolean getOutput(int index) {
        if (index < this.state.length) if (!direction[index]) return state[index];
        return false;
    }

    public void setInput(int index, boolean state) {
        if (index < this.state.length) if (direction[index]) this.state[index] = state;
    }

    public int getWidth() {
        return state.length;
    }
}
