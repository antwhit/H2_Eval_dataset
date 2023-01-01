import java.io.Serializable;

public class HTMLFormatter implements Formatter {

    @Override
    public byte[] getFormatedContent(String content2Format) {
        content2Format = "<html><head></head><body><p>" + content2Format + "</p></body> </html>";
        return content2Format.getBytes();
    }
}
