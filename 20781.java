/***********************************************************************
 * Scan class
 * 
 * Implements the 'scan' command
 * 
 * Signature: public static Scan create (); public String name (); public
 * boolean execute (String[] arguments);
 ***********************************************************************/
public class Scan extends Command {

    protected Scan() {
        super("scan");
    }

    public static Scan create() {
        return new Scan();
    }

    public boolean execute(String[] args) {
        if (SpaceGame.me().energy() < 10) {
            SpaceGame.me().report("sensors damaged");
            return false;
        }
        for (String str : SpaceGame.map().picture()) SpaceGame.me().report(str);
        return false;
    }
}
