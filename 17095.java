import hs485.FirmwareVersion;
import java.net.MalformedURLException;

public class GetHS485PCIVersion extends Command {

    public static FirmwareVersion getHS485PCIVersion(String urlString) throws MalformedURLException {
        return getCommunication(urlString).getHS485PCIVersion();
    }

    public static void main(String[] args) throws Exception {
        int result = 20;
        if (args.length == 1) {
            FirmwareVersion hs485PCIVersion = getHS485PCIVersion(args[0]);
            if (hs485PCIVersion != null) {
                System.out.println(hs485PCIVersion);
                result = 0;
            } else {
                System.err.println("HS485 USB module not found");
            }
        } else {
            System.err.println("usage: GetHS485PCIVersion <URL>");
        }
        System.exit(result);
    }
}
