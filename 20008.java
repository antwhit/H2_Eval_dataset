import ag.ion.bion.officelayer.application.IOfficeApplication;
import ag.ion.bion.officelayer.application.OfficeApplicationRuntime;
import ag.ion.bion.officelayer.document.DocumentDescriptor;
import ag.ion.bion.officelayer.document.IDocument;
import ag.ion.bion.officelayer.document.IDocumentService;
import ag.ion.bion.officelayer.text.IText;
import ag.ion.bion.officelayer.text.ITextDocument;
import ag.ion.bion.officelayer.text.ITextTable;
import java.util.HashMap;

/**
 * This code snippet creates a table and makes the first three line table header style.
 * 
 * @author Markus Kr�ger
 * @version $Revision: 10398 $
 */
public class Snippet16 {

    private static final String OPEN_OFFICE_ORG_PATH = "C:\\Programme\\OpenOffice.org 2.1";

    public static void main(String[] args) {
        try {
            HashMap configuration = new HashMap();
            configuration.put(IOfficeApplication.APPLICATION_HOME_KEY, OPEN_OFFICE_ORG_PATH);
            configuration.put(IOfficeApplication.APPLICATION_TYPE_KEY, IOfficeApplication.LOCAL_APPLICATION);
            final IOfficeApplication officeAplication = OfficeApplicationRuntime.getApplication(configuration);
            officeAplication.setConfiguration(configuration);
            officeAplication.activate();
            IDocumentService documentService = officeAplication.getDocumentService();
            ITextDocument textDocument = (ITextDocument) documentService.constructNewDocument(IDocument.WRITER, DocumentDescriptor.DEFAULT);
            int rows = 15;
            int cols = 5;
            ITextTable textTable = textDocument.getTextTableService().constructTextTable(rows, cols);
            textDocument.getTextService().getTextContentService().insertTextContent(textTable);
            textTable.setHeaderRows(3);
            for (int i = 0, n = rows; i < n; i++) {
                for (int j = 0, m = cols; j < m; j++) {
                    IText cellText = textTable.getCell(j, i).getTextService().getText();
                    cellText.setText("Line " + (i + 1) + " Col " + (j + 1));
                }
            }
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
    }
}
