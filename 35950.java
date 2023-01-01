class CEO extends Manager {

    public CEO(String name) {
        super(name);
    }

    public String getPosition() {
        return "CEO";
    }

    public void disownMemory() {
        swigCMemOwn = false;
    }
}

public class runme {

    static {
        try {
            System.loadLibrary("example");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        CEO e = new CEO("Alice");
        System.out.println(e.getName() + " is a " + e.getPosition());
        System.out.println("Just call her \"" + e.getTitle() + "\"");
        System.out.println("----------------------");
        EmployeeList list = new EmployeeList();
        e.disownMemory();
        list.addEmployee(e);
        System.out.println("----------------------");
        System.out.println("(position, title) for items 0-3:");
        System.out.println("  " + list.get_item(0).getPosition() + ", \"" + list.get_item(0).getTitle() + "\"");
        System.out.println("  " + list.get_item(1).getPosition() + ", \"" + list.get_item(1).getTitle() + "\"");
        System.out.println("  " + list.get_item(2).getPosition() + ", \"" + list.get_item(2).getTitle() + "\"");
        System.out.println("  " + list.get_item(3).getPosition() + ", \"" + list.get_item(3).getTitle() + "\"");
        System.out.println("----------------------");
        list.delete();
        System.out.println("----------------------");
        System.out.println("java exit");
    }
}
