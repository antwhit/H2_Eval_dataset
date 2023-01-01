import java.io.File;
import java.io.IOException;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject.Kind;

public class CustomJavaCompiler {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid arg length " + args.length);
            System.exit(-1);
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdFileManager = compiler.getStandardFileManager(null, null, null);
        JavaFileManager fileManager = new CustomJavaFileManager(stdFileManager);
        System.out.println(new File(args[0]).getAbsolutePath());
        System.out.println(compiler.getTask(null, fileManager, null, null, null, stdFileManager.getJavaFileObjects(args[0])).call());
    }

    private static class CustomJavaFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {

        private int outputFileCount = 0;

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling) throws IOException {
            if (++outputFileCount > 32) {
                throw new IOException("Too many output files");
            }
            return super.getJavaFileForOutput(location, className, kind, sibling);
        }

        public CustomJavaFileManager(StandardJavaFileManager fileManager) {
            super(fileManager);
        }
    }
}

;
