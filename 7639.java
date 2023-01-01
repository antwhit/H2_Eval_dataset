import com.rbnb.sapi.*;

public class SendMessage {

    public static void main(String[] args) {
        try {
            Sink sink = new Sink();
            sink.OpenRBNBConnection();
            ChannelMap cm = new ChannelMap();
            int num = cm.Add("/Server/EchoMessage/text");
            cm.PutDataAsString(num, "Hello, world!");
            sink.Request(cm, 0, 0, "newest");
            ChannelMap cm2 = sink.Fetch(-1);
        } catch (Exception e) {
        }
    }
}
