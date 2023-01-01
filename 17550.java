import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class TPOpenGL {

    public static boolean transparency = false;

    public static double[][][] scene() {
        double[][][] result = new double[20][3][3];
        for (int i = 0; i < 20; ++i) {
            double t = i * Math.PI * 2.0 / 20;
            double x = 3 * Math.cos(t);
            double y = 3 * Math.sin(t);
            result[i][0][0] = x;
            result[i][0][1] = y;
            result[i][0][2] = 0;
            result[i][1][0] = x;
            result[i][1][1] = y;
            result[i][1][2] = 2.0;
            result[(i + 1) % 20][2][0] = x;
            result[(i + 1) % 20][2][1] = y;
            result[(i + 1) % 20][2][2] = 1.0;
        }
        return result;
    }

    public static double[][][] sort(double[][][] scene) {
        return scene;
    }

    public static void main(String[] args) {
        transparency = true;
        final Renderer renderer = new Renderer();
        JFrame frame = new JFrame("TP OpenGL");
        frame.setSize(1024, 768);
        renderer.common(frame);
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
