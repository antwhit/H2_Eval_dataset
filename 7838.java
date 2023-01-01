import com.trilead.ssh2.KnownHosts;
import com.trilead.ssh2.ServerHostKeyVerifier;

/**
 * This example hostkey verifier is used by the
 * UsingKnownHosts.java example.
 *  
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: SimpleVerifier.java,v 1.4 2007/10/15 12:49:57 cplattne Exp $
 */
class SimpleVerifier implements ServerHostKeyVerifier {

    KnownHosts database;

    public SimpleVerifier(KnownHosts database) {
        if (database == null) throw new IllegalArgumentException();
        this.database = database;
    }

    public boolean verifyServerHostKey(String hostname, int port, String serverHostKeyAlgorithm, byte[] serverHostKey) throws Exception {
        int result = database.verifyHostkey(hostname, serverHostKeyAlgorithm, serverHostKey);
        switch(result) {
            case KnownHosts.HOSTKEY_IS_OK:
                return true;
            case KnownHosts.HOSTKEY_IS_NEW:
                database.addHostkey(new String[] { hostname }, serverHostKeyAlgorithm, serverHostKey);
                return true;
            case KnownHosts.HOSTKEY_HAS_CHANGED:
                return false;
            default:
                throw new IllegalStateException();
        }
    }
}
