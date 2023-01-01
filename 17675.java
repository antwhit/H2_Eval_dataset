import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.StringTemplate;
import java.net.URL;
import java.util.Properties;
import java.util.Locale;
import java.io.InputStream;
import java.io.IOException;

/** Test internationalization/localization by showing that StringTemplate
 *  easily deals with multiple versions of the same string.  The StringTemplates
 *  and strings properties file are looked up using CLASSPATH.
 */
public class Test {

    static ClassLoader cl = Thread.currentThread().getContextClassLoader();

    public static void main(String[] args) throws IOException {
        String skin = "blue";
        Locale locale = Locale.getDefault();
        String defaultLanguage = locale.getLanguage();
        String language = defaultLanguage;
        if (args.length > 0) {
            language = args[0];
        }
        URL stringsFile = cl.getResource(language + ".strings");
        if (stringsFile == null) {
            System.err.println("can't find strings for language: " + language);
            return;
        }
        Properties strings = new Properties();
        InputStream is = stringsFile.openStream();
        strings.load(is);
        String absoluteSkinRootDirectoryName = cl.getResource(skin).getFile();
        StringTemplateGroup templates = new StringTemplateGroup("test", absoluteSkinRootDirectoryName);
        StringTemplate page1ST = templates.getInstanceOf("page1");
        page1ST.setAttribute("strings", strings);
        StringTemplate page2ST = templates.getInstanceOf("page2");
        page2ST.setAttribute("strings", strings);
        System.out.println(page1ST);
        System.out.println(page2ST);
    }
}
