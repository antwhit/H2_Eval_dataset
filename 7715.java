import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;

public class PaginationExample implements Printable, ActionListener {

    int[] pageBreaks;

    String[] textLines;

    private void initTextLines() {
        if (textLines == null) {
            int numLines = 200;
            textLines = new String[numLines];
            for (int i = 0; i < numLines; i++) {
                textLines[i] = "This is line number " + i;
            }
        }
    }

    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        Font font = new Font("Serif", Font.PLAIN, 10);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight();
        if (pageBreaks == null) {
            initTextLines();
            int linesPerPage = (int) (pf.getImageableHeight() / lineHeight);
            int numBreaks = (textLines.length - 1) / linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b = 0; b < numBreaks; b++) {
                pageBreaks[b] = (b + 1) * linesPerPage;
            }
        }
        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        int y = 0;
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex - 1];
        int end = (pageIndex == pageBreaks.length) ? textLines.length : pageBreaks[pageIndex];
        for (int line = start; line < end; line++) {
            y += lineHeight;
            g.drawString(textLines[line], 0, y);
        }
        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
            }
        }
    }

    public static void main(String args[]) {
        try {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn);
        } catch (Exception cnf) {
        }
        JFrame f = new JFrame("Printing Pagination Example");
        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JButton printButton = new JButton("Print Pages");
        printButton.addActionListener(new PaginationExample());
        f.add("Center", printButton);
        f.pack();
        f.setVisible(true);
    }
}
