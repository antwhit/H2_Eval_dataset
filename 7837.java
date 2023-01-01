import java.net.*;

class RequestListener extends Thread {

    private ServerSocket serverSocket;

    private String name;

    public RequestListener(int port, String name) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.name = name;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        this.listen();
    }

    private void listen() {
        Socket clientSocket;
        try {
            while (true) {
                System.out.println(this.name + " - Listening for client connection.");
                clientSocket = this.serverSocket.accept();
                System.out.println(this.name + "client connection detected. Creating connection to client socket");
                switch(this.serverSocket.getLocalPort()) {
                    case 40070:
                        new POPHandler(clientSocket).start();
                        break;
                    case 40071:
                        new SMTPHandler(clientSocket).start();
                        break;
                    default:
                        throw new Exception("RequestListenerException: Request protocol type not " + "known for given port " + this.serverSocket.getLocalPort());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
