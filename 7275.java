import br.com.fatecpg.zanotti.acessobanco.conComboLoja;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel lblLoja = new JLabel("Selecione a Loja");
        frame.setLayout(new GridLayout(0, 1));
        conComboLoja con = new conComboLoja();
        frame.add(lblLoja);
        frame.setSize(250, 80);
        frame.setVisible(true);
    }
}
