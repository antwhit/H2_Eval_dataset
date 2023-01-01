import java.awt.event.*;
import java.io.*;
import javax.swing.*;

class openDirAction extends AbstractAction {

    SimpleFrame frame;

    JFileChooser chooser;

    File file;

    ReadFileAction readaFile;

    openDirAction(SimpleFrame dataframe, JFileChooser chooser, Action readFile) {
        this.chooser = chooser;
        this.frame = dataframe;
        this.readaFile = (ReadFileAction) readFile;
    }

    public void actionPerformed(ActionEvent evt) {
        int returnValue = chooser.showOpenDialog(frame.getViewFrame());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String strDir = chooser.getCurrentDirectory().getPath();
            System.out.println("OpenDirectoryAction Directory: " + strDir);
            this.frame.ReadAllFiles(strDir);
        }
    }
}
