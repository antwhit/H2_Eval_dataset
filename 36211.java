class A {

    void a() throws AException {
        int a = 3;
        if (2 < a) {
            throw new AException("abc");
        }
    }
}
