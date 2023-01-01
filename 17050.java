import javax.persistence.*;

@Entity
public class RU2_1 {

    @Id
    @GeneratedValue
    private java.lang.Long id;

    @OneToMany
    private java.util.Set<RU2_2> rU2_2s;

    public RU2_1() {
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setRU2_2s(java.util.Set<RU2_2> rU2_2s) {
        this.rU2_2s = rU2_2s;
    }

    public java.util.Set<RU2_2> getRU2_2s() {
        return this.rU2_2s;
    }
}
