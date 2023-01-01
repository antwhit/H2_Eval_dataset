import code.ChatRoom;
import code.User;
import fitlibrary.DoFixture;

/**
 * 
 */
public class UserFixture extends DoFixture {

    private ChatRoom chat;

    private User user;

    public UserFixture(ChatRoom chat, User user) {
        this.chat = chat;
        this.user = user;
    }

    public boolean createsRoom(String roomName) {
        chat.createRoom(roomName, user);
        return true;
    }

    public boolean entersRoom(String roomName) {
        return chat.userEntersRoom(user.getName(), roomName);
    }
}
