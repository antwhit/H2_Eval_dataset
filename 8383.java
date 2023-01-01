import java.io.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class BenchmarkFileIO {

    private TransformerFactory transformerFactory = null;

    public void doit() {
        Source xmlSource = new StreamSource("data.xml");
        Source styleSource = new StreamSource("viewsoccergames.xsl");
        try {
            Transformer transformer = transformerFactory.newTransformer(styleSource);
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            StreamResult result = new StreamResult(charArrayWriter);
            transformer.transform(xmlSource, result);
            int x = charArrayWriter.toString().length();
            String s = charArrayWriter.toString();
        } catch (Exception e) {
            System.out.println("FAOO" + e);
        }
    }

    public BenchmarkFileIO() {
        transformerFactory = TransformerFactory.newInstance();
    }

    public static void main(String[] args) {
        Benchmark b = new Benchmark();
        for (int i = 0; i < 1000; i++) {
            b.doit();
        }
    }
}
