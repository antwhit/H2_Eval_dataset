import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Settlement extends Unit {

    protected Vector people;

    protected Vector vehicles;

    public Settlement(int unitID, String name, Coordinates location, VirtualMars mars, UnitManager manager) {
        super(unitID, name, location, mars, manager);
        people = new Vector();
        vehicles = new Vector();
    }

    public int getPeopleNum() {
        return people.size();
    }

    public int getVehicleNum() {
        return vehicles.size();
    }

    public Person getPerson(int index) {
        if (index < people.size()) return (Person) people.elementAt(index); else return null;
    }

    public Vehicle getVehicle(int index) {
        if (index < vehicles.size()) return (Vehicle) vehicles.elementAt(index); else return null;
    }

    public void addPerson(Person newPerson) {
        people.addElement(newPerson);
    }

    public void personLeave(Person person) {
        if (people.contains(person)) people.removeElement(person);
    }

    public void addVehicle(Vehicle newVehicle) {
        vehicles.addElement(newVehicle);
    }

    public void vehicleLeave(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) vehicles.removeElement(vehicle);
    }

    public UnitDialog getDetailWindow(MainDesktopPane parentDesktop) {
        return new SettlementDialog(parentDesktop, this);
    }
}
