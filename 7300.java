import java.io.*;

class FileTypeFilter implements FileFilter {

    private String fileMasks = ".log,.mpg,.mpeg,.bin,.mpv,.mpa,.ts,.tp,.dvr-ms,.rec,.pva,.avi";

    public FileTypeFilter(String filter) {
        fileMasks = filter;
    }

    public boolean accept(File pathname) {
        if (pathname == null) return false;
        if (pathname.isDirectory()) return true;
        String[] acceptable = fileMasks.split(",");
        for (int x = 0; x < acceptable.length; x++) {
            if (pathname.getName().toLowerCase().indexOf(acceptable[x].toLowerCase()) > -1) {
                return true;
            }
        }
        return false;
    }
}
