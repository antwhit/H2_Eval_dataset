import java.io.*;
import java.util.*;

class PpaWirings {

    class Wiring {

        private String srcName;

        private String srcExit;

        private String dest;

        private String srcType;

        public String getSrcName() {
            return srcName;
        }

        public void setSrcName(String s) {
            srcName = s;
        }

        public String getSrcExit() {
            return srcExit;
        }

        public void setSrcExit(String s) {
            srcExit = s;
        }

        public String getDest() {
            return dest;
        }

        public void setDest(String s) {
            dest = s;
        }

        public void setSrcType(String s) {
            srcType = s;
        }

        public void print(PrintStream file) {
            if (srcName.equals("eth0")) {
                file.println("\tmakeConnection(\"" + srcName + "\", lee::NetInterface::" + srcExit.toUpperCase() + ", \"" + dest + "\");");
            } else if (srcName.equals("eth1")) {
                file.println("\tmakeConnection(\"" + srcName + "\", lee::NetInterface::" + srcExit.toUpperCase() + ", \"" + dest + "\");");
            } else if (srcName.equals("eth2")) {
                file.println("\tmakeConnection(\"" + srcName + "\", lee::NetInterface::" + srcExit.toUpperCase() + ", \"" + dest + "\");");
            } else if (srcName.equals("eth3")) {
                file.println("\tmakeConnection(\"" + srcName + "\", lee::NetInterface::" + srcExit.toUpperCase() + ", \"" + dest + "\");");
            } else {
                file.println("\tmakeConnection(\"" + srcName + "\", userPPF::" + srcType + "::" + srcExit.toUpperCase() + ", \"" + dest + "\");");
            }
        }
    }

    private LinkedList wiringList;

    private HashMap env;

    public PpaWirings() {
        wiringList = new LinkedList();
        env = null;
    }

    public void print(PrintStream file) {
        Iterator wirings = wiringList.iterator();
        int listSize = wiringList.size();
        for (int i = 0; i < listSize; i++) {
            Wiring wiring = (Wiring) wirings.next();
            wiring.print(file);
        }
        file.println();
    }

    public void addWiring(Wiring w) {
        wiringList.addLast(w);
    }

    public void setEnv(HashMap e) {
        env = e;
        Iterator wirings = wiringList.iterator();
        int listSize = wiringList.size();
        for (int i = 0; i < listSize; i++) {
            Wiring wiring = (Wiring) wirings.next();
            wiring.setSrcType((String) env.get(wiring.getSrcName()));
        }
    }
}
