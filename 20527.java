import java.lang.*;
import java.util.*;
import magenta.*;

/**
 * class Mp3
 */
public class Mp3 extends GdmoObject {

    private String artist;

    private String title;

    private String location;

    public Mp3(String path, String name) {
        super(path, name);
    }

    public String toString() {
        return new String(super.toString() + " artist: " + artist + " title: " + title + " location: " + location);
    }

    public String setArtist(String newArtist) {
        artist = newArtist;
        return new String("success");
    }

    public String getArtist() {
        return artist;
    }

    public String setTitle(String newTitle) {
        title = newTitle;
        return new String("success");
    }

    public String getTitle() {
        return title;
    }

    public String setLocation(String newLocation) {
        location = newLocation;
        return new String("success");
    }

    public String getLocation() {
        return location;
    }
}
