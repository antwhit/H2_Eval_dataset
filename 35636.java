import org.ozoneDB.OzoneRemote;

public interface OO7_Connection extends OzoneRemote {

    public void setType(String x);

    public String type();

    public void setLength(long x);

    public long length();

    public void setFrom(OO7_AtomicPart x);

    public OO7_AtomicPart from();

    public void setTo(OO7_AtomicPart x);

    public OO7_AtomicPart to();
}
