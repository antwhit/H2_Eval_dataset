import com.dalsemi.system.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import com.dalsemi.tininet.http.HTTPWorker;
import com.dalsemi.tininet.http.PostElement;
import com.dalsemi.tininet.http.PostScript;

public class Active implements PostScript {

    public void handlePost(Vector arg0, OutputStream arg1, HTTPWorker arg2) throws IOException {
        PostElement pe;
        for (int i = 0; i < arg0.size(); i++) {
            pe = (PostElement) arg0.elementAt(i);
            if (arg0.size() == 1) new BitPort(BitPort.Port5Bit3).clear(); else new BitPort(BitPort.Port5Bit3).set();
            if (pe.field.equals("rc")) {
                int val = Integer.parseInt(pe.value);
                if (val == 0) Timer2.stopT2(); else {
                    int n1 = (val * 255) / 100;
                    int n2 = 255 - n1;
                    Timer2.startpwmT2((n2 << 8) + n1);
                }
            }
        }
    }
}
