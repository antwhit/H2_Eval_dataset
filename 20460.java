import java.awt.Toolkit;
import java.io.*;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.JOptionPane;

/**
 * @author Markus Plessing
 */
public class FilePrinter {

    PrintJobWatcher pjDone;

    /**
     * 
     * @param filepath
     * @param showConfirm
     * @param flavor
     * @param attrSet
     */
    public FilePrinter(String filepath, DocFlavor flavor, boolean showConfirm, HashPrintRequestAttributeSet attrSet) {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(filepath));
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob job = service.createPrintJob();
            Doc doc = new SimpleDoc(is, flavor, null);
            pjDone = new PrintJobWatcher(job);
            job.print(doc, attrSet);
            pjDone.waitForDone();
            is.close();
            clearJob();
            service = null;
            job = null;
            if (showConfirm) JOptionPane.showMessageDialog(MainWindow1.mainPanel, "Dokument wurde zum Drucker gesendet!", "Druckstatus", JOptionPane.INFORMATION_MESSAGE);
        } catch (PrintException ex) {
            ex.printStackTrace();
            new ErrorHandler(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            new ErrorHandler(ex);
        }
    }

    /**
     * 
     * @return boolean
     */
    public boolean isJobDone() {
        return pjDone.done;
    }

    /**
     * 
     *
     */
    public void clearJob() {
        pjDone.done = true;
        pjDone = null;
    }

    class PrintJobWatcher {

        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            job.addPrintJobListener(new PrintJobAdapter() {

                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        /**
         * 
         *
         */
        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                new ErrorHandler(ex);
            }
        }
    }
}
