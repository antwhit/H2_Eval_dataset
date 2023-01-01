import java.util.*;
import java.util.prefs.Preferences;
import java.net.*;
import java.io.*;

public class Context {

    protected static Context instance;

    protected File subjectCache = new File("./subject_cache");

    protected String subjectSource = "http://localhost/catlist";

    protected String defaultCreator = "No Name";

    protected String defaultProvider = "unknown";

    protected Preferences user;

    public static Context get() {
        if (instance == null) instance = new Context();
        return instance;
    }

    protected HashMap subjects = new HashMap();

    public Context() {
        user = Preferences.userRoot();
        subjectSource = user.get("subjectSource", subjectSource);
        defaultCreator = user.get("defaultCreator", defaultCreator);
        defaultProvider = user.get("defaultProvider", defaultProvider);
        downloadSubjects();
        loadSubjectsFromCache();
    }

    public String getPrefSubjectSource() {
        return subjectSource;
    }

    public String getPrefDefaultCreator() {
        return defaultCreator;
    }

    public String getPrefDefaultProvider() {
        return defaultProvider;
    }

    public void setPrefSubjectSource(String val) {
        if (!subjectSource.equals(val)) {
            subjectSource = val;
            user.put("subjectSource", val);
            subjects.clear();
            downloadSubjects();
            loadSubjectsFromCache();
        }
    }

    public void setPrefDefaultCreator(String val) {
        defaultCreator = val;
        user.put("defaultCreator", val);
    }

    public void setPrefDefaultProvider(String val) {
        defaultProvider = val;
        user.put("defaultProvider", val);
    }

    protected void downloadSubjects() {
        File scBackup = new File(subjectCache.getAbsolutePath() + ".bak");
        boolean backedUp = subjectCache.renameTo(scBackup);
        try {
            System.out.println("Reading category list from " + subjectSource);
            URL catlistsrc = new URL(subjectSource);
            InputStream clis = catlistsrc.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clis));
            FileWriter writer = new FileWriter(subjectCache);
            int count = 0;
            for (String line = br.readLine(); line != null; line = br.readLine(), count++) {
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
            System.out.println("" + count + " categories downloaded successfully");
        } catch (Exception e) {
            System.err.println("Cannot read cathegory list from server: " + e);
            if (backedUp) scBackup.renameTo(subjectCache);
        }
    }

    protected void loadSubjectsFromCache() {
        try {
            System.out.println("Reading category list from cache: " + subjectCache);
            FileReader input = new FileReader(subjectCache);
            BufferedReader br = new BufferedReader(input);
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                int sep = line.indexOf(' ');
                if (sep != -1) {
                    subjects.put(line.substring(0, sep), line.substring(sep + 1));
                } else {
                    System.err.println("Unable to parse line: " + line);
                }
            }
            System.out.println("" + subjects.size() + " categories read successfully");
        } catch (Exception e) {
            System.err.println("Cannot read cathegory list from cache:");
            e.printStackTrace();
        }
    }

    public HashMap getAllSubjects() {
        return subjects;
    }

    public String getDefaultCreator() {
        return defaultCreator;
    }

    public String getDefaultProvider() {
        return defaultProvider;
    }
}
