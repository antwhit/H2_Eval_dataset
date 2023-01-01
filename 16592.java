/**
 *
 * @author me
 */
public interface QuaterlyScheduleListener {

    public void handleSchedule(CourseDescriptor[] courses, boolean[] selected, int cumUnits) throws Exception;
}
