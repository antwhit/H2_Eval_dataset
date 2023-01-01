import javax.swing.JComboBox;
import java.util.ArrayList;

public class ComboBoxLigation extends JComboBox {

    static final long serialVersionUID = 1L;

    ArrayList<ArrayList<Integer>> schnittstellenListe;

    ComboBoxLigation() {
        this.addItem("              ");
    }

    public void setSchnittstellenListe(ArrayList<ArrayList<Integer>> schnittstellenListe) {
        this.schnittstellenListe = schnittstellenListe;
        this.removeAllItems();
        for (int i = 1; i <= schnittstellenListe.size(); i++) this.addItem("Loesung " + i);
    }

    public ArrayList<Integer> getSelectedSchnittstelle() {
        return this.schnittstellenListe.get(this.getSelectedIndex());
    }
}
