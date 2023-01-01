import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {

    private JFrame frame = new JFrame("Min Alarmklocka");

    private JPanel background, currentTimePanel, chooseTimePanel, buttonPanel;

    private JLabel currentTime;

    private JSpinner s_Hour, s_Min, s_Sec;

    private String[] hour, minSec;

    private AlarmClock aClock;

    public GUI() {
        background = new JPanel();
        currentTimePanel = new JPanel();
        chooseTimePanel = new JPanel();
        buttonPanel = new JPanel();
        hour = new String[24];
        minSec = new String[60];
        hour = makeArray(true);
        minSec = makeArray(false);
        SpinnerListModel hourModel = new SpinnerListModel(hour);
        SpinnerListModel minModel = new SpinnerListModel(minSec);
        SpinnerListModel secModel = new SpinnerListModel(minSec);
        s_Hour = new JSpinner(hourModel);
        s_Min = new JSpinner(minModel);
        s_Sec = new JSpinner(secModel);
        JLabel CurrentTimeDisp = new JLabel("Current time: ");
        currentTime = new JLabel("00:00:00");
        JButton alarmOn = new JButton("On");
        AlarmOnListener AlarmOnLisen = new AlarmOnListener();
        alarmOn.addActionListener(AlarmOnLisen);
        JButton alarmOff = new JButton("Off");
        AlarmOffListener AlarmOffLisen = new AlarmOffListener();
        alarmOff.addActionListener(AlarmOffLisen);
        JButton alarmSnooze = new JButton("Snooze");
        AlarmSnoozeListener AlarmSnoozeLisen = new AlarmSnoozeListener();
        alarmSnooze.addActionListener(AlarmSnoozeLisen);
        currentTimePanel.add(CurrentTimeDisp);
        currentTimePanel.add(currentTime);
        chooseTimePanel.add(s_Hour);
        chooseTimePanel.add(s_Min);
        chooseTimePanel.add(s_Sec);
        buttonPanel.add(alarmOn);
        buttonPanel.add(alarmOff);
        buttonPanel.add(alarmSnooze);
        background.setLayout(new BorderLayout());
        background.add(currentTimePanel, BorderLayout.SOUTH);
        background.add(chooseTimePanel, BorderLayout.NORTH);
        background.add(buttonPanel, BorderLayout.CENTER);
        aClock = new AlarmClock(this);
        frame.setContentPane(background);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WC());
    }

    private class WC extends WindowAdapter {

        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }

    private String[] makeArray(boolean isHour) {
        int time = 59;
        if (isHour) {
            time = 23;
        }
        String[] returnArray = new String[time + 1];
        for (int x = 0; x <= time; x++) {
            returnArray[x] = "" + x;
            if (x < 10) {
                returnArray[x] = "0" + returnArray[x];
            }
        }
        return returnArray;
    }

    public class AlarmOnListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            String alarmTime = "" + s_Hour.getValue().toString() + ":" + s_Min.getValue().toString() + ":" + s_Sec.getValue().toString() + "";
            aClock.setAlarmTime(alarmTime);
        }
    }

    public class AlarmOffListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            aClock.unSetAlarmTime();
        }
    }

    public class AlarmSnoozeListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            aClock.setNewAlarmTime();
        }
    }

    public void setTime(String newTime) {
        currentTime.setText(newTime);
        frame.repaint();
    }

    public void alarmRing() {
        System.out.println("iRing");
        for (int x = 0; x < 100000000; x++) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            try {
                Thread.sleep(x % 7);
            } catch (Exception e) {
            }
        }
    }
}
