/**
 * �������������� �����.
 * @author ������ ������� ����������
 */
public class TComputThread extends Thread {

    public double fResult;

    private double fa;

    private double fb;

    private int fn;

    public TComputThread(double a, double b, int n) {
        super();
        fa = a;
        fb = b;
        fn = n;
    }

    @Override
    public void run() {
        synchronized (this) {
            fResult = CComputingIntegral.integral(fa, fb, fn);
            notify();
        }
    }
}
