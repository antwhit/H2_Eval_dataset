import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

class KBSysInfoRes extends HTTPResponse {

    public KBSysInfoRes() throws Exception {
        super();
    }

    public void getResponse(HTTPurl urlData, OutputStream outStream, HashMap<String, String> headers) throws Exception {
        if ("01".equals(urlData.getParameter("action"))) {
            return;
        } else {
            outStream.write(showSysInfo(urlData, headers));
            return;
        }
    }

    private byte[] showSysInfo(HTTPurl urlData, HashMap<String, String> headers) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation di = db.getDOMImplementation();
        Document doc = di.createDocument("", "sys-info", null);
        Element root = doc.getDocumentElement();
        Element item = null;
        item = doc.createElement("info");
        item.setAttribute("name", "Server Version");
        item.setAttribute("units", "");
        item.setAttribute("value", store.getVersion());
        root.appendChild(item);
        item = doc.createElement("info");
        item.setAttribute("name", "Number of Schedules");
        item.setAttribute("units", "");
        int numSch = store.getScheduleCount();
        item.setAttribute("value", new Integer(numSch).toString());
        root.appendChild(item);
        CaptureDeviceList devList = CaptureDeviceList.getInstance();
        item = doc.createElement("info");
        item.setAttribute("name", "Cards in use");
        item.setAttribute("units", "");
        item.setAttribute("value", new Integer(devList.getActiveDeviceCount()).toString());
        root.appendChild(item);
        Date now = new Date();
        int timeToNext = -1;
        ScheduleItem next = store.getNextSchedule();
        if (next != null) {
            timeToNext = (int) ((next.getStart().getTime() - now.getTime()) / (1000 * 60));
            if (timeToNext < 0) timeToNext = 1;
        }
        item = doc.createElement("info");
        item.setAttribute("name", "Time to next");
        item.setAttribute("units", "");
        item.setAttribute("value", new Integer(timeToNext).toString());
        root.appendChild(item);
        XSL transformer = new XSL(doc, "kb-sys-info.xsl", urlData, headers);
        return transformer.doTransform();
    }
}
