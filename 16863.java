/**
 * Created by IntelliJ IDEA.
 * User: �������������
 * Date: 27.10.2010
 * Time: 14:20:47
 * To change this template use File | Settings | File Templates.
 */
public class EndSum extends Thread {

    private double lower = 0;

    private double upper = 0;

    private double end_sum = 0;

    private Trapez current = null;

    public EndSum(Trapez current, double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
        this.current = current;
        this.setDaemon(true);
        this.setPriority(Thread.NORM_PRIORITY);
        start();
    }

    public void run() {
        myFunction func = new myFunction();
        end_sum = func.getValue(lower) + func.getValue(upper);
    }

    public double getValue() {
        return end_sum;
    }
}
