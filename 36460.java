import javax.persistence.*;

@Entity
public class RU2_2 {

    @Id
    @GeneratedValue
    private java.lang.Long id;

    public RU2_2() {
    }

    public java.lang.Long getId() {
        return this.id;
    }
}
