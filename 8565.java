public class Categorii {

    float Ezi, E1, E2, F;

    int n1 = 500, n2 = 1000, p1 = 1, p2 = 2, p3 = 3;

    int p = 1;

    int pr = 2;

    int pzi = 4;

    int pn = 2;

    int pv = 3;

    int pg = 4;

    int pa = 1;

    float Ev = 10, Eg = 10, En = 10;

    void calculateCS(float E, int N) {
        Ezi = E / N;
        if (Ezi <= n1) {
            F = E * p1;
        }
        if ((n1 <= Ezi) || (Ezi <= n2)) {
            E1 = n1 * N;
            F = E1 * p1 + (E - E1) * p2;
        }
        if (Ezi > n2) {
            E1 = n1 * N;
            E2 = (n2 - n1) * N;
            F = E1 * p1 + E2 * p2 + (E - E1 - E2) * p3;
            System.out.println("Factura este de " + F + " lei!");
        }
    }

    void calculateCD(float E) {
        F = E * p;
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCR(float E, int N) {
        F = E * p + N * pr;
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCR2(float E, float En, int N) {
        Ezi = E / N;
        System.out.println("Consumul aproximativ pe zi este de " + Ezi);
        F = Ezi * pzi + En * pn + N * pr;
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCR3(float E, float Ev, float En, float Eg, int N) {
        F = Ev * pv + En * pn + Eg * pg + N * pr;
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCTP(float E, int N) {
        F = E * p + N * pr;
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCI(float E, int N) {
        int unkWzi = 1;
        Ezi = E / N;
        if (Ezi <= unkWzi) {
            F = pa * N;
        } else if (Ezi > unkWzi) {
            F = pa * N + (E - 1 * N) * p;
        }
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCP(float E, int N) {
        F = E * p + N * pr;
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCP2(float E, float En, int N) {
        F = Ezi * pzi + En * pn + N * pr;
        System.out.println("Factura este de " + F + " lei!");
    }

    void calculateCP3(float E, float En, float Ev, float Eg, int N) {
        F = Ev * pv + En * pn + Eg * pg + N * pr;
        System.out.println("Factura este de " + F + " lei!");
    }
}
