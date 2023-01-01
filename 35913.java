public class ExitMidlet extends javax.microedition.midlet.MIDlet {

    public void startApp() {
        System.out.println("ExitMidlet: calling System.exit (should get an exception)");
        try {
            System.exit(1);
        } catch (SecurityException e) {
            System.out.println("... tried to call System.exit(1) and got an exception:");
            e.printStackTrace();
        }
        System.out.println("ExitMidlet exiting");
        notifyDestroyed();
    }

    public static void main(String ignore[]) {
        new ExitMidlet().startApp();
    }

    public void destroyApp(boolean ignore) {
    }
}
