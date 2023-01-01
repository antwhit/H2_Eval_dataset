public class StudentRecord {

    private String fname;

    private String lname;

    private String mub;

    private String id;

    private String free;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMub() {
        return mub;
    }

    public void setMub(String mub) {
        this.mub = mub;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String toString() {
        return fname + ";" + lname + ";" + mub + ";" + id + ";" + free;
    }
}
