import java.awt.Container;
import javax.microedition.xlet.UnavailableContainerException;
import javax.microedition.xlet.XletContext;

public class RestrictedXletContext implements XletContext {

    private ClassLoader classLoader;

    public RestrictedXletContext(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void notifyDestroyed() {
    }

    public void notifyPaused() {
    }

    public Object getXletProperty(String key) {
        return null;
    }

    public void resumeRequest() {
    }

    public Container getContainer() throws UnavailableContainerException {
        return null;
    }
}
