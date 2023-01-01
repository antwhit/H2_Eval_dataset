import file.io.FileWriter;

public class w {

    public static void main(String[] args) {
        FileWriter f = new FileWriter("foo.txt");
        PrintWriter p = new PrintWriter(f);
        p.println("dmiles, myarray and zealot were here!");
        p.close();
    }
}
