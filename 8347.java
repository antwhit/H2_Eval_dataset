import org.makagiga.test.Ignore;
import org.makagiga.test.Test;
import org.makagiga.test.Tester;

@Test(className = Ignore.class)
public final class TestXML {

    @Test(description = "Top directory, NetBeans IDE project")
    public void test_commons() {
        Tester.testXMLFile(".classpath");
        Tester.testXMLFile(".project");
        Tester.testXMLFile("build.xml");
        Tester.testXMLFile("makagiga.jnlp");
        Tester.testXMLFile("splash.svg");
        Tester.testXMLDir("nbproject");
    }

    @Test(description = "Disk Free Example")
    public void test_examples() {
        Tester.testXMLFile("examples/commons.xml");
        Tester.testXMLFile("examples/diskfree/build.xml");
        Tester.testXMLFile("examples/minimal/build.xml");
    }

    @Test(description = "SDK Templates")
    public void test_sdk() {
        Tester.testXMLFile("sdk/SDK/template/build.xml.template");
        Tester.testXMLFile("sdk/SDK/template/findbugs-project.xml");
        Tester.testXMLFile("sdk/SDK/template/netbeans-project.xml");
    }

    @Test(description = "Tip Resources")
    public void test_tips() {
        Tester.testXMLDir("src/org/makagiga/resources/tips");
    }

    @Test(description = "Icon Theme updater")
    public void test_tools() {
        Tester.testXMLFile("tools/icon-theme/build.xml");
        Tester.testXMLDir("tools/icon-theme/nbproject");
    }
}
