import javax.ejb.*;
import javax.naming.*;

public class MyTestBean implements SessionBean {

    String name;

    public void ejbCreate(String name) throws CreateException {
        this.name = name;
    }

    public void ejbCreate() throws CreateException {
        String str = null;
        try {
            Context ic = new InitialContext();
            Context env = (Context) ic.lookup("java:comp/env");
            str = (String) env.lookup("Default Name");
        } catch (NamingException e) {
            e.printStackTrace();
            str = "DefaultInCode";
        }
        ejbCreate(str);
    }

    public String getName() {
        return name;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public MyTestBean() {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void setSessionContext(SessionContext sc) {
    }
}
