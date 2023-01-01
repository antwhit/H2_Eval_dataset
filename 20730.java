import java.awt.Image;

public interface Notifier {

    boolean notifyEvent(Image img, String text);

    boolean configureNotifier(NotifierConfig config);
}
