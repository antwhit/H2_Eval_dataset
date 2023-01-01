public class SecondMain {

    public Object objectAttSecondMain = null;

    private Object objectPrivAttSecondMain;

    public Object getobjectAttSecondMain() {
        System.out.println("Entering method  SecondMaingetobjectAttSecondMain()");
        System.out.println("Exiting method SecondMaingetobjectAttSecondMain()");
        return objectAttSecondMain;
    }

    public void setobjectAttSecondMain(Object o) {
        System.out.println("Entering method  SecondMainsetobjectAttSecondMain(java.lang.Object)");
        objectAttSecondMain = o;
        System.out.println("Exiting method SecondMainsetobjectAttSecondMain(java.lang.Object)");
    }
}
