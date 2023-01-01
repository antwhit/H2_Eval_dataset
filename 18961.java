public class ExceptionMapper {

    public static final RuntimeException mapException(Exception e) throws RuntimeException {
        RuntimeException ee = new RuntimeException();
        return (ee);
    }

    class A {

        void f() {
            try {
            } catch (Exception e) {
                throw ExceptionMapper.mapException();
            }
        }
    }
}
