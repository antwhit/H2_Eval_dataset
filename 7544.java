import javax.persistence.*;

@Entity
public class E1 {

    private java.lang.String name;

    @Id
    @GeneratedValue
    private java.lang.Long id;

    public E1() {
    }

    public E1(java.lang.String name) {
        this();
        this.name = name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getName() {
        return this.name;
    }

    public java.lang.Long getId() {
        return this.id;
    }
}
