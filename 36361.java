import org.makagiga.test.Test;
import org.makagiga.test.Tester;

public final class TestXML {

    @Test
    public void test_commons() {
        Tester.testXMLFile("build.xml");
        Tester.testXMLFile("makagiga.jnlp");
        Tester.testXMLFile("src/org/makagiga/desktop/calendar/desklet.xml");
        Tester.testXMLDir("test/src/ghns/testdata");
    }

    @Test
    public void test_sdk() {
        Tester.testXMLFile("sdk/SDK/template/build.xml.template");
        Tester.testXMLFile("sdk/SDK/template/netbeans-project.xml");
    }

    @Test
    public void test_tips() {
        Tester.testXMLDir("src/resources/tips");
    }
}
