import mucode.*;
import mucode.util.*;

public class Provider {

    public static void main(String[] args) {
        MuServer s = new MuServer();
        new Launcher(s).launch(args, 0);
        ClassSpace priv = s.getPrivateClassSpace();
        ClassSpace shared = s.getSharedClassSpace();
        try {
            System.out.println("Exporting the classes...");
            priv.copyClassTo("Toolkit", shared);
            priv.copyClassTo("Hammer", shared);
            priv.copyClassTo("Nail", shared);
            System.out.println("Classes exported. Shared class space: " + shared);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
