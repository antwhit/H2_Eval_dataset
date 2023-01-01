import java.util.Vector;
import java.awt.*;
import java.awt.event.*;

public class AWTAltaClientes implements ActionListener {

    Paises paises = new Paises();

    Paises vpais = new Paises();

    Vector Vpaises = paises.cargarTodos();

    Identificaciones identificaciones = new Identificaciones();

    Identificaciones videntificacion = new Identificaciones();

    Vector Videntificaciones = identificaciones.cargarTodos();

    public Frame frame = new Frame("Alta Cliente");

    public Label lbl1 = new Label("Nombre");

    public TextField tf1 = new TextField();

    public Label lbl2 = new Label("Apellido");

    public TextField tf2 = new TextField();

    public Label lbl3 = new Label("Documento");

    public TextField tf3 = new TextField();

    public Label lbl4 = new Label("Tipo Doc.");

    public Choice tf4 = new Choice();

    public Label lbl5 = new Label("Pais");

    public Choice tf5 = new Choice();

    public Label lbl6 = new Label("Direccion");

    public TextField tf6 = new TextField();

    public Label lbl7 = new Label("Telefono");

    public TextField tf7 = new TextField();

    public Label lbl8 = new Label("Movil");

    public TextField tf8 = new TextField();

    public Label lbl9 = new Label("Mail");

    public TextField tf9 = new TextField();

    public Label lbl10 = new Label("Licencia");

    public TextField tf10 = new TextField();

    public Label lbl11 = new Label("Licencia Vto.   (DD/MM/AAAA)");

    public TextField tf11 = new TextField();

    public Label lbl12 = new Label("Licencia Pais");

    public Choice tf12 = new Choice();

    private Button btn1 = new Button("Guardar");

    private Button btn2 = new Button("Cancelar");

    public AWTAltaClientes() {
        frame.setLayout(new GridLayout(13, 2));
        frame.setSize(600, 415);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                AWTMantenimientoClientes ma = new AWTMantenimientoClientes();
                frame.dispose();
            }
        });
        for (int i = 0; i < Vpaises.size(); i++) {
            tf5.add(((Paises) Vpaises.get(i)).obtenerPais());
            tf12.add(((Paises) Vpaises.get(i)).obtenerPais());
        }
        for (int i = 0; i < Videntificaciones.size(); i++) {
            tf4.add(((Identificaciones) Videntificaciones.get(i)).obtenerNombreDocumento());
        }
        frame.add(lbl1);
        frame.add(tf1);
        frame.add(lbl2);
        frame.add(tf2);
        frame.add(lbl3);
        frame.add(tf3);
        frame.add(lbl4);
        frame.add(tf4);
        frame.add(lbl5);
        frame.add(tf5);
        frame.add(lbl6);
        frame.add(tf6);
        frame.add(lbl7);
        frame.add(tf7);
        frame.add(lbl8);
        frame.add(tf8);
        frame.add(lbl9);
        frame.add(tf9);
        frame.add(lbl10);
        frame.add(tf10);
        frame.add(lbl11);
        frame.add(tf11);
        frame.add(lbl12);
        frame.add(tf12);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        frame.add(btn1);
        frame.add(btn2);
        frame.show();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            vpais = (Paises) Vpaises.elementAt(tf5.getSelectedIndex());
            int b = vpais.obtenerId();
            vpais = (Paises) Vpaises.elementAt(tf12.getSelectedIndex());
            int c = vpais.obtenerId();
            videntificacion = (Identificaciones) Videntificaciones.elementAt(tf4.getSelectedIndex());
            int a = videntificacion.obtenerId();
            Clientes ctmp = new Clientes(tf3.getText(), b, a, tf1.getText(), tf2.getText(), tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText(), tf10.getText(), tf11.getText(), c);
            boolean res = ctmp.ingresarCliente();
            if (res) {
                AWTMantenimientoClientes ma = new AWTMantenimientoClientes();
                frame.dispose();
            }
            if (!res) {
                AWTMensaje m = new AWTMensaje("ERROR", "La Entrada no pudo ser guardada ");
            }
        }
        if (e.getSource() == btn2) {
            AWTMantenimientoClientes ma = new AWTMantenimientoClientes();
            frame.dispose();
        }
    }
}
