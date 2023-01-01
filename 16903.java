import gui.MainWindow;
import com.trolltech.qt.gui.*;

public class Pencil extends QWidget {

    public static void main(String[] args) {
        QApplication.initialize(args);
        QApplication.setWindowIcon(new QIcon("classpath:icons/icon.png"));
        MainWindow testPencil = new MainWindow(null);
        testPencil.show();
        QApplication.exec();
    }

    public Pencil(QWidget parent) {
        super(parent);
    }
}
