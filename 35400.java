/**
 * IAuthorizationService
 *
 * The IAuthorizationService interface allows for custom to classes to
 * be used for authentication by implementing this interface. We also
 * define standard macros here.
 *
 *
 * @author	<a href="mailto:mesullvn@uiuc.edu">Mark Sullivan</a>
 * @version 1.0
 * @requires jdk1.3+
 *
 */
public interface IAuthorizationService {

    static final short BBS_OK = 0;

    static final short BBS_ERROR_USERNAME_INVALID = 1;

    static final short BBS_ERROR_BAD_PASSWORD = 2;

    static final short BBS_ERROR_NEW_PASSWORD = 3;

    static final short BBS_ERROR_USERNAME_TAKEN = 4;

    boolean checkIfUserExists(String user);

    boolean checkPassWord(String userName, String passWord);

    int changePassWord(String userName, String oldPass, String newPass);

    int addNewUser(String userName, String passWord);
}
