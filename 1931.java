import java.util.Hashtable;

/**
 *
 * @author snieradka
 */
public class Line {

    public class Direction {

        String nazwa = "";

        boolean computed = false;

        public short[] stations;

        public short[][] t0;

        public short[][] t1;

        public Hashtable descriptionsDictionary;

        public void compute() {
            if (!computed) {
                for (short i = 1; i < t0[0].length; ++i) if ((t0[0][i] % DB.MAX) != DB.NOT_HOUR) {
                    t0[0][i] = (short) ((t0[0][i - 1] % DB.MAX) + t0[0][i]);
                }
                for (short i = 1; i < t1[0].length; ++i) if ((t1[0][i] % DB.MAX) != DB.NOT_HOUR) {
                    t1[0][i] = (short) ((t1[0][i - 1] % DB.MAX) + t1[0][i]);
                }
                for (short j = 1; j < stations.length - 1; j++) {
                    for (short k = 0; k < t0[j].length; ++k) {
                        if ((t0[j][k] % DB.MAX) != DB.NOT_HOUR) {
                            t0[j][k] = (short) ((t0[j - 1][k] % DB.MAX) + t0[j][k]);
                            if ((t0[j - 1][k] % DB.MAX) == DB.NOT_HOUR) {
                                t0[j][k] -= DB.NOT_HOUR;
                            }
                        }
                    }
                    for (short k = 0; k < t1[j].length; ++k) {
                        if ((t1[j][k] % DB.MAX) != DB.NOT_HOUR) {
                            t1[j][k] = (short) ((t1[j - 1][k] % DB.MAX) + t1[j][k]);
                            if ((t1[j - 1][k] % DB.MAX) == DB.NOT_HOUR) {
                                t1[j][k] -= DB.NOT_HOUR;
                            }
                        }
                    }
                }
                computed = true;
            }
        }
    }

    public Direction[] directions;

    public Line() {
        directions = new Direction[2];
        directions[0] = new Direction();
        directions[1] = new Direction();
    }

    public short[] getTimeTable(int direction, short station, int tx) throws IllegalArgumentException {
        int i = 0;
        short[] out;
        for (; i < directions[direction].stations.length; i++) if (directions[direction].stations[i] == station) {
            break;
        }
        directions[direction].compute();
        if (tx == 0) {
            out = directions[direction].t0[i];
        } else {
            out = directions[direction].t1[i];
        }
        return out;
    }
}
