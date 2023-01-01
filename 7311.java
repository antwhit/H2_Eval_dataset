import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO javadoc
 * @author Nicolas Cabanis
 */
public class ArtistGrabber {

    public static void main(String[] args) throws Exception {
        File dir = new File("F:\\mp3\\music");
        List<String> allSongs = parse(dir);
        List<String> allArtists = parseList(allSongs);
        Class.forName("org.hsqldb.jdbcDriver");
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:runtime/medialibrary/mediadb", "sa", "");
        Statement stmt = conn.createStatement();
        for (String artist : allArtists) {
            artist = artist.replace('\'', '`');
            System.out.println("artist: " + artist);
            stmt.execute("INSERT INTO artist VALUES ('" + artist + "')");
        }
        stmt.execute("SHUTDOWN");
        stmt.close();
        conn.close();
    }

    private static List<String> parse(File file) {
        List<String> result = new ArrayList<String>();
        File[] content = file.listFiles();
        if (content != null) {
            for (int i = 0; i < content.length; i++) {
                if (content[i].isDirectory()) {
                    result.addAll(parse(content[i]));
                } else {
                    result.add(content[i].getName().toLowerCase());
                }
            }
        }
        return result;
    }

    private static List<String> parseList(List<String> allSongs) {
        List<String> result = new ArrayList<String>();
        String artist;
        for (String song : allSongs) {
            if (song.indexOf("-") == -1) {
                System.out.println("WARNING: invalid file name: " + song);
                continue;
            }
            artist = song.substring(0, song.indexOf("-") - 1).trim();
            if (!"".equals(artist) && !result.contains(artist)) {
                result.add(artist);
            }
        }
        return result;
    }
}
