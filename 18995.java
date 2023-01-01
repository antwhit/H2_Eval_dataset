import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.beans.*;
import java.lang.*;

class ProjectFileChooser extends JFileChooser {

    public ProjectFileChooser(String startDirectory) {
        setFileView(new ProjectFileView());
        setFileSelectionMode(DIRECTORIES_ONLY);
        setMultiSelectionEnabled(false);
        setFileHidingEnabled(true);
        if ((startDirectory != null) && (startDirectory.equals("") == false)) {
            File dir = new File(startDirectory);
            if (dir.exists() && dir.isDirectory()) setCurrentDirectory(dir);
        }
    }

    protected void acceptedProjectDir() {
        approveSelection();
    }

    public void approveSelection() {
        if (isProjectDir(getSelectedFile())) {
            File d = getSelectedFile();
            if (d.isDirectory()) setCurrentDirectory(d);
            super.approveSelection();
        } else {
            setCurrentDirectory(getSelectedFile());
        }
    }

    public static boolean isProjectDir(File f) {
        if (f == null) return false;
        File projFile = new File(f.getAbsolutePath() + File.separator + OConsts.PROJ_FILENAME);
        File internal = new File(f.getAbsolutePath() + File.separator + OConsts.DEFAULT_INTERNAL);
        if (projFile.exists() && internal.exists() && internal.isDirectory()) {
            return true;
        } else return false;
    }

    public static void main(String[] args) {
        ProjectFileChooser pfc = new ProjectFileChooser("/home/keith/tmp");
        int retVal = pfc.showDialog(null, "select");
        if (retVal == JFileChooser.APPROVE_OPTION) System.out.println("accepted project directory '" + pfc.getCurrentDirectory() + "'"); else System.out.println("user cancelled");
    }
}
