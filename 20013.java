import it.sauronsoftware.cron4j.Scheduler;

public class Main {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Scheduler scheduler = new Scheduler();
        scheduler.schedule("* * * * *", task);
        scheduler.start();
        try {
            Thread.sleep(5L * 60L * 1000L);
        } catch (InterruptedException e) {
            ;
        }
        scheduler.stop();
    }
}
