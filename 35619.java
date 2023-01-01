public class User {

    int userid;

    String username;

    String realName;

    public String toString() {
        return username + ((realName != null) ? " (" + realName + ")" : "");
    }
}
