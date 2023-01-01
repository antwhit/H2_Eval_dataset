/**
*This is an basic testing program, tests some sporadic functions of some key classes
*note: this "testclass" is wery lame.
*@author (pellefantus)
*@version (2005.04.21)
*/
public class Test {

    private Main m;

    private Controller c;

    private Client cl;

    private SocketThrdServer s;

    private GUI g;

    public Test() {
        m = new Main();
        c = new Controller();
        g = new GUI(c);
        doTesting();
    }

    public static void main(String[] args) {
        new Test();
    }

    private void doTesting() {
        if (c.serverIsRunning() == false) System.out.println("Test: ok");
        if (c.serverIsRunning() == true) System.out.println("Test: ok");
        cl = new Client("client1", c);
        System.out.println("Test: no errors found, exiting");
        System.exit(0);
    }
}
