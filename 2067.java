public class NetOptions {

    boolean firewall = false;

    boolean proxy = false;

    boolean authenticationRequired = false;

    short type;

    static final short HTTP = 0;

    static final short SOCKS4 = 1;

    static final short SOCKS5 = 2;

    String proxyHost;

    int proxyPort;

    String authLogin, authPasswd;

    public void setFirewall(boolean firewall) {
        this.firewall = firewall;
    }

    public boolean firewallPresent() {
        return firewall;
    }

    public void setProxy(boolean proxy) {
        this.proxy = proxy;
        if (proxy) {
            firewall = true;
        }
    }

    public boolean proxyPresent() {
        return proxy;
    }

    public void setProxyType(short type) {
        this.type = type;
        firewall = true;
        proxy = true;
    }

    public short getProxyType() {
        return type;
    }

    public void setProxyHost(String host, int port) {
        this.proxyHost = host;
        this.proxyPort = port;
        this.firewall = true;
        this.proxy = true;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setAuthentication(String login, String passwd) {
        this.proxy = true;
        this.firewall = true;
        authenticationRequired = true;
        this.authLogin = login;
        this.authPasswd = passwd;
    }

    public boolean authReqired() {
        return authenticationRequired;
    }

    public String getLogin() {
        return authLogin;
    }

    public String getPasswd() {
        return authPasswd;
    }
}
