public class HourlyEmployee extends Employee {

    private double wage;

    private double hours;

    public HourlyEmployee(String first, String last, String ssn, double hourlyWage, double hoursWorked) {
        super(first, last, ssn);
        sethourlyWage(hourlyWage);
        sethoursWorked(hoursWorked);
    }

    public void sethourlyWage(double w) {
        wage = (w < 0.0) ? 0.0 : w;
    }

    public double gethourlyWage() {
        return wage;
    }

    public void sethoursWorked(double h) {
        hours = (h < 0.0) ? 0.0 : h;
    }

    public double gethoursWorked() {
        return hours;
    }

    @Override
    public double earnings() {
        return gethourlyWage() * gethoursWorked();
    }

    @Override
    public String toString() {
        String temp = "First Name: " + getfirstName() + "\n" + "Last Name: " + getlastName() + "\n" + "SSN: " + getSsn() + "\n" + "Hours Worked: " + gethoursWorked() + "\n" + "Hourly Wage: " + gethourlyWage() + "\n" + "Salary: " + earnings() + "\n";
        return temp;
    }
}
