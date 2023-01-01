class BreakWithLabel {

    void f() {
        loop: while (true) {
            if (true) {
                break loop;
            }
            break;
        }
    }
}
