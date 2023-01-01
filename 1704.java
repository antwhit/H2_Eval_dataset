/**
 * 
 */
public class Authentication {

    /**
       *
       */
    public Authentication() {
    }

    /**
       * Returns true if the user is properly authenticated.
       */
    public boolean login(String username, String password) {
        return (username.equals("johnd") && password.equals("secret"));
    }

    /**
       * Returns a user with the specifed username.
       */
    public User getUserWithName(String username) {
        return new User("John Doe", "johnd", "secret");
    }
}
