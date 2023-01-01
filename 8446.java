import org.jbehave.core.mock.UsingMatchers;

public class CottaBehaviour extends UsingMatchers {

    public void shouldReportInfoOnMain() throws Exception {
        Cotta.main(new String[0]);
    }
}
