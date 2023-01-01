import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import fibs.FibsListener;

public class FibsTester implements java.util.Observer {

    public FibsTester(String host, String port, String username, String password) {
        try {
            this.fl = new FibsListener(host, Integer.parseInt(port), username, password, System.out);
        } catch (java.net.UnknownHostException e) {
            e.printStackTrace();
        }
        heartBeat = new HeartBeat(fl, System.out);
        System.out.println("heartbeat=" + heartBeat);
        fl.addObserver(this);
        (new Thread(fl)).start();
        fl.register(new TestHeartBeatAction());
    }

    public void update(Observable o, Object arg) {
        System.out.println("TESTER: update, arg=" + arg);
        boolean connected = ((Boolean) arg).booleanValue();
        if (connected) {
            (new Thread(heartBeat)).start();
            System.out.println("TESTER: fl is connected, start HeartBeat");
        } else {
            System.out.println("TESTER: fl is DISconnected");
            this.heartBeat.stop();
        }
    }

    public static void main(String args[]) {
        new FibsTester("fibs.com", "4321", "RepBot", "wrong-");
    }

    private FibsListener fl;

    private HeartBeat heartBeat;
}
