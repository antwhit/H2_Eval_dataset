import org.ozoneDB.*;

public interface Auto extends OzoneRemote {

    public void print();

    public void setName(String newName);

    public String name();

    public void setAge(Integer newAge) throws Exception;

    public int setAge(int newAge) throws Exception;

    public Integer age() throws Exception;

    public Auto doSomthing(Auto auto) throws Exception;

    public void crash() throws Exception;
}
