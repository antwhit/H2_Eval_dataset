public class SyncA extends A {

    public synchronized int read() {
        return super.read();
    }

    public synchronized void write(int c) {
        super.write(c);
    }
}
