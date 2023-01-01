import org.ozoneDB.OzoneRemote;

public interface Car extends OzoneRemote {

    public void setName(String name);

    public String name();

    public void setYearOfConst(int year);

    public int age();
}
