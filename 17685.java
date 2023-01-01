import com.liferay.portal.model.LayoutModel;
import com.liferay.portal.model.LayoutReference;
import com.liferay.portal.service.http.LayoutServiceSoap;
import com.liferay.portal.service.http.LayoutServiceSoapServiceLocator;
import java.net.URL;
import java.util.Locale;

/**
 * <a href="Test.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class Test {

    public static void main(String[] args) {
        try {
            Test test = new Test();
            test.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test() throws Exception {
        LayoutServiceSoapServiceLocator locator = new LayoutServiceSoapServiceLocator();
        String url = "http://localhost:8080/tunnel/axis/Portal_LayoutService";
        if (false) {
            String userId = "liferay.com.1";
            String password = "test";
            url = "http://" + userId + ":" + password + "@localhost:8080/tunnel/secure/axis/Portal_LayoutService";
        }
        LayoutServiceSoap soap = locator.getPortal_LayoutService(new URL(url));
        LayoutReference[] layoutReferences = soap.getLayoutReferences("liferay.com", "56", "article-id", "PRODUCTS-LICENSING");
        for (int i = 0; i < layoutReferences.length; i++) {
            LayoutModel layoutModel = layoutReferences[i].getLayoutModel();
            String portletId = layoutReferences[i].getPortletId();
            String layoutName = soap.getLayoutName(layoutModel.getLayoutId(), layoutModel.getOwnerId(), Locale.getDefault().toString());
            System.out.println("Layout id " + layoutModel.getLayoutId());
            System.out.println("Layout name " + layoutName);
            System.out.println("Portlet id " + portletId);
        }
    }
}
