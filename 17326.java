import java.io.File;

public class AdvancedJreHomeProperty {

    public static void main(String[] args) {
        String defaultJavaHome = System.getProperty("java.home");
        if (!isJDK(defaultJavaHome)) {
            try {
                String jdkPath = lookForJDKPath(defaultJavaHome);
                if (jdkPath != null) {
                    defaultJavaHome = jdkPath;
                }
            } catch (Exception e) {
            }
        }
        System.out.println(defaultJavaHome);
    }

    private static boolean isJDK(String path) {
        return path.toLowerCase().contains("jdk");
    }

    private static String lookForJDKPath(String jrePath) {
        File jreDir = new File(jrePath);
        File parentDir = jreDir.getParentFile();
        String jdkDir = scanForJDK(parentDir);
        if (jdkDir == null) {
            File parentOfParentDir = parentDir.getParentFile();
            return scanForJDK(parentOfParentDir);
        } else {
            return jdkDir;
        }
    }

    private static String scanForJDK(File javaDir) {
        File[] children = javaDir.listFiles();
        for (File child : children) {
            String childPath = child.getAbsolutePath();
            if (isJDK(childPath)) {
                return childPath;
            }
        }
        return null;
    }
}
