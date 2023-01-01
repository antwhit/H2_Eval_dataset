/**
 * Excepcao CartaoRetido
 *
 * @author PO
 * @version 1.0
 **/
public class CartaoRetido extends Exception {

    public CartaoRetido() {
    }

    public CartaoRetido(String msg) {
        super(msg);
    }
}
