public class Student extends Person {

    private int stNum;

    enum Status {

        Applied, Enrolled, Graduated, Quit
    }

    private Status status;

    private Supervisor supervisor;

    private boolean canSetStNum;

    public Student(String aName, int aStNum) {
        super(aName);
        stNum = aStNum;
        canSetStNum = false;
        setStatus(Status.Applied);
    }

    public boolean setStNum(int aStNum) {
        boolean wasSet = false;
        if (!canSetStNum) {
            return false;
        }
        canSetStNum = false;
        stNum = aStNum;
        wasSet = true;
        return wasSet;
    }

    public int getStNum() {
        return stNum;
    }

    public Status getStatus() {
        return status;
    }

    public boolean quit() {
        boolean wasEventProcessed = false;
        switch(status) {
            case Applied:
                setStatus(Status.Quit);
                wasEventProcessed = true;
                break;
            case Enrolled:
                setSupervisor(null);
                setStatus(Status.Quit);
                wasEventProcessed = true;
                break;
        }
        return wasEventProcessed;
    }

    public boolean enrol() {
        boolean wasEventProcessed = false;
        switch(status) {
            case Applied:
                setStatus(Status.Enrolled);
                wasEventProcessed = true;
                break;
        }
        return wasEventProcessed;
    }

    public boolean graduate() {
        boolean wasEventProcessed = false;
        switch(status) {
            case Enrolled:
                setSupervisor(null);
                setStatus(Status.Graduated);
                wasEventProcessed = true;
                break;
        }
        return wasEventProcessed;
    }

    private void setStatus(Status aStatus) {
        status = aStatus;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public boolean setSupervisor(Supervisor aSupervisor) {
        boolean wasSet = false;
        if (aSupervisor != null && (supervisor != null || status != Status.Enrolled)) {
            return false;
        }
        Supervisor existingSupervisor = supervisor;
        supervisor = aSupervisor;
        if (existingSupervisor != null && !existingSupervisor.equals(aSupervisor)) {
            existingSupervisor.removeStudent(this);
        }
        if (aSupervisor != null) {
            aSupervisor.addStudent(this);
        }
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        if (supervisor != null) {
            supervisor.removeStudent(this);
        }
        super.delete();
    }

    public String toString() {
        return ((getName() == null ? " " : getName()) + " status=" + getStatus() + " stNum=" + getStNum() + " Supervisor=" + (getSupervisor() == null ? "nobody" : getSupervisor().toString()));
    }
}
