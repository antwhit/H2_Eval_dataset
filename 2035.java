import ipc.java.*;

public class module2 extends module {

    private static class msg1Handler implements IPC.HANDLER_TYPE {

        msg1Handler(String theClientData) {
            clientData = theClientData;
        }

        public void handle(IPC.MSG_INSTANCE msgRef, Object callData) {
            System.out.println("msg1Handler: Receiving " + IPC.msgInstanceName(msgRef) + " (" + callData + ") [" + clientData + "]");
        }

        String clientData;
    }

    private static class queryHandler implements IPC.HANDLER_TYPE {

        queryHandler(String theClientData) {
            clientData = theClientData;
        }

        public void handle(IPC.MSG_INSTANCE msgRef, Object callData) {
            System.out.println("queryHandler: Receiving " + IPC.msgInstanceName(msgRef) + " [" + clientData + "]");
            System.out.println(callData.toString());
            String str1 = "Hello, world";
            System.out.println("\n  IPC.publishData(\"" + MSG2 + "\", \"" + str1 + "\")");
            IPC.publishData(MSG2, str1);
            T2 t2 = new T2();
            t2.str1 = str1;
            t2.t1 = new T1[1];
            t2.t1[0] = (T1) callData;
            t2.count = 1;
            t2.status = ReceiveVal;
            System.out.println("\n  IPC.respondData(" + msgRef + ", \"" + RESPONSE1 + "\", " + t2 + ")");
            IPC.respondData(msgRef, RESPONSE1, t2);
        }

        String clientData;
    }

    private static class stdinHnd implements IPC.FD_HANDLER_TYPE {

        stdinHnd(String theClientData) {
            clientData = theClientData;
        }

        public void handle(int fd) {
            try {
                int in = System.in.read();
                if (in == 'q' || in == 'Q') {
                    IPC.disconnect();
                    System.exit(-1);
                } else {
                    System.out.println("stdinHnd [" + clientData + "]: Received " + (char) in);
                }
                while (System.in.available() > 0) System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String clientData;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("\nIPC.connect(\"" + MODULE2_NAME + "\")");
        IPC.connect(MODULE2_NAME);
        System.out.println("\nIPC.defineMsg(\"" + MSG2 + "\", \"" + MSG2_FORMAT + "\")");
        IPC.defineMsg(MSG2, MSG2_FORMAT);
        System.out.println("\nIPC.defineMsg(\"" + RESPONSE1 + "\", \"" + RESPONSE1_FORMAT + "\")");
        IPC.defineMsg(RESPONSE1, RESPONSE1_FORMAT);
        System.out.println("\nIPC.subscribeData(\"" + MSG1 + "\", new msg1Handler(\"" + MODULE2_NAME + "\"), int.class)");
        IPC.subscribeData(MSG1, new msg1Handler(MODULE2_NAME), int.class);
        System.out.println("\nIPC.subscribeData(\"" + QUERY1 + "\", new queryHandler(\"" + MODULE2_NAME + "\"), T1.class)");
        IPC.subscribeData(QUERY1, new queryHandler(MODULE2_NAME), T1.class);
        System.out.println("\nIPC_subscribeFD(0, new stdinHnd(\"" + MODULE1_NAME + "\"))");
        IPC.subscribeFD(0, new stdinHnd(MODULE1_NAME));
        System.out.println("\nType 'q' to quit");
        IPC.dispatch();
        IPC.disconnect();
    }
}
