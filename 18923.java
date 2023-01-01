public class ExceptionHandlerTestklasse {

    public ExceptionHandlerTestklasse() {
    }

    public void m() {
        try {
            new java.io.FileInputStream("Aetsch");
        } catch (java.io.FileNotFoundException e) {
        } catch (RuntimeException e) {
        }
    }
}
