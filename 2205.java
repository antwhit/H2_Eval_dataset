/**
 * Test Timed Wait.
 *
 *  monitorenter
 *  monitorexit
 *
 *  wait
 *  wait(millis)
 *  notify
 *  notifyAll
 *
 * @author unascribed
 */
class Task extends Thread {

    Mailbox mailbox;

    Flag flag;

    Task(Mailbox mailbox, Flag flag) {
        this.mailbox = mailbox;
        this.flag = flag;
    }

    public void run() {
        mailbox.send(getName());
        try {
            sleep(200);
        } catch (InterruptedException e) {
        }
        System.out.println(getName() + " waiting");
        flag.await();
        System.out.println(getName() + " ending");
    }
}

class Mailbox {

    String messages[];

    int received;

    Mailbox(int max) {
        messages = new String[max];
    }

    synchronized void send(String message) {
        messages[received++] = message;
        if (received == messages.length) {
            System.out.println("mailbox: notification sent to tell main that mailbox is full");
            notify();
        }
    }

    synchronized void await() {
        if (received != messages.length) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ": mailbox: notification received");
    }
}

class Flag {

    boolean flag = false;

    synchronized void set() {
        System.out.println(Thread.currentThread().getName() + ": flag: notification sent");
        flag = true;
        notifyAll();
    }

    synchronized void await() {
        if (flag == true) System.out.println(Thread.currentThread().getName() + ": flag: already set"); else {
            while (flag == false) {
                try {
                    wait(1000000);
                } catch (InterruptedException e) {
                }
                if (flag == false) System.out.println(Thread.currentThread().getName() + ": flag: timed out");
            }
            System.out.println(Thread.currentThread().getName() + ": flag: notification received");
        }
    }
}

class TestTimedWait {

    public static void main(String args[]) {
        System.out.println("TestNotification");
        int cnt = 20;
        Mailbox mailbox = new Mailbox(cnt);
        Flag flag = new Flag();
        Task tasks[] = new Task[cnt];
        for (int i = 0; i < cnt; ++i) tasks[i] = new Task(mailbox, flag);
        TestTimedWait test = new TestTimedWait(tasks);
        test.run();
        mailbox.await();
        for (int i = 0; i < cnt; ++i) System.out.println("main: " + mailbox.messages[i] + " replied");
        System.out.println("main: sleeping");
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("main: running");
        flag.set();
        for (int i = 0; i < cnt; ++i) {
            System.out.println("main: joining " + tasks[i].getName());
            try {
                tasks[i].join();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("main: bye");
    }

    Task tasks[];

    TestTimedWait(Task tasks[]) {
        this.tasks = tasks;
    }

    public void run() {
        for (int i = 0; i < tasks.length; ++i) tasks[i].start();
    }
}
