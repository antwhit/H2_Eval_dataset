import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ThreadLettore extends Thread {

    Indirizzi indirizzi;

    Manifestazioni eventi;

    public ThreadLettore(Manifestazioni ev, Indirizzi url) {
        eventi = ev;
        indirizzi = url;
    }

    public void run() {
        String url;
        URL u;
        HttpURLConnection huc;
        String pagina = "", linea;
        String[] urlAndName;
        boolean errore = true;
        System.out.println(getName() + " inizia a leggere");
        while ((url = indirizzi.getIndirizzo()) != null) {
            urlAndName = url.split("#");
            try {
                u = new URL(urlAndName[0]);
                while (errore) {
                    try {
                        huc = (HttpURLConnection) u.openConnection();
                        if (huc.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            InputStreamReader in = new InputStreamReader(huc.getInputStream());
                            BufferedReader input = new BufferedReader(in);
                            while ((linea = input.readLine()) != null) pagina += linea;
                            System.out.println(getName() + " parte a Parsare, " + urlAndName[1]);
                            MySpaceParser parser = new MySpaceParser(urlAndName[1], pagina);
                            System.out.println(getName() + " ha finito di parsare, " + urlAndName[1]);
                            parser.parseProfileDiv();
                            eventi.addEventi(parser.getEventsListFiltered());
                        }
                        errore = false;
                        huc.disconnect();
                    } catch (IOException e) {
                        System.out.println("Errore Connessione");
                        System.out.println("Nuovo tentativo in corso...");
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                System.err.println("URL non corretto");
                e.printStackTrace();
            }
            System.out.println(getName() + " ha finito di leggere");
            synchronized (eventi) {
                eventi.notify();
            }
            ;
        }
    }
}
