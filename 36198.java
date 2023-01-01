import java.util.*;
import java.io.File;
import javax.swing.SwingUtilities;
import org.tmatesoft.svn.core.wc.*;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;

class Svn extends Thread {

    SVNClientManager clientManager;

    SVNUpdateClient updateClient;

    SVNStatusClient statusClient;

    String urlBase = "http://terrascenery.googlecode.com/svn/trunk/data/Scenery/";

    String pathBase;

    LinkedList<TileName> syncList;

    Svn() {
        DAVRepositoryFactory.setup();
        clientManager = SVNClientManager.newInstance();
        updateClient = clientManager.getUpdateClient();
        statusClient = clientManager.getStatusClient();
        updateClient.setIgnoreExternals(false);
        syncList = new LinkedList<TileName>();
    }

    private String buildPath(String tile) {
        if (tile.length() < 7) return null;
        int lon = Integer.parseInt(tile.substring(1, 4));
        int lat = Integer.parseInt(tile.substring(5));
        char ew = tile.charAt(0);
        char ns = tile.charAt(4);
        int modlon = lon % 10;
        lon -= ew == 'w' && modlon != 0 ? modlon - 10 : modlon;
        int modlat = lat % 10;
        lat -= ns == 's' && modlat != 0 ? modlat - 10 : modlat;
        return String.format("%s%03d%s%02d/%s", ew, lon, ns, lat, tile);
    }

    void sync(Collection<TileName> set) {
        synchronized (syncList) {
            syncList.addAll(set);
        }
    }

    private void checkout(String name) {
        String[] types = { "Terrain/", "Objects/" };
        int[] ntype = { TerraMaster.TERRAIN, TerraMaster.OBJECTS };
        System.out.print("checkout " + name + "... ");
        System.out.flush();
        for (int i = 0; i < types.length; ++i) {
            String node = types[i] + name;
            try {
                SVNURL url = SVNURL.parseURIDecoded(urlBase + node);
                File f = new File(pathBase + node);
                long rev = updateClient.doCheckout(url, f, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, true);
                System.out.printf("checked out rev %s\n", rev);
                TerraMaster.addScnMapTile(TerraMaster.mapScenery, f, ntype[i]);
            } catch (SVNException x) {
                System.out.println(x.getMessage());
            } catch (Exception x) {
                System.out.println(x);
            }
        }
    }

    private void deltree(File d) {
        for (File f : d.listFiles()) {
            if (f.isDirectory()) deltree(f);
            try {
                f.delete();
            } catch (SecurityException x) {
                System.out.println(x);
            }
        }
        try {
            d.delete();
        } catch (SecurityException x) {
        }
    }

    void delete(Collection<TileName> set) {
        for (TileName n : set) {
            TileData d = TerraMaster.mapScenery.remove(n);
            if (d == null) continue;
            if (d.terrain) {
                deltree(d.dir_terr);
            }
            if (d.objects) {
                deltree(d.dir_obj);
            }
            synchronized (syncList) {
                syncList.remove(n);
            }
        }
    }

    void quit() {
        clientManager.dispose();
    }

    void setScnPath(File f) {
        pathBase = f.getPath() + "/";
    }

    private boolean noquit = true;

    public void run() {
        while (noquit) {
            if (syncList.size() > 0) {
                final TileName n;
                synchronized (syncList) {
                    n = syncList.getFirst();
                }
                String path = buildPath(n.getName());
                if (path != null) checkout(path);
                synchronized (syncList) {
                    syncList.remove(n);
                }
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        TerraMaster.frame.doSvnUpdate(n);
                    }
                });
            } else try {
                Thread.sleep(1000);
            } catch (Exception x) {
            }
        }
    }
}
