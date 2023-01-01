import java.io.*;

public class MapSide implements Serializable {

    private String _name = "";

    public MapSide() {
        _name = "";
    }

    public MapSide(String name) {
        _name = name;
    }

    public String toString() {
        return _name;
    }

    public boolean equals(Object obj) {
        if ((obj != null) && (obj.getClass().equals(this.getClass()))) {
            MapSide mapSide = (MapSide) obj;
            return (_name.equals(mapSide.getName()));
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
