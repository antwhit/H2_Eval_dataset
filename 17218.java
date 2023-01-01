import java.util.HashMap;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import org.openliberty.arisid.jaas.ArisIdLoginModule;

public class TestSecurityConfig extends Configuration {

    public static String TEST_CONFIG_APPNAME = "TestIGF-OpenDs";

    public AppConfigurationEntry appEntry;

    @SuppressWarnings("unchecked")
    public TestSecurityConfig() {
        String module = ArisIdLoginModule.class.getName();
        appEntry = new AppConfigurationEntry(module, AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, new HashMap());
    }

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        if (name.equals(TEST_CONFIG_APPNAME)) {
            AppConfigurationEntry[] res = new AppConfigurationEntry[1];
            res[0] = appEntry;
            return res;
        }
        return null;
    }

    @Override
    public void refresh() {
    }
}
