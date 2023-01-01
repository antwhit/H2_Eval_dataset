import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import map.items.*;
import java.sql.*;

public class UserData implements Serializable {

    PC pc;

    String password;

    Timestamp timestamp;

    boolean active;

    int ID;

    public UserData(PC pc, String password) {
        this.pc = pc;
        this.password = password;
    }

    public void setPass(String pass) {
        this.password = pass;
    }

    public String getPass() {
        return password;
    }
}
