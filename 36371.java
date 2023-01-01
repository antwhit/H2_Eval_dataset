import java.sql.*;

/**
 * Encapsulates a request to add an event
 */
public class AddEventRequest extends GCalendarRequest {

    private String eventId;

    private String date;

    private String title;

    /** Creates a new instance of AddEventRequest */
    protected AddEventRequest(int requestId, String eventId, String date, String title) {
        super(requestId);
        setEventId(eventId);
        setDate(date);
        setTitle(title);
    }

    public String toString() {
        return "Request # " + getId() + " to add event (" + getDate() + ": " + getTitle() + ")";
    }

    public void submit(GCalendar calendar) throws Exception {
        CalEvent event = calendar.addEvent(getDate(), getTitle());
        EventManager.updateEventId(eventId, event.getId());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
