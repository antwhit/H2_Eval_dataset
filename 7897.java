import java.util.Calendar;
import java.util.Date;

public class IntDate {

    public static Date getDate(String year, String month, String day) {
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return c.getTime();
    }
}
