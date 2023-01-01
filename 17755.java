import javax.persistence.*;

@Entity
public class I3 {

    @Id
    private java.lang.String name;

    public I3() {
    }

    public I3(java.lang.String name) {
        this();
        this.name = name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getName() {
        return this.name;
    }
}
