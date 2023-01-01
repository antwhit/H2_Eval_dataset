import org.ozoneDB.*;

public interface Part extends OzoneRemote {

    public void connect(int index, Part part) throws Exception;

    public void backConnect(Part part);

    public void deconnect() throws Exception;

    public void deBackConnect(Part part) throws Exception;

    public void set(String _type, int _x, int _y);

    public void get();

    public void traversal(int _x, int _y, int depth) throws Exception;
}
