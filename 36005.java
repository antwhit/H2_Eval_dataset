public class errorSQL extends Exception {

    private static final long serialVersionUID = 1L;

    public errorSQL() {
        super("Problemas SQL");
    }

    public errorSQL(String pText) {
        super(pText);
    }
}
