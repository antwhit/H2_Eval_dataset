import computational.geometry.segmentintersection.*;
import computational.geometry.*;
import computational.*;

public class SweepingLineAnySegmentIntersectionLogManager extends EventLogManager implements CGHistory {

    private String[] columnNames = { "#", "Event Type", "Event Queue", "Segment Tree", "Test" };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return getEvents().size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        switch(col) {
            case 0:
                return new Integer(row + 1);
            case 1:
                return ((SweepingLineEvent) getEvents().get(row)).getType();
            case 2:
                return ((SweepingLineEvent) getEvents().get(row)).getEventQueue();
            case 3:
                return ((SweepingLineEvent) getEvents().get(row)).getSegmentTree();
            case 4:
                return ((SweepingLineEvent) getEvents().get(row)).getTest();
            default:
                return null;
        }
    }
}
