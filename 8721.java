import bridge.JavaToFlexBridge;
import action.*;

public class Main {

    public static void main(String[] args) {
        Thread thread_1 = new Thread(JavaToFlexBridge.getInstance());
        Thread thread_2 = new Thread(new KernelApplicationLaunchAction());
        thread_1.start();
        thread_2.start();
    }
}
