import org.junit.Assert;
import org.junit.Test;
import org.specrunner.SpecRunnerServices;
import org.specrunner.result.IResultSet;

public class TestBrowser {

    @Test
    public void rodarBrowser() throws Exception {
        try {
            IResultSet result = SpecRunnerServices.getSpecRunner().run("src/test/resources/income/browser.html");
            Assert.assertTrue(result.asString(), !result.getStatus().isError());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void rodarBusca() throws Exception {
        try {
            IResultSet result = SpecRunnerServices.getSpecRunner().run("src/test/resources/income/busca.html");
            Assert.assertTrue(result.asString(), !result.getStatus().isError());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void rodarArvore() throws Exception {
        try {
            IResultSet result = SpecRunnerServices.getSpecRunner().run("src/test/resources/income/arvore.html");
            Assert.assertTrue(result.asString(), !result.getStatus().isError());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        new TestBrowser().rodarBrowser();
    }
}
