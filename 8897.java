import org.junit.Assert;
import org.junit.Test;
import org.specrunner.SpecRunnerServices;
import org.specrunner.result.IResultSet;

public class TestGrupo {

    @Test
    public void rodarBrowser() throws Exception {
        try {
            IResultSet result = SpecRunnerServices.getSpecRunner().run("src/test/resources/income/grupo.html");
            Assert.assertTrue(result.asString(), !result.getStatus().isError());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
