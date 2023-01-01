class Rule19 {

    int x = 1;

    int y = 2;

    double f = 1.0;

    double g = ((double) x) / ((float) y);

    double h = x / ((float) y);

    double i = ((float) x) / y;

    float j = x / y;

    public void f() {
        int x = 1;
        int y = 2;
        double f = 1.0;
        double g = ((double) x) / ((float) y);
        double h = x / ((float) y);
        double i = ((float) x) / y;
        final float j = x / y;
    }

    float f_array[] = { 4 / 5 };

    double g_array[] = { ((double) x) / ((float) y) };

    double h_array[] = { 4 / 5, ((float) x) / y };
}
