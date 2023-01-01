import java.util.List;
import javax.naming.InitialContext;
import junit.framework.Assert;
import net.javaseminar.playlist.domain.Playlist;
import net.javaseminar.playlist.domain.Track;
import net.javaseminar.playlist.facade.PlaylistService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlaylistServiceIntegrationTest {

    private PlaylistService service;

    @Before
    public void setUp() throws Exception {
        service = (PlaylistService) new InitialContext().lookup("java:global/Tag01_Example_EAR/Tag01_Example_EJB/PlaylistFacade!" + PlaylistService.class.getName());
    }

    @Test
    public void creatPlaylistWithOneTrack_AndExpectOne() {
        Playlist list = service.newPlaylist("firstPlaylist3", 10);
        List<Track> tracksWithName = service.findTracks("Bad");
        Assert.assertFalse(tracksWithName.isEmpty());
        service.addToPlayList(list.getId(), tracksWithName.get(0).getId());
        list = service.findPlayList(list.getId());
        Assert.assertEquals(1, list.getTracks().size());
    }

    @After
    public void tearDown() throws Exception {
    }
}
