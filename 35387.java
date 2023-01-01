import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class ReportBarang extends JInternalFrame {

    private JButton btnCetak1 = new JButton("Print");

    ReportBarang() {
        super("Report (Laporan) Data Barang", false, true, false, true);
        setSize(350, 150);
        btnCetak1.setBounds(100, 28, 85, 35);
        btnCetak1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                PrinterJob PrintOK1 = PrinterJob.getPrinterJob();
                PrintOK1.setPrintable(new isiRecordBarang());
                if (PrintOK1.printDialog()) {
                    try {
                        PrintOK1.print();
                    } catch (PrinterException e) {
                        System.out.println(e);
                    }
                }
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(btnCetak1);
        setVisible(true);
    }
}

class isiRecordBarang implements Printable {

    private java.util.Date currDate = new java.util.Date();

    private SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private String d1 = sdf1.format(currDate);

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        try {
            Graphics2D g1 = (Graphics2D) graphics;
            g1.setFont(new Font("Arial", Font.ITALIC, 12));
            g1.setColor(Color.black);
            Connect ObjKoneksi1 = new Connect();
            Connection con1 = ObjKoneksi1.OpenConnect();
            Statement st1 = con1.createStatement();
            String sql1 = "SELECT * FROM Barang";
            ResultSet rs1 = st1.executeQuery(sql1);
            int i1, x1, y1;
            g1.drawString("Report (Laporan) Data Barang", 100, 120);
            g1.drawString("Kelompok 1", 100, 130);
            g1.drawLine(100, 145, 600, 145);
            g1.drawString("No. ", 100, 155);
            g1.drawString("Kode Barang", 130, 155);
            g1.drawString("Nama Barang", 200, 155);
            g1.drawString("Harga Jual", 350, 155);
            g1.drawString("Harga Beli", 450, 155);
            g1.drawLine(100, 165, 600, 165);
            x1 = 100;
            y1 = 190;
            i1 = 1;
            while (rs1.next()) {
                g1.drawString("" + i1 + ".", 100, y1);
                g1.drawString(rs1.getString(1), 130, y1);
                g1.drawString(rs1.getString(2), 200, y1);
                g1.drawString(rs1.getString(3), 350, y1);
                g1.drawString(rs1.getString(4), 450, y1);
                y1 += 14;
                i1++;
            }
            g1.drawLine(x1, y1, 600, y1);
            g1.drawString("Tanggal Cetak : " + d1, 100, y1 + 14);
            rs1.close();
            con1.close();
        } catch (Exception e) {
        }
        return PAGE_EXISTS;
    }
}
