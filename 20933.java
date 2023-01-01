import junit.framework.Test;
import junit.framework.TestSuite;
import org.xaware.api.XAwareAPITestSuite;
import org.xaware.functoids.FunctoidsTestSuite;
import org.xaware.server.applications.ClientApplicationTestSuite;
import org.xaware.server.common.ServerCommonTestSuite;
import org.xaware.server.common.XMLNamespaceUtilTestSuite;
import org.xaware.server.connector.servlet.HTTPServletTestSuite;
import org.xaware.server.connector.servlet.SoapServletTestSuite;
import org.xaware.server.engine.channel.ChannelTestSuite;
import org.xaware.server.engine.context.ContextTestSuite;
import org.xaware.server.engine.context.SubstitutionHelperTestSuite;
import org.xaware.server.engine.controller.transaction.TransactionTestSuite;
import org.xaware.server.engine.enums.EnumsTestSuite;
import org.xaware.server.engine.instruction.InstructionTestSuite;
import org.xaware.server.engine.instruction.bizcomps.copybook.CopybookTestSuite;
import org.xaware.server.engine.instruction.bizcomps.file.FileTestSuite;
import org.xaware.server.engine.instruction.bizcomps.ftp.FTPTestSuite;
import org.xaware.server.engine.instruction.bizcomps.http.HttpTestSuite;
import org.xaware.server.engine.instruction.bizcomps.java.JavaTestSuite;
import org.xaware.server.engine.instruction.bizcomps.multiformat.MultiformatTestSuite;
import org.xaware.server.engine.instruction.bizcomps.soap.SoapDocLitTestSuite;
import org.xaware.server.engine.instruction.bizcomps.soap.SoapV2RpcEncodedTestSuite;
import org.xaware.server.engine.instruction.bizcomps.soap.SoapV2RpcLiteralTestSuite;
import org.xaware.server.engine.instruction.bizcomps.xmlmapper.XMLMapperTestSuite;
import org.xaware.server.engine.integration.IntegrationTestSuite;
import org.xaware.server.engine.integration.outstream.OutStreamingTestSuite;
import org.xaware.server.engine.integration.xsl.XSLBizCompTestSuite;
import org.xaware.server.engine.utils.SearchPathHelperTestSuite;
import org.xaware.server.management.statistics.XAwareStatisticsTestSuite;
import org.xaware.server.resources.ResourceTestSuite;
import org.xaware.server.streaming.StreamingTestSuite;
import org.xaware.shared.util.SharedUtilsTestSuite;

/**
 * @author tferguson
 *
 */
public class XAwareDeveloperTests extends TestSuite {

    /**
     * 
     * @return Test
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Test for XAware Developers to run external to XAware network");
        suite.addTest(XAwareAPITestSuite.suite());
        suite.addTest(FunctoidsTestSuite.suite());
        suite.addTest(ClientApplicationTestSuite.suite());
        suite.addTest(ServerCommonTestSuite.suite());
        suite.addTest(XMLNamespaceUtilTestSuite.suite());
        suite.addTest(HTTPServletTestSuite.suite());
        suite.addTest(SoapServletTestSuite.suite());
        suite.addTest(ContextTestSuite.suite());
        suite.addTest(SubstitutionHelperTestSuite.suite());
        suite.addTest(EnumsTestSuite.suite());
        suite.addTest(ChannelTestSuite.suite());
        suite.addTest(TransactionTestSuite.suite());
        suite.addTest(InstructionTestSuite.suite());
        suite.addTest(CopybookTestSuite.suite());
        suite.addTest(FileTestSuite.suite());
        suite.addTest(FTPTestSuite.suite());
        suite.addTest(HttpTestSuite.suite());
        suite.addTest(JavaTestSuite.suite());
        suite.addTest(MultiformatTestSuite.suite());
        suite.addTest(SoapDocLitTestSuite.suite());
        suite.addTest(SoapV2RpcEncodedTestSuite.suite());
        suite.addTest(SoapV2RpcLiteralTestSuite.suite());
        suite.addTest(XMLMapperTestSuite.suite());
        suite.addTest(IntegrationTestSuite.suite());
        suite.addTest(OutStreamingTestSuite.suite());
        suite.addTest(XSLBizCompTestSuite.suite());
        suite.addTest(SearchPathHelperTestSuite.suite());
        suite.addTest(XAwareStatisticsTestSuite.suite());
        suite.addTest(ResourceTestSuite.suite());
        suite.addTest(StreamingTestSuite.suite());
        suite.addTest(SharedUtilsTestSuite.suite());
        suite.addTest(ResourceTestSuite.suite());
        return suite;
    }
}
