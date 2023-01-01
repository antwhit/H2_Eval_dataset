import uk.ac.shef.oak.iracema.AktiveMediaDocumentTests;
import uk.ac.shef.oak.iracema.TokenTests;
import uk.ac.shef.oak.iracema.annotation.L1L2AnnotatorTests;
import uk.ac.shef.oak.iracema.annotation.SaxonAnnotatorTests;
import uk.ac.shef.oak.iracema.classifier.ClassifierUtilsTests;
import uk.ac.shef.oak.iracema.document.DocumentsUtilsTests;
import uk.ac.shef.oak.iracema.features.token.TokenFeaturesTests;
import uk.ac.shef.oak.iracema.rune.RuneAnnotationsLengthHistogramTests;
import uk.ac.shef.oak.iracema.rune.RuneBoundaryTagCombinerTests;
import uk.ac.shef.oak.iracema.rune.RuneBoundaryTaggerL2Tests;
import uk.ac.shef.oak.iracema.rune.RuneLoadDocumentsTests;
import uk.ac.shef.oak.iracema.rune.RuneSlidingWindowInstanceFactoryTests;
import uk.ac.shef.oak.iracema.rune.RuneTextAnnotationsExtractorTests;
import uk.ac.shef.oak.iracema.rune.RuneTextTokeniserNoTagsTests;
import uk.ac.shef.oak.iracema.rune.RuneTokenInstanceFactoryL1Tests;
import uk.ac.shef.oak.iracema.rune.RuneTokenInstanceFactoryL2Tests;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestAll extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(RuneAnnotationsLengthHistogramTests.class);
        suite.addTestSuite(RuneBoundaryTagCombinerTests.class);
        suite.addTestSuite(RuneBoundaryTaggerL2Tests.class);
        suite.addTestSuite(RuneLoadDocumentsTests.class);
        suite.addTestSuite(RuneSlidingWindowInstanceFactoryTests.class);
        suite.addTestSuite(RuneTextAnnotationsExtractorTests.class);
        suite.addTestSuite(RuneTextTokeniserNoTagsTests.class);
        suite.addTestSuite(RuneTokenInstanceFactoryL1Tests.class);
        suite.addTestSuite(RuneTokenInstanceFactoryL2Tests.class);
        suite.addTestSuite(TokenFeaturesTests.class);
        suite.addTestSuite(DocumentsUtilsTests.class);
        suite.addTestSuite(ClassifierUtilsTests.class);
        suite.addTestSuite(L1L2AnnotatorTests.class);
        suite.addTestSuite(AktiveMediaDocumentTests.class);
        suite.addTestSuite(TokenTests.class);
        suite.addTestSuite(SaxonAnnotatorTests.class);
        return suite;
    }
}
