public class PidRef implements ToXML {

    public PidRef(String someName, String n, String f, int r, int c) {
        name = someName;
        note = n;
        filename = f;
        line = r;
        column = c;
    }

    public boolean equals(PidRef a) {
        return name.equals(a.name) && note.equals(a.note) && line == a.line;
    }

    public String toXML() {
        return XMLIndent.getSpace() + "<PidRef name=" + Pid.qt(name) + " note=" + Pid.qt(note) + " line=" + Pid.qt(line + "") + " col=" + Pid.qt(column + "") + " file=" + Pid.qt(filename) + "/>" + Pid.Eol;
    }

    public String toAbbvXML() {
        String a;
        try {
            a = filename.substring(filename.lastIndexOf("/") + 1);
            try {
                a = a.substring(0, a.lastIndexOf("."));
            } catch (Exception e) {
            }
        } catch (Exception e) {
            a = filename;
        }
        return XMLIndent.getSpace() + "<PidRef name=" + Pid.qt(name) + " note=" + Pid.qt(note) + " line=" + Pid.qt(line + "") + " col=" + Pid.qt(column + "") + " file=" + Pid.qt(a) + "/>" + Pid.Eol;
    }

    public String toString() {
        String ps = System.getProperty("file.separator");
        int l = filename.lastIndexOf(ps);
        String filestem;
        for (filestem = filename; (l = filestem.indexOf(ps)) != -1; filestem = filestem.substring(l + 1)) ;
        l = filestem.lastIndexOf(".");
        if (l != -1) filestem = filestem.substring(0, l);
        return name + " " + filestem + " Note: " + note;
    }

    String name;

    int column;

    int line;

    String filename;

    String note;
}
