import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class MyURL extends URLClassLoader {

    String dirCon = null;

    String name;

    public MyURL(URL[] url) {
        super(url);
    }

    public void loadClass1(String name) {
        try {
            Class<?> cls = findClass(name);
            System.out.println(cls.getCanonicalName());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found:" + name);
        }
    }

    public void walk(File dir) {
        String pattern = ".class";
        File listFile[] = dir.listFiles();
        String newStr = dir.getPath().toString();
        int last = newStr.length();
        newStr = newStr.substring(newStr.indexOf("bin"), last);
        newStr = newStr.replaceAll("\\\\", ".");
        newStr = newStr.replaceAll("bin.", "");
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    walk(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(pattern)) {
                        name = listFile[i].getName();
                        if (newStr.equals("bin")) name = name.substring(0, name.lastIndexOf(".")); else {
                            name = name.substring(0, name.lastIndexOf("."));
                            name = newStr + "." + name;
                        }
                        loadClass1(name);
                    }
                }
            }
        }
    }
}
