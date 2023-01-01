import soundmachine.audio.PreListen;

public class PreListenTest {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Testing with (String) null argument");
            PreListen.prelisten((String) null);
            System.out.println("Testing with (String[]) null argument");
            PreListen.prelisten((String[]) null);
            System.out.println("Testing with String[0] argument");
            String[] asTest0 = new String[0];
            PreListen.prelisten(asTest0);
            System.out.println("Testing with String[] = {null, null} argument");
            String[] asTest1 = { null, null };
            PreListen.prelisten(asTest1);
        } else if (args.length == 1) {
            PreListen.prelisten(args[0]);
        } else {
            int nStart;
            if (args[0].equals("-k")) {
                nStart = 1;
            } else {
                nStart = 0;
            }
            String[] asFilenames = new String[args.length - nStart];
            for (int i = 0; i < args.length - nStart; i++) {
                asFilenames[i] = args[i + nStart];
            }
            PreListen.prelisten(asFilenames);
        }
    }
}
