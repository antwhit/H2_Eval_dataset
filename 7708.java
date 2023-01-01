import ac.hiu.j314.elmve.*;
import java.util.*;

public class MyRoom extends RealElm {

    public void searchNextElm(Request r) {
        ArrayList al = getElms(name + "/@OrderExam");
        ArrayList reqs = makeRequests(al, "searchNextElm", null);
        prepareForReply(reqs, "searchNextElm2", r);
        sendMessages(reqs);
    }

    public void searchNextElm2(ReplySet rs) {
        sendMessage(makeReply(rs.getRequestArgAt(0), "Ok"));
    }

    public void loop(Request r) {
        Elm elm = getElm(name + "/node1");
        sendMessage(makeOrder(elm, "loop", null));
        sendMessage(makeReply(r, "Ok."));
    }
}
