import net.sourceforge.cruisecontrol.launch.Launcher;

/**
 * This class wraps the MasterBuild process to snazzify the command line.
 * (instead of "java net.sourceforge.cruisecontrol.launch.Launcher" the command line is
 * snazzier, in the form of "java CruiseControl")
 *
 * @author <a href="mailto:alden@mac.com">alden almagro</a>
 * @author <a href="mailto:pj@thoughtworks.com">Paul Julius</a>
 * @author <a href="mailto:jcyip@thoughtworks.com">Jason Yip</a>
 *
 * @deprecated since 2.6, use the Launcher class instead.
 */
public final class CruiseControl {

    private CruiseControl() {
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
