class T3107v15 {

    void foo(int i) {
        switch(i) {
            case 0:
            case (("\b" == "") ? 1 : 0):
            case (("\10" == "") ? 2 : 0):
        }
    }
}
