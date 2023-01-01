class Unreach {

    static int j = 0;

    final double[] d = { 0.5, 0.4, 0.3 };

    public static final void m(int i) throws Exception {
        switch(i) {
            case 1:
                j += 2;
                for (; ; ) {
                    j += 3;
                    switch(j) {
                        case 2:
                            break;
                        default:
                            j += 4;
                            return;
                    }
                }
            default:
                j += 5;
        }
    }
}
