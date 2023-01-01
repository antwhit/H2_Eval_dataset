import gui.ErrorDialog;
import gui.GUIMain;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import packets.Capture;
import state.ProgramState;
import storage.DB;

public class JWC3Banlist {

    /**
	 * @param args
	 * @return void
	 */
    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        final ErrorDialog errors = ErrorDialog.instance();
        PrintStream errorStream = new PrintStream(new OutputStream() {

            public void write(byte[] b) {
                String error = new String(b);
                errors.newMessage(error, ErrorDialog.ERROR);
            }

            public void write(int b) {
                byte[] c = { Byte.parseByte(Integer.toString(b)) };
                write(c);
            }
        });
        PrintStream messageStream = new PrintStream(new OutputStream() {

            public void write(byte[] b) {
                String error = new String(b);
                errors.newMessage(error, ErrorDialog.MESSAGE);
            }

            public void write(int b) {
                byte[] c = { Byte.parseByte(Integer.toString(b)) };
                write(c);
            }
        });
        System.setErr(errorStream);
        System.setOut(messageStream);
        DB b = DB.instance();
        ProgramState programState = ProgramState.instance();
        GUIMain g = GUIMain.instance();
        Capture c = new Capture(programState);
        b.setGUI(g);
        g.run();
        c.run();
    }
}
