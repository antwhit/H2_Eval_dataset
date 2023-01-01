import java.io.*;

public class MultiPartParser {

    private String bountry = "";

    private byte[] multiPartData = null;

    public MultiPartParser(byte[] data, String bound) {
        this.bountry = bound;
        this.multiPartData = data;
    }

    public byte[] getPart(String name) {
        int startOfPart = 0;
        while (true) {
            startOfPart = findNextPart(startOfPart);
            if (startOfPart > -1) {
                String partName = getName(startOfPart);
                if (partName != null && partName.equals(name)) {
                    return extractPartData(startOfPart);
                }
            } else {
                return null;
            }
        }
    }

    private byte[] extractPartData(int start) {
        String line = "";
        int x = start;
        for (; x < this.multiPartData.length; x++) {
            if (this.multiPartData[x] == '\n') {
                if (line == "") {
                    x++;
                    break;
                } else line = "";
            } else if (this.multiPartData[x] != '\r') {
                line += (char) this.multiPartData[x];
            }
        }
        int partEnd = findPartEnd(x);
        ByteArrayOutputStream partBytes = new ByteArrayOutputStream();
        for (; x < partEnd; x++) {
            if (x < partEnd - 2) {
                partBytes.write(this.multiPartData[x]);
            } else if ((x == (partEnd - 2)) && this.multiPartData[x] != '\r') {
                partBytes.write(this.multiPartData[x]);
            } else if ((x == (partEnd - 1)) && this.multiPartData[x] != '\n') {
                partBytes.write(this.multiPartData[x]);
            }
        }
        return partBytes.toByteArray();
    }

    private String getName(int start) {
        StringBuffer buff = new StringBuffer();
        int lineEnd = readLine(start, buff);
        if (lineEnd > -1) {
            String name = null;
            int indexOf = buff.toString().indexOf("name=\"");
            if (indexOf > -1) {
                name = buff.substring(indexOf + 6, buff.indexOf("\"", indexOf + 6));
            }
            return name;
        } else return null;
    }

    private int findPartEnd(int start) {
        StringBuffer buff = new StringBuffer();
        int lineStart = start;
        int end = start;
        end = readLine(end, buff);
        while (end > -1) {
            String myData = buff.toString();
            if (myData.equals("--" + bountry) || myData.equals("--" + bountry + "--")) return lineStart;
            buff = new StringBuffer();
            lineStart = end;
            end = readLine(end, buff);
        }
        return -1;
    }

    private int findNextPart(int start) {
        StringBuffer buff = new StringBuffer();
        int end = start;
        end = readLine(end, buff);
        while (end > -1) {
            String myData = buff.toString();
            if (myData.equals("--" + bountry)) return end;
            buff = new StringBuffer();
            end = readLine(end, buff);
        }
        return -1;
    }

    private int readLine(int start, StringBuffer buff) {
        if (start >= this.multiPartData.length - 1) return -1;
        int x = start;
        for (; x < this.multiPartData.length; x++) {
            if (this.multiPartData[x] == '\n') {
                return x + 1;
            } else if (this.multiPartData[x] != '\r') {
                buff.append((char) this.multiPartData[x]);
            }
        }
        if (x > this.multiPartData.length - 1) x = this.multiPartData.length - 1;
        return x;
    }
}
