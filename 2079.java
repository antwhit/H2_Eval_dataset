import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = new java.util.Properties();
        try {
            String sConfigFile = "all.properties";
            InputStream in = Main.class.getClassLoader().getResourceAsStream(sConfigFile);
            properties.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(".____                 ____.      .__  .__");
        System.out.println("|    |   _____       |    | ____ |  | |  | _____  ");
        System.out.println("|    |   \\__  \\      |    |/  _ \\|  | |  | \\__  \\  ");
        System.out.println("|    |___ / __ \\_/\\__|    (  <_> )  |_|  |__/ __ \\_");
        System.out.println("|_______ (____  /\\________|\\____/|____/____(____  /");
        System.out.println("        \\/    \\/                                \\/");
        System.out.println("version " + properties.getProperty("version"));
        System.out.println("http://lajolla.sf.net");
        System.out.println("\nBling Bling - cool to see you around.\n\n" + "LaJolla is a software package that uses an efficient technique\n" + "to generate alignments for Proteins and for RNAs (based on suite codes). \n\n" + "Try the follwing: \n\n" + "1. alignment of protein structures: \n" + "   type in: java -cp lajolla.jar PRO -h\n" + "2. alignment of RNAs: \n" + "   type in: java -cp lajolla.jar RNA -h\n" + "\n" + "\n" + "More infos: http://lajolla.sf.net");
    }
}
