import java.io.File;
import java.io.IOException;
import com.google.code.javascribd.connection.ScribdClient;
import com.google.code.javascribd.connection.ScribdConnectionException;
import com.google.code.javascribd.connection.StreamableData;
import com.google.code.javascribd.docs.Upload;
import com.google.code.javascribd.docs.UploadResponse;
import com.google.code.javascribd.type.Access;
import com.google.code.javascribd.type.ApiKey;
import com.google.code.javascribd.type.FileData;

public class UploadSnippet {

    public static void main(String[] args) throws ScribdConnectionException, IOException {
        ScribdClient client = new ScribdClient();
        ApiKey apiKey = new ApiKey("scripd_api_key");
        File file = new File("example.pdf");
        StreamableData uploadData = new FileData(file);
        Upload upload = new Upload(apiKey, uploadData);
        upload.setDocType("pdf");
        upload.setAccess(Access.PRIVATE);
        UploadResponse response = client.execute(upload);
        System.out.printf("document_id: %d\n", response.getDocId().intValue());
        System.out.printf("document_id: %s\n", response.getAccessKey());
    }
}
