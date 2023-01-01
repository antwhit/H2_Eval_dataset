/***********************************************************************
 * Command class
 * 
 * Implements the base functionality of commands
 *
 * Signature:
 *  public String name ();
 *  public boolean execute (String[] arguments);
 ***********************************************************************/
public abstract class Command {

    private String name;

    protected Command(String n) {
        name = n;
    }

    public String name() {
        return name;
    }

    public abstract boolean execute(String[] args);
}
