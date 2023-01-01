import waba.ui.*;
import waba.io.*;

public class LoadMmFile extends Container {

    private FreeMindPDA main;

    private Button btnQuit;

    private Button btnOk;

    private ListBox listFiles;

    private ComboBox cbxFiles;

    private ListBox listDirs;

    private ComboBox cbxDirs;

    private String storeDir;

    protected String selectedFileName = null;

    public LoadMmFile(FreeMindPDA main) {
        super();
        this.main = main;
    }

    public void onStart() {
        btnQuit = new Button("Quit");
        add(btnQuit, LEFT, BOTTOM);
        File file = main.getStorageDir();
        if ((file == null) || (!file.exists())) {
            add(new Label("No file system found."), CENTER, CENTER);
            return;
        }
        storeDir = file.getPath();
        btnOk = new Button("  OK  ");
        add(btnOk, RIGHT, BOTTOM);
        String[] list = file.listFiles();
        listFiles = new ListBox();
        listDirs = new ListBox();
        listDirs.add("Not implemented yet.");
        if (list != null) {
            for (int i = 0; i < list.length; i++) if (list[i] != null) {
                if (isDir(list[i])) {
                    listDirs.add(list[i]);
                } else {
                    if (list[i].toLowerCase().endsWith(".mm")) {
                        listFiles.add(list[i]);
                    }
                }
            }
            add(new Label("Select file to load:"), LEFT, TOP + 1);
            cbxFiles = new ComboBox(listFiles);
            add(cbxFiles);
            cbxFiles.setRect(LEFT, AFTER + 2, FILL, PREFERRED);
            cbxFiles.select(listFiles.getItemAt(0));
            add(new Label("Change directory:"), LEFT, AFTER + 10);
            cbxDirs = new ComboBox(listDirs);
            add(cbxDirs);
            cbxDirs.setRect(LEFT, AFTER, FILL, PREFERRED);
            cbxDirs.select(listDirs.getItemAt(0));
        }
    }

    /**
	 * Standard event handler.
	 */
    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnQuit) {
                getParentWindow().swap(null);
            } else if (event.target == btnOk) {
                if (cbxFiles.getSelectedIndex() >= 0) selectedFileName = (String) cbxFiles.getSelectedItem();
                main.loadFile(storeDir + selectedFileName);
                getParentWindow().swap(null);
            }
        }
    }

    private boolean isDir(String string) {
        if (string.endsWith("/")) return true;
        return false;
    }
}
