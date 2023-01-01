import java.io.*;
import java.net.*;

public class Client extends Thread {

    private String fpath;

    private String fmode;

    private static int numClients = 0;

    private int id;

    private int numOperations = 10;

    private class IllegalModeException extends Exception {

        private IllegalModeException() {
        }
    }

    private class ServerErrorException extends Exception {

        private ServerErrorException() {
        }
    }

    /**
	 * Crea un cliente.
	 *
	 * @param path el path hacia el fichero del cual se hará una petición
	 * @param mode indica si es una petición de lectura o escritura
	 */
    public Client(String path, String mode) {
        fpath = path;
        fmode = mode;
        id = numClients++;
        Log.println("creado cliente que hará una " + "petición en modo " + mode + " por el fichero " + path, Log.INIT);
    }

    public String toString() {
        return "(Client" + id + ", " + fpath + ", " + fmode + ")";
    }

    /**
	 * Detecta si la respuesta recibida del servidor es un error y en caso
	 * afirmativo lanza una excepción.
	 */
    private void parseAnswer(String answer) throws ServerErrorException {
        Log.println("Respuesta recibida: " + answer, Log.CONNECTION);
        String[] s = answer.split(":");
        if (s[0].equals("ERROR")) {
            throw new ServerErrorException();
        }
    }

    /**
	 *  Se conecta con el servidor y realiza una petición de lectura o escritura.
	 */
    public void run() {
        Socket socket;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            socket = new Socket("localhost", 4242);
            Log.println("conectado al servidor, " + socket, Log.CONNECTION);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Log.println("Enviando petición para el fichero " + fpath, Log.CONNECTION);
            out.println(fpath);
            parseAnswer(in.readLine());
            Log.println("Enviando petición para abrir el fichero " + fpath + " en modo " + fmode, Log.CONNECTION);
            out.println(fmode);
            parseAnswer(in.readLine());
            String input;
            int i = 0;
            if (fmode.equals("r")) {
                while (i < numOperations && !Main.acabar) {
                    out.println("READ:" + i);
                    parseAnswer(in.readLine());
                    i++;
                }
            } else {
                while (i < numOperations && !Main.acabar) {
                    String data = "DATA" + i;
                    out.println("WRITE:" + i + ":" + data);
                    parseAnswer(in.readLine());
                    i++;
                }
            }
            Log.println("Enviando petición para cerrar el fichero", Log.CONNECTION);
            out.println("CLOSE");
            parseAnswer(in.readLine());
            out.close();
            in.close();
            socket.close();
            Log.println("socket " + socket + " cerrado", Log.CONNECTION);
            if (Main.acabar) {
                System.out.println("Cliente terminando ...");
            }
        } catch (UnknownHostException e) {
            Log.println("Exception: " + e);
        } catch (IOException e) {
            Log.println("Exception: " + e);
        } catch (ServerErrorException e) {
            Log.println("Error en el servidor");
        }
    }
}
