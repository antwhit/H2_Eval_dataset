public class Block {

    static int top = 3;

    static char stack[] = new char[] { 'a', 'b', 'c', 'd', '$', '$' };

    public static void main(String[] a) {
        System.out.println("Main thread starts executing.");
        System.out.println("Initial value of top = " + top + ".");
        System.out.println("Initial value of stack top = " + stack[top] + ".");
        System.out.println("Main thread will now fork two threads.");
        AcquireBlock t1 = new AcquireBlock();
        ReleaseBlock t2 = new ReleaseBlock();
        t2.start();
        t1.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        ;
        System.out.println("System terminates normally.");
        System.out.println("Final value of top = " + top + ".");
        System.out.println("Final value of stack top = " + stack[top] + ".");
        System.out.println("Final value of stack top-1 = " + stack[top - 1] + ".");
    }
}

class AcquireBlock extends Thread {

    private char block;

    private char copy;

    private int oldtop;

    private int newtop;

    public void run() {
        System.out.println("AcquireBlock thread starts executing.");
        yield();
        System.out.println("AcquireBlock thread requests Ms block.");
        oldtop = Block.top;
        copy = Block.stack[oldtop];
        System.out.println("AcquireBlock thread obtains  Ms block " + copy + " from position " + oldtop + ".");
        block = Block.stack[oldtop];
        newtop = Block.top - 1;
        System.out.println("AcquireBlock decrements pointer to " + newtop + ".");
        --Block.top;
        System.out.println("Acq: Current value of top = " + Block.top + ".");
        System.out.println("Acq: Current value of stack top = " + Block.stack[Block.top] + ".");
        System.out.println("AcquireBlock thread terminates.");
    }
}

class ReleaseBlock extends Thread {

    private char block = 'e';

    private int oldtop;

    private int newtop;

    public void run() {
        System.out.println("ReleaseBlock thread starts executing.");
        yield();
        newtop = Block.top + 1;
        System.out.println("ReleaseBlock increments pointer to " + newtop + ".");
        Block.top++;
        yield();
        System.out.println("ReleaseBlock thread returns  Ms block " + block + " to position " + Block.top + ".");
        Block.stack[Block.top] = block;
        System.out.println("Rel: Current value of top = " + Block.top + ".");
        System.out.println("Rel: Current value of stack top = " + Block.stack[Block.top] + ".");
        System.out.println("ReleaseBlock thread terminates.");
    }
}
