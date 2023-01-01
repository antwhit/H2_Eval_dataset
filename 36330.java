import javax.swing.*;

public class delete extends javax.swing.JFrame {

    int gg = 0;

    /** Creates new form delete */
    public delete(int ID) {
        initComponents();
        delID(ID);
    }

    public void delID(int ID) {
        int u = JOptionPane.showConfirmDialog(null, "�� ��� ����� �� ����� �� ��� ��� ��� ����� ������");
        if (u == 0) {
            gg = 1;
            connect con = new connect();
            String sec = "delete from remainder where ID=" + ID;
            con.del(sec);
            String note = "delete from second where ID=" + ID;
            con.del(note);
            String s = "delete from employee where ID=" + ID;
            con.del(s);
            con.close2();
            this.dispose();
        }
        if (u == 2 || u == 1) {
            gg = 2;
        }
    }

    private void initComponents() {
        getContentPane().setLayout(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
            }
        });
    }
}
