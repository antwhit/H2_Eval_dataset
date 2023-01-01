/**
 * Class used to show animation during SQL query execution
 * @author emiliano.marin
 * @see Runnable
 */
public class LifeShower implements Runnable {

    private boolean stop_condition = false;

    public void stop() {
        stop_condition = true;
    }

    public void run() {
        System.out.println();
        while (!stop_condition) {
            for (int i = 0; i < 33; i++) {
                System.out.print(">");
                System.out.flush();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 33; i++) {
                System.out.print("\b");
                System.out.flush();
            }
            for (int i = 0; i < 33; i++) {
                System.out.print(" ");
                System.out.flush();
            }
            for (int i = 0; i < 33; i++) {
                System.out.print("\b");
                System.out.flush();
            }
        }
        System.out.println();
        System.out.flush();
    }
}
