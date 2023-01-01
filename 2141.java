import ag.ion.bion.officelayer.application.IOfficeApplication;
import ag.ion.bion.officelayer.application.OfficeApplicationException;
import ag.ion.bion.officelayer.application.OfficeApplicationRuntime;
import ag.ion.bion.officelayer.document.DocumentDescriptor;
import ag.ion.bion.officelayer.document.IDocument;
import ag.ion.bion.officelayer.document.IDocumentService;
import ag.ion.bion.officelayer.text.ITextDocument;
import ag.ion.bion.officelayer.text.ITextTable;
import ag.ion.bion.officelayer.text.ITextTableCell;
import ag.ion.bion.officelayer.text.ITextTableColumn;
import ag.ion.bion.officelayer.text.ITextTableRow;
import ag.ion.bion.officelayer.text.TextException;
import ag.ion.noa.NOAException;
import java.util.HashMap;

/**
 * In here we will do some more stuff with tables especially with formulas.
 *  
 * @author Sebastian R�sgen
 * @version $Revision: 10398 $
 * @date 17.03.2006
 */
public class Snippet11 {

    private static final String OPEN_OFFICE_ORG_PATH = "/usr/lib/ooo-2.0";

    public static void main(String[] args) {
        DocumentDescriptor documentDescriptor = new DocumentDescriptor();
        documentDescriptor.setHidden(false);
        HashMap configuration = new HashMap();
        configuration.put(IOfficeApplication.APPLICATION_HOME_KEY, OPEN_OFFICE_ORG_PATH);
        configuration.put(IOfficeApplication.APPLICATION_TYPE_KEY, IOfficeApplication.LOCAL_APPLICATION);
        try {
            IOfficeApplication officeAplication = OfficeApplicationRuntime.getApplication(configuration);
            officeAplication.activate();
            IDocumentService documentService = officeAplication.getDocumentService();
            IDocument document = documentService.constructNewDocument("TestDocument", documentDescriptor);
            ITextDocument textDocument = (ITextDocument) document;
            textDocument.addCloseListener(new SnippetDocumentCloseListener(officeAplication));
            constructAndFillTables(textDocument);
        } catch (OfficeApplicationException exception) {
            exception.printStackTrace();
        } catch (NOAException exception) {
            exception.printStackTrace();
        }
    }

    /**
	 * A table example in which we will create a table and the let calculate the
	 * sum of each column.
	 * 
	 * @param textDocument the document to place the table in
	 *
	 * @author Sebastian R�sgen
	 * @date 17.03.2006
	 */
    public static void constructAndFillTables(ITextDocument textDocument) {
        try {
            ITextTable textTable = textDocument.getTextTableService().constructTextTable(3, 5);
            textDocument.getTextService().getTextContentService().insertTextContent(textTable);
            int tableRowCount = textTable.getRowCount();
            int tableColumnCount = textTable.getColumnCount();
            int counter = 0;
            for (int i = 0; i < tableRowCount; i++) {
                for (int j = 0; j < tableColumnCount; j++) {
                    textTable.getCell(j, i).setValue(++counter);
                }
            }
            textTable.addRow(1);
            ITextTableRow row = textTable.getRow(textTable.getRowCount() - 1);
            ITextTableColumn[] columns = textTable.getColumns();
            for (int i = 0; i < columns.length; i++) {
                ITextTableCell[][] cells = columns[i].getCellRange().getCells();
                StringBuffer cellNames = new StringBuffer();
                for (int x = 0; x < cells.length - 1; x++) {
                    cellNames.append("<" + cells[x][0].getName().getName() + ">");
                    if (x < cells.length - 2) {
                        cellNames.append("+");
                    }
                }
                ITextTableCell formulaCell = row.getCells()[i];
                formulaCell.getFormulaService().setFormula(cellNames.toString());
            }
        } catch (TextException exception) {
            exception.printStackTrace();
        }
    }
}
