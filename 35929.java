import aggregate.*;

public class aggregate_runme {

    static {
        try {
            System.loadLibrary("aggregate");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        int result = aggregate.move(aggregate.UP);
        if (result != aggregate.UP) throw new RuntimeException("UP failed");
        result = aggregate.move(aggregate.DOWN);
        if (result != aggregate.DOWN) throw new RuntimeException("DOWN failed");
        result = aggregate.move(aggregate.LEFT);
        if (result != aggregate.LEFT) throw new RuntimeException("LEFT failed");
        result = aggregate.move(aggregate.RIGHT);
        if (result != aggregate.RIGHT) throw new RuntimeException("RIGHT failed");
        try {
            aggregate.move(0);
            throw new RuntimeException("0 test failed");
        } catch (IllegalArgumentException e) {
        }
    }
}
