import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import java.util.*;

public class PrintViewWindow extends ScheduleViewerWindow {

    public PrintViewWindow(String scheduleTitle, String[][] fullSchedule) {
        super(scheduleTitle, fullSchedule);
    }

    public void printSchedule(String scheduleTitle) {
        MessageFormat format = new MessageFormat(scheduleTitle);
        try {
            scheduleTable.print(JTable.PrintMode.FIT_WIDTH, format, null, true, null, true);
        } catch (java.awt.print.PrinterException pe) {
            JOptionPane.showMessageDialog(null, "Error: " + pe.getMessage(), "Error Printing!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
