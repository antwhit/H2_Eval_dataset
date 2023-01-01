class C {

    void m1() {
        boolean b = false;
        if (!b) {
        }
        if (b) {
            if (!b) {
            } else {
            }
        }
        if (b) {
            if (!b) {
            }
        }
    }

    void m2() {
        boolean b = false;
        if (!b) {
            if (b) {
                if (!b) {
                } else {
                }
            }
        }
        if (b) {
            if (!b) {
            } else {
            }
        } else {
        }
    }
}
