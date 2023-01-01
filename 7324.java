import com.izforge.izpress.*;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.tidy.Tidy;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Property;

/**
 * Task to compress html size.
 *
 * @author <a href="mailto:barozzi@nicolaken.com">Nicola Ken Barozzi</a>
 * @created 14 January 2002
 */
public class IZPressHtmlCompressTask extends org.apache.tools.ant.Task {

    private String src;

    private String dest;

    private Compressor compressor;

    private CompressorConfig conf;

    private String wipeComments = "true";

    private String wipeBorders = "true";

    private String wipeReturns = "true";

    private String wipeSpaces = "true";

    PrintWriter pw;

    /**
   * Constructor.
   */
    public IZPressHtmlCompressTask() {
        super();
    }

    /**
   * Initializes the task.
   */
    public void init() {
        super.init();
        conf = new CompressorConfig(false, false, true, true);
    }

    /**
   * Run the task.
   * @exception org.apache.tools.ant.BuildException The exception raised during task execution.
   */
    public void execute() throws org.apache.tools.ant.BuildException {
        try {
            FileInputStream in = new FileInputStream(src);
            FileOutputStream out = new FileOutputStream(dest);
            compressor = new Compressor(in, out, conf);
            compressor.compress();
            out.flush();
            in.close();
            out.close();
        } catch (IOException ioe) {
            throw new BuildException(ioe);
        }
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setWipeComments(String wipeComments) {
        this.wipeComments = wipeComments;
    }

    public void setWipeBorders(String wipeBorders) {
        this.wipeBorders = wipeBorders;
    }

    public void setWipeReturns(String wipeReturns) {
        this.wipeReturns = wipeReturns;
    }

    public void setWipeSpaces(String wipeSpaces) {
        this.wipeSpaces = wipeSpaces;
    }
}
