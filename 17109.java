import java_enums.*;

public class java_enums_runme implements stuff {

    static {
        try {
            System.loadLibrary("java_enums");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        int number = 200;
        switch(number) {
            case stuff.FIDDLE:
                break;
            case stuff.STICKS:
                break;
            case stuff.BONGO:
                break;
            case stuff.DRUMS:
                break;
            default:
                break;
        }
        if (stuff.DRUMS != 15) throw new RuntimeException("Incorrect value for DRUMS");
        short poppycock = nonsense.POPPYCOCK;
        short tst1 = java_enums.test1(poppycock);
        short tst2 = java_enums.test2(poppycock);
        switch(number) {
            case FIDDLE:
                break;
            case STICKS:
                break;
            case BONGO:
                break;
            case DRUMS:
                break;
            default:
                break;
        }
    }
}
