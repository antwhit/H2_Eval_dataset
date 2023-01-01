import java.awt.BorderLayout;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import edu.upmc.opi.caBIG.caTIES.middletier.CaTIES_PathologyReportImpl;

public class MainClass {

    private static final String ACCESSION_NO_LABEL = "ACCESSION NO:";

    private static final String COLLECTION_DATE_LABEL = "COLLECTION DATE:";

    private static final String PATIENT_AGE_LABEL = "AGE AT COLLECTION:";

    private static final String COLLECTION_YEAR_LABEL = "COLLECTION YEAR:";

    private static final String TISSUE_LABEL = "TISSUE AVAILABILITY:";

    private CaTIES_PathologyReportImpl document;

    private JTextPane textPane;

    private static String getTimeDiff(Date collectionDateTime) {
        Date context = new Date();
        long delta = context.getTime() - collectionDateTime.getTime();
        long x = delta / 1000;
        long seconds = x % 60;
        x /= 60;
        long minutes = x % 60;
        x /= 60;
        float hours = x % 24;
        x /= 24;
        float days = x % 30;
        x /= 30;
        float months = x % 12;
        x /= 12;
        float years = x;
        return null;
    }

    public static void main(final String args[]) {
        getTimeDiff(new Date());
        SimpleAttributeSet TITLE_STYLE;
        SimpleAttributeSet REGULAR_STYLE;
        SimpleAttributeSet PARA_STYLE;
        SimpleAttributeSet SECONDCOLUMN_STYLE;
        JFrame frame = new JFrame("Tab Attributes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StyledDocument text = new DefaultStyledDocument();
        PARA_STYLE = new SimpleAttributeSet();
        TabStop tabstop = new TabStop(120, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
        TabSet tabset = new TabSet(new TabStop[] { tabstop });
        StyleConstants.setTabSet(PARA_STYLE, tabset);
        SECONDCOLUMN_STYLE = new SimpleAttributeSet();
        tabstop = new TabStop(350, TabStop.ALIGN_LEFT, TabStop.LEAD_DOTS);
        tabset = new TabSet(new TabStop[] { tabstop });
        StyleConstants.setTabSet(SECONDCOLUMN_STYLE, tabset);
        TITLE_STYLE = new SimpleAttributeSet();
        StyleConstants.setFontFamily(TITLE_STYLE, "SansSerif");
        StyleConstants.setFontSize(TITLE_STYLE, 11);
        StyleConstants.setBold(TITLE_STYLE, true);
        REGULAR_STYLE = new SimpleAttributeSet();
        StyleConstants.setFontFamily(REGULAR_STYLE, "SansSerif");
        StyleConstants.setFontSize(REGULAR_STYLE, 11);
        try {
            text.insertString(text.getLength(), ACCESSION_NO_LABEL, TITLE_STYLE);
            text.insertString(text.getLength(), "\t" + "dsfsd sdfsdf sdf sdf sdf" + "\n", REGULAR_STYLE);
            text.insertString(text.getLength(), COLLECTION_YEAR_LABEL, TITLE_STYLE);
            text.insertString(text.getLength(), "\t" + "2008" + "\n", REGULAR_STYLE);
            text.insertString(text.getLength(), PATIENT_AGE_LABEL, TITLE_STYLE);
            text.insertString(text.getLength(), "\t" + "24" + "\n", REGULAR_STYLE);
            text.insertString(text.getLength(), TISSUE_LABEL, TITLE_STYLE);
            text.insertString(text.getLength(), "\t" + "Unknown" + "\n", REGULAR_STYLE);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        JTextPane textPane = new JTextPane(text);
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}
