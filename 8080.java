import java.lang.Math;
import java.text.DecimalFormat;

public class FireAlarm extends SecurityAlarm {

    final double MAX_TEMP_THRESHOLD = 200.0;

    final double TEMP_FAIL_THRESHOLD = 40.0;

    double tempThreshold = 0.0;

    double tempSensor = 0.0;

    String location;

    public FireAlarm(String loc) {
        location = new String(loc);
        tempThreshold = MAX_TEMP_THRESHOLD;
    }

    public FireAlarm(String loc, double threshold) {
        location = new String(loc);
        if (threshold < MAX_TEMP_THRESHOLD) tempThreshold = threshold; else tempThreshold = MAX_TEMP_THRESHOLD;
    }

    public int sensorSample() {
        int status = ALARM_NORMAL;
        DecimalFormat twoDigits = new DecimalFormat("##,##0.0");
        tempSensor = Math.random() * 1000;
        if (tempSensor > tempThreshold) {
            alarm();
            status = ALARM_ACTIVATED;
        } else if (tempSensor < TEMP_FAIL_THRESHOLD) {
            alarmFailure("\nThe " + location + " fire alarm senses a temperature of " + twoDigits.format(tempSensor) + "\nwhich is below the failure threshold of " + TEMP_FAIL_THRESHOLD);
            status = ALARM_FAILURE;
        }
        return status;
    }

    public void alarm() {
        DecimalFormat twoDigits = new DecimalFormat("##,##0.0");
        System.out.println("*** The " + location + " fire alarm sensor has detected a potential fire.");
        System.out.println("*** The temperature of " + twoDigits.format(tempSensor) + " has exceeded the threshold of " + tempThreshold + "\n");
    }
}
