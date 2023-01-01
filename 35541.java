import java.io.*;
import ogdl.reference.*;
import ogdl.reference.OgdlPullParser;
import ogdl.reference.OgdlSerializer;
import ogdl.*;

public class SerializerExample1 {

    public static void main(String[] args) {
        try {
            OgdlPullParser parser = new OgdlPullParser();
            parser.setInput(new FileReader(args[0]));
            OgdlSerializer serializer = new OgdlSerializer();
            serializer.setOutput(System.out, null);
            for (int type = 0; type != OgdlParserToken.EOF; type = parser.next()) {
                switch(type) {
                    case OgdlParserToken.START_GRAPH:
                        serializer.startGraph();
                        break;
                    case OgdlParserToken.TEXT:
                        String text = parser.getText();
                        serializer.scalar(text);
                        serializer.flush();
                        break;
                    case OgdlParserToken.END_GRAPH:
                        serializer.endGraph();
                        break;
                }
            }
            serializer.flush();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
