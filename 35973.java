public class Test8 {

    public void foo() {
        int i = 5;
        int j = 6;
        if (i == j) return;
        for (; i > j; ) ;
        for (; i < j; ) i++;
        while (i != j) ;
        while (i != j) ;
        ;
        while (i == j) i--;
        do ; while (i == j);
        do {
            ;
        } while (i == j);
        do i++; while (i == j);
        ;
        int k;
        if (i == j) i = 0; else i = 1;
        label1: {
        }
        label2: {
            int l;
        }
        switch(i) {
            case i == 0:
                i = 10;
            default:
                i = 11;
        }
    }
}
