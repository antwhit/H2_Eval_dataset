import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {

    private Display display;

    private Shell shell;

    public Main() {
        display = new Display();
        shell = new Shell(display);
        shell.setLayout(new FillLayout());
        shell.setSize(700, 500);
        shell.setText("ForEunmi (�׶��� ���� �Ϻκ� ����)");
    }

    public void run() {
        new UIMain(shell, SWT.None);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        Main instance = new Main();
        instance.run();
    }
}
