class UnrefMethPar {

    public int f(int a, int b) {
        return (a + 2);
    }

    public int g(int[] x, boolean b[], int[] y[]) {
        b = new boolean[10];
        return (3);
    }
}
