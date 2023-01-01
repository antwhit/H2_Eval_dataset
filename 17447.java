import java.io.*;
import java.util.*;

public class BuildMessage {

    private String absoluteFilename = null;

    private String filename = null;

    private int line = -1;

    private int column = -1;

    private String message = null;

    private boolean isWarning = false;

    private boolean isError = false;

    public BuildMessage() {
    }

    public BuildMessage(String message) {
        this(" ", -1, -1, message);
    }

    public BuildMessage(String filename, int line, String message) {
        this(filename, line, -1, message);
    }

    public BuildMessage(String filename, int line, int column, String message) {
        this.absoluteFilename = filename;
        this.filename = filename.substring(filename.lastIndexOf(File.separatorChar) + 1);
        this.line = line;
        this.column = column;
        if (message != null) this.message = message.trim();
        if (message != null) this.isWarning = message.toLowerCase().startsWith("warning:");
        if (message != null) this.isError = message.toLowerCase().startsWith("error:");
    }

    public boolean isComplete() {
        boolean Result = true;
        if (filename == null) Result = false; else if (line == -1) Result = false; else if (column == -1) Result = false; else if (message == null) Result = false;
        return Result;
    }

    public String getAbsoluteFilename() {
        return absoluteFilename;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getMessage() {
        return message;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(boolean val) {
        this.isError = val;
    }

    public void setWarning(boolean val) {
        this.isWarning = val;
    }

    public void setFileName(String name) {
        this.absoluteFilename = name;
        this.filename = name.substring(name.lastIndexOf(File.separatorChar) + 1);
    }

    public boolean isWarning() {
        return isWarning;
    }

    public boolean isError() {
        return isError;
    }

    public String toString() {
        if (isError || isWarning) return message + " at line " + line + ", column " + column; else return message;
    }
}
