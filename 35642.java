import java.util.Collection;
import java.util.List;

/**
 * Factory for filesystem commands.
 */
class Filesystem {

    public void move(String source, String target) {
        new Command("mv", source, target).execute();
    }

    /**
     * Moves all of the files in {@code source} to {@code target}, one at a
     * time. Unlike {@code move}, this approach works even if the target
     * directory is nonempty.
     */
    public int moveContents(String source, String target) {
        return copyContents(true, source, target);
    }

    /**
     * Copies all of the files in {@code source} to {@code target}, one at a
     * time. Unlike {@code move}, this approach works even if the target
     * directory is nonempty.
     */
    public int copyContents(String source, String target) {
        return copyContents(false, source, target);
    }

    private int copyContents(boolean move, String source, String target) {
        List<String> files = new Command("find", source, "-type", "f").execute();
        for (String file : files) {
            String targetFile = target + "/" + file.substring(source.length());
            mkdir(parent(targetFile));
            if (move) {
                new Command("mv", "-i", file, targetFile).execute();
            } else {
                new Command("cp", file, targetFile).execute();
            }
        }
        return files.size();
    }

    private String parent(String file) {
        return file.substring(0, file.lastIndexOf('/'));
    }

    public void mkdir(String dir) {
        new Command("mkdir", "-p", dir).execute();
    }

    public List<String> find(String where, String name) {
        return new Command("find", where, "-name", name).execute();
    }

    public void rm(Collection<String> files) {
        new Command.Builder().args("rm", "-r").args(files).execute();
    }

    public void rm(String file) {
        new Command("rm", "-r", file).execute();
    }
}
