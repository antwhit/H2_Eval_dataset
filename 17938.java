import java.util.*;

/**
 * AppointmentController is the maintainer class for all appointments. It
 * provides storage, addition, removal, edit functions.
 * 
 * (In alphabetical order)
 * @author Max Gilman
 * @author Dustin Howett
 * @author Lemuel Lebron
 * @author Gordon Mascarenhas
 * @author PueyWei Tan
 * 
 */
public class AppointmentController {

    public TreeMap<Integer, Appointment> holder;

    private static Database db;

    /**
	 * Constructor that takes in a database and creates a tree to store the appointments
	 * @param db Database connection
	 */
    public AppointmentController(Database db) {
        holder = new TreeMap<Integer, Appointment>();
        this.db = db;
    }

    /**
	 * Adds an appointment to the collection in the class
	 * @param desc Appointment description
	 * @param start Start date/time
	 * @param end End date/time
	 * @return a unique appointment id
	 */
    public int addAppointment(String desc, GregorianCalendar start, GregorianCalendar end) {
        int aID = db.getNextAppointmentIndex();
        holder.put(aID, new Appointment(desc, start, end, aID));
        return aID;
    }

    /**
	 * Edits the contents of a currently existing appointment
	 * 
	 * @param aID Appointment ID number
	 * @param desc New description
	 * @param start New start date/time
	 * @param end New end date/time
	 */
    public void editAppointment(int aID, String desc, GregorianCalendar start, GregorianCalendar end) {
        holder.get(aID).setStart(start);
        holder.get(aID).setEnd(end);
        holder.get(aID).setDesc(desc);
    }

    /**
	 * Removes the specified Appointment from the collection
	 * 
	 * @param aID
	 *            Unique Appointment ID (int)
	 * 
	 */
    public void deleteAppointment(int aID) {
        if (holder.containsKey(aID)) {
            holder.remove(aID);
        }
    }

    /**
	 * Gets a Collection of type Appointment of all the appointments
	 * 
	 * @return Collection of Appointments
	 */
    public Collection<Appointment> getAllAppointments() {
        return holder.values();
    }

    /**
	 * Gets a Collection of type Integer of all the appointmentIDs
	 * 
	 * @return Collection of AppointmentsIDs
	 */
    public Collection<Integer> getAllAppointmentIDs() {
        return holder.keySet();
    }

    /**
	 * Get one appointment, given it's aID
	 * 
	 * @return Appointment sought, or null if an invalid key is given
	 */
    public Appointment getAppointment(int aID) {
        return holder.get(aID);
    }

    /**
	 * Returns true if key aID exists
	 * 
	 * @param aID
	 *            Key
	 * @return True if exists, false if not
	 */
    public boolean contains(int aID) {
        return holder.containsKey(aID);
    }

    /**
	 * For testing purposes
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    }
}
