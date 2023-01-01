import java.awt.*;
import java.awt.event.*;

class GUI extends ExtendedFrame implements ActionListener {

    Button ok = new Button("Ok"), avsluta = new Button("Avsluta");

    TextField iPartier, iMandat, iSystem;

    Application a;

    public GUI(Application app) {
        super("Valsimulator" + AB_Valsimulator.VERSION);
        a = app;
        iPartier = new TextField("7");
        iMandat = new TextField("349");
        iSystem = new TextField("4");
        this.setLayout(new GridLayout(4, 2));
        this.add(new Label("Antal partier"));
        this.add(iPartier);
        this.add(new Label("Antal mandat"));
        this.add(iMandat);
        this.add(new Label("Procentsp�rr"));
        this.add(iSystem);
        this.add(ok);
        this.add(avsluta);
        avsluta.addActionListener(this);
        ok.addActionListener(this);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ok)) {
            try {
                new MandatRuta(new Integer(iPartier.getText()).intValue(), new Integer(iMandat.getText()).intValue(), new Integer(iSystem.getText()).intValue(), a);
            } catch (NumberFormatException ex) {
                iPartier.setText("");
                iMandat.setText("");
                iSystem.setText("");
            }
        } else System.exit(0);
    }
}
