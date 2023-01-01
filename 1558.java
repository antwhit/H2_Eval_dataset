/**
 * Class holding all data received from a mote.
 */
class Node {

    static final int INCREMENT = 100 * Constants.NREADINGS;

    static final int MAX_SIZE = 100 * INCREMENT;

    int id;

    int[] data;

    int dataStart, dataEnd;

    Node(int _id) {
        id = _id;
    }

    private void setEnd(int newDataIndex, int newEnd) {
        if (newDataIndex < dataStart || data == null) {
            dataStart = newDataIndex;
            data = new int[INCREMENT];
        }
        if (newEnd > dataStart + data.length) {
            if (data.length < MAX_SIZE) {
                int newLength = (newEnd - dataStart + INCREMENT - 1) / INCREMENT * INCREMENT;
                if (newLength >= MAX_SIZE) newLength = MAX_SIZE;
                int[] newData = new int[newLength];
                System.arraycopy(data, 0, newData, 0, data.length);
                data = newData;
            }
            if (newEnd > dataStart + data.length) {
                int newStart = newDataIndex + INCREMENT - data.length;
                if (dataStart + data.length > newStart) System.arraycopy(data, newStart - dataStart, data, 0, data.length - (newStart - dataStart));
                dataStart = newStart;
            }
        }
        for (int i = dataEnd < dataStart ? dataStart : dataEnd; i < newDataIndex; i++) data[i - dataStart] = -1;
        dataEnd = newEnd;
    }

    void update(int messageId, int readings[]) {
        int start = messageId * Constants.NREADINGS;
        setEnd(start, start + Constants.NREADINGS);
        for (int i = 0; i < readings.length; i++) data[start - dataStart + i] = readings[i];
    }

    int getData(int x) {
        if (x < dataStart || x >= dataEnd) return -1; else return data[x - dataStart];
    }

    int maxX() {
        return dataEnd - 1;
    }
}
