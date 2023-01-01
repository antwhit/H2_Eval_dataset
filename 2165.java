import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestLoad {

    public static void main(String[] args) throws Exception {
        File f = new File("Fail.class");
        if (!f.exists()) {
            System.out.println(f.getAbsolutePath() + " does not exists");
        }
        String className = "Fail";
        URL[] fileURL = { f.toURI().toURL() };
        URLClassLoader classLoader = new URLClassLoader(fileURL);
        Class c = classLoader.loadClass(className);
        Object o = c.newInstance();
    }
}
