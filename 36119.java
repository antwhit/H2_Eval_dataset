import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

public class Client {

    public static void main(String[] args) {
        System.out.println("AAA");
        try {
            ORB o = ORB.init(args, null);
            org.omg.CORBA.Object objRef = o.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
            NameComponent nc = new NameComponent("Library", "");
            NameComponent path[] = { nc };
            Library lib = LibraryHelper.narrow(ncRef.resolve(path));
            System.out.println(lib.getDupa());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
