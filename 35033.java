import java.util.Date;

/**
 * This is the simplest task form: a class implementing the {@link Runnable}
 * interface.
 */
public class MyTask implements Runnable {

    public void run() {
        System.out.println("Current system time: " + new Date());
        System.out.println("Another minute ticked away...");
    }
}
