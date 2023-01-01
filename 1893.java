public abstract class Flow {

    int g;

    int[] ain;

    void skip() {
        for (int i = 0; i < 3; i++) {
            if (g > 5) {
                for (int j = 0; j < 5; j++) {
                    g++;
                }
            }
            i--;
        }
    }

    public void switchWhileTest() {
        int dir = g;
        int x = 0;
        int y = 0;
        boolean done = false;
        g = 5;
        switch(dir) {
            case 1:
                while (!done) {
                    done = true;
                    if (g > 7) g = g - 4;
                    x = g;
                    y = g;
                    if (x > 7) x = x - 4;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (ain[j] == x + i && ain[j] == y) done = false;
                        }
                    }
                }
                for (int i = 0; i < 5; i++) {
                    ain[g] = x + i;
                    ain[g] = y;
                    g += 1;
                }
                break;
            case 2:
                while (!done) {
                    done = true;
                    x = g;
                    y = g;
                    if (y > 7) y = y - 4;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (ain[j] == x && ain[j] == y + i) done = false;
                        }
                    }
                }
                for (int i = 0; i < 4; i++) {
                    ain[g] = x;
                    ain[g] = y + i;
                    g += 1;
                }
                break;
            case 3:
                big: for (; ; ) {
                    x = g;
                    y = g;
                    if (y > 7) y = y - 4;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (ain[j] == x && ain[j] == y + i) continue big;
                        }
                    }
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    ain[g] = x;
                    ain[g] = y + i;
                    g += 1;
                }
                break;
        }
    }

    /**
     * This was an example where our flow analysis didn't find an
     * elegant solution.  The reason is, that we try to make 
     * while(true)-loops as small as possible (you can't see the real
     * end of the loop, if it is breaked there like here).
     *
     * Look at the assembler code and you know why my Decompiler had
     * problems with this.  But the decompiler did produce compilable
     * code which produces the same assembler code.  
     *
     * The solution was, to make switches as big as possible, the whole
     * analyze methods were overworked.
     */
    void WhileTrueSwitch() {
        int i = 1;
        while (true) {
            switch(i) {
                case 0:
                    return;
                case 1:
                    i = 5;
                    continue;
                case 2:
                    i = 6;
                    continue;
                case 3:
                    throw new RuntimeException();
                default:
                    i = 7;
                    return;
            }
        }
    }

    abstract int test();

    /**
     * This tests shorts and empty ifs.  Especially the no op ifs can
     * be optimized to very unusual code.
     */
    public void shortIf() {
        while (g != 7) {
            if (g == 5) return; else if (g != 4) break; else if (g == 2) shortIf(); else return;
            if (g != 7) shortIf(); else {
                shortIf();
                return;
            }
            if (g != 1) break; else if (g == 3) shortIf(); else break;
            if (g + 5 == test()) {
            }
            if (g == test()) continue;
        }
        while (g == 3) {
            if (test() == 4 || test() == 3 && test() == 2) ;
            if (test() == 4 || test() == 3 && test() == 2) continue;
        }
        while (g == 2) {
            if ((long) (test() + test() - test()) == (long) (g - 4)) ;
            if ((long) (test() + test() - test()) == (long) (g - 4)) continue;
        }
        System.err.println("Hallo");
    }
}
