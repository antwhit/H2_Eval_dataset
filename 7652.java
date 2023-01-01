import java.util.Iterator;
import java.util.List;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;

/**
 *
 * @author didi
 */
public class Test {

    public static void main(String[] args) {
        String login = "############";
        String passwd = "###########";
        Twitter twitter = new Twitter(login, passwd);
        List<Status> fav = twitter.getFavorites();
        Iterator<Status> favIter = fav.iterator();
        Status favStatus = null;
        System.out.println("Fetching favori ...");
        while (favIter.hasNext()) {
            favStatus = favIter.next();
            System.out.println("__________________________________________");
            System.out.println(favStatus.getCreatedAt());
            System.out.println(favStatus.getId());
            System.out.println(favStatus.getText());
            System.out.println(favStatus.getUser().getName());
            System.out.println("__________________________________________");
        }
        System.exit(0);
    }
}
