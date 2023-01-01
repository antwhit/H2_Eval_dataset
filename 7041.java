/**
 * Error indirection class.
 *
 * Some VMs will load this class and fail on the "new" call, others will
 * refuse to load this class at all.
 */
public class Indirect {

    public static void main() {
        Base base = new Base();
    }
}
