import ipc.java.*;

public class module3 extends module {

    private static class msg1Handler implements IPC.HANDLER_TYPE {

        msg1Handler(String theClientData) {
            clientData = theClientData;
        }

        public void handle(IPC.MSG_INSTANCE msgRef, Object callData) {
            System.out.println("msg1Handler: Receiving " + IPC.msgInstanceName(msgRef) + " (" + callData + ") [" + clientData + "]");
        }

        String clientData;
    }

    private static class msg2Handler implements IPC.HANDLER_TYPE {

        msg2Handler(String theClientData) {
            clientData = theClientData;
        }

        public void handle(IPC.MSG_INSTANCE msgRef, Object callData) {
            System.out.println("msg2Handler: Receiving " + IPC.msgInstanceName(msgRef) + " (\"" + callData + "\") [" + clientData + "]");
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
        System.out.println("\nIPC.connect(\"" + MODULE3_NAME + "\")");
        IPC.connect(MODULE3_NAME);
        System.out.println("\nIPC.subscribeData(\"" + MSG1 + "\", new msg1Handler(\"" + MODULE3_NAME + "\"), int.class)");
        IPC.subscribeData(MSG1, new msg1Handler(MODULE3_NAME), int.class);
        System.out.println("\nIPC.subscribeData(\"" + MSG2 + "\", new msg2Handler(\"" + MODULE3_NAME + "\"), String.class)");
        IPC.subscribeData(MSG2, new msg2Handler(MODULE3_NAME), String.class);
        System.out.println("\nIPC_subscribeFD(0, new stdinHnd(\"" + MODULE3_NAME + "\"))");
        IPC.subscribeFD(0, new stdinHnd(MODULE3_NAME));
        System.out.println("\nType 'q' to quit");
        IPC.dispatch();
        IPC.disconnect();
    }
}
