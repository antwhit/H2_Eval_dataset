import java.util.*;
import java.io.*;
import java.util.zip.*;

public class SourceSnapshot {

    static final char SOURCE_PATH_SEPARATOR = System.getProperty("path.separator").charAt(0);

    private SourceFinder sourceFinder;

    private Vector sourceRepositories;

    private String currSourceName;

    private byte data[];

    private int line_count;

    public SourceSnapshot() {
        String sourcePath = System.getProperty("java.class.path");
        sourceRepositories = parseAndValidatePathSpecification("sourcepath", sourcePath, SOURCE_PATH_SEPARATOR);
        sourceFinder = new SourceFinder(sourceRepositories);
        currSourceName = "";
    }

    public String getSourceLine(String source, int linenum) {
        int start, end, j, currline;
        if (!currSourceName.equals(source)) {
            currSourceName = source;
            data = sourceFinder.getFileContents("", source);
            line_count = countLine();
        }
        if (data == null) {
            return ("  (Source file not available)");
        }
        currline = 1;
        j = 1;
        start = 0;
        end = 0;
        for (int i = start; i < data.length; i++) {
            if (data[i] == '\n') {
                end = i;
                break;
            }
        }
        while (end < data.length) {
            if (currline == linenum) {
                return new String(data, start, (end - start));
            } else {
                start = end + 1;
                for (int i = start; i < data.length; i++) {
                    if (data[i] == '\n') {
                        end = i;
                        currline++;
                        break;
                    }
                }
            }
        }
        return ("Line " + linenum + " does not exist");
    }

    private int countLine() {
        int count = 0;
        if (data == null) return 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '\n') {
                count++;
            }
        }
        if (data[data.length - 1] != '\n') count++;
        return count;
    }

    public int getLineCount() {
        return line_count;
    }

    static Vector parseAndValidatePathSpecification(String pathKind, String pathSpecification, char pathSeparator) {
        Vector components = new Vector();
        for (StringTokenizer st = new StringTokenizer(pathSpecification, String.valueOf(pathSeparator), false); st.hasMoreTokens(); ) {
            String componentName = st.nextToken();
            boolean isDuplicate = false;
            for (Enumeration c = components.elements(); c.hasMoreElements(); ) if (componentName.equals((String) c.nextElement())) {
                isDuplicate = true;
                break;
            }
            if (isDuplicate) {
                System.out.println(pathKind + " component \"" + componentName + "\" multiply defined (duplicate ignored)");
                continue;
            }
            if (componentName.endsWith(".zip") || componentName.endsWith(".jar")) {
                try {
                    ZipFile archive = new ZipFile(componentName);
                    archive.close();
                } catch (IOException x) {
                    System.out.println(pathKind + " component \"" + componentName + "\" doesn't exist (ignoring it)");
                    continue;
                }
            } else {
                File dir = new File(componentName);
                if (dir.exists() == false) {
                    System.out.println(pathKind + " component \"" + componentName + "\" doesn't exist (ignoring it)");
                    continue;
                }
                if (dir.isDirectory() == false) {
                    System.out.println(pathKind + " component \"" + componentName + "\" is not a directory (ignoring it)");
                    continue;
                }
            }
            components.addElement(componentName);
        }
        return components;
    }

    public boolean sameSourceFile(String newSourceName) {
        return (currSourceName.equals(newSourceName));
    }

    public boolean exists(String name) {
        String resolved_name = sourceFinder.resolveSourceFileName("", name);
        if (resolved_name == null) return false; else return true;
    }

    public void resetBuffer() {
        currSourceName = "";
    }
}
