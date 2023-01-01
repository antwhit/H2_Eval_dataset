import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import code.*;

public class OccupantListInRoom extends fit.RowFixture {

    private ChatServer chat = new ChatServer();

    public Object[] query() throws Exception {
        List occupancies = new ArrayList();
        collectOccupants(occupancies, chat.room(args[0]));
        return occupancies.toArray();
    }

    public Class getTargetClass() {
        return Occupancy.class;
    }

    private void collectOccupants(List occupancies, Room room) {
        for (Iterator it = room.users(); it.hasNext(); ) {
            User user = (User) it.next();
            Occupancy occupant = new Occupancy(room.getName(), user.getName());
            occupancies.add(occupant);
        }
    }
}
