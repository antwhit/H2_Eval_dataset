public class User {

    private String uid;

    private String pwd;

    public User() {
        this("default", "default");
    }

    public User(String puid, String ppwd) {
        uid = puid;
        pwd = ppwd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean checkPassword(String pass) {
        if (pass == null) return false; else return pass.equals(pwd);
    }
}
