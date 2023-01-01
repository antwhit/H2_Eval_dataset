import junit.framework.Test;
import junit.framework.TestSuite;
import meditor.CodegenTest;
import meditor.FactorizationTest;
import meditor.GeneralTest;
import meditor.GeometricTest;
import meditor.GroebnerTest;
import meditor.IntegralTest;
import meditor.MathmlTest;
import meditor.NumericTest;
import meditor.PresentationTest;
import meditor.RootTest;
import meditor.SimplificationTest;
import meditor.SubstitutionTest;
import paket.algebra.TestAlgebra;
import paket.algebra.TestPlanimetrie;
import paket.analysis.TestAnalysis;
import paket.analysis.TestExpression;
import utils.TestParser;
import utils.TestTaschenrechner;
import utils.TestUtils;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for default package");
        if (false) {
            suite.addTestSuite(CodegenTest.class);
            suite.addTestSuite(FactorizationTest.class);
            suite.addTestSuite(GeneralTest.class);
            suite.addTestSuite(GeometricTest.class);
            suite.addTestSuite(GroebnerTest.class);
            suite.addTestSuite(IntegralTest.class);
            suite.addTestSuite(MathmlTest.class);
            suite.addTestSuite(NumericTest.class);
            suite.addTestSuite(PresentationTest.class);
            suite.addTestSuite(RootTest.class);
            suite.addTestSuite(SimplificationTest.class);
            suite.addTestSuite(SubstitutionTest.class);
        }
        suite.addTestSuite(TestExpression.class);
        suite.addTestSuite(TestParser.class);
        suite.addTestSuite(TestTaschenrechner.class);
        suite.addTestSuite(TestAlgebra.class);
        suite.addTestSuite(TestPlanimetrie.class);
        suite.addTestSuite(TestAnalysis.class);
        suite.addTestSuite(TestUtils.class);
        return suite;
    }
}
