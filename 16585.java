import fr.esrf.Tango.*;
import fr.esrf.TangoDs.*;
import fr.esrf.TangoApi.*;

/**
 * @author	$Author: pascal_verdier $
 * @version	$Revision: 9742 $
 */
public class TangoclassProxy extends DeviceProxy {

    /**
	 *	DeviceData object used for command input parameter(s)
	 */
    private DeviceData data_in;

    /**
	 *	DeviceData object used for command output parameter(s)
	 */
    private DeviceData data_out;

    /**
	 *	Tango device name.
	 */
    public String name;

    public TangoclassProxy(String devname) throws DevFailed {
        super(devname);
        data_in = new DeviceData();
        data_out = new DeviceData();
        name = devname;
    }

    public static void main(String[] argv) {
        try {
            TangoclassProxy client = new TangoclassProxy("");
        } catch (DevFailed e) {
            Except.print_exception(e);
        }
    }
}
