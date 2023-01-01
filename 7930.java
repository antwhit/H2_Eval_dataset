import org.exolab.castor.jdo.*;
import org.exolab.castor.util.Logger;
import java.io.PrintWriter;

public class Prueba2 {

    public static void main(String arg[]) {
        Prueba prueba;
        PrintWriter writer;
        writer = new Logger(System.out).setPrefix("prueba");
        try {
            run(writer);
        } catch (Exception e) {
            writer.println(e);
            e.printStackTrace(writer);
        }
    }

    private static void run(PrintWriter writer) throws Exception {
        JDO jdo;
        Database db;
        Autor autor1;
        Direccion direccion1;
        Autor tmp;
        OQLQuery oql;
        QueryResults results;
        jdo = new JDO();
        jdo.setConfiguration("prueba.xml");
        jdo.setDatabaseName("centropublicaciones");
        jdo.setLogWriter(writer);
        jdo.setClassLoader(Prueba.class.getClassLoader());
        db = jdo.getDatabase();
        autor1 = new Autor();
        autor1.setNombre("Lucas");
        autor1.setApellido("Di Pentima");
        autor1.setDocumento("27705159");
        autor1.setProfesion("Estudiante");
        autor1.setTelefono("4593122");
        autor1.setTipoDoc("DNI");
        autor1.setProyecto("LUNIX");
        direccion1 = new Direccion();
        direccion1.setCalle("General Lopez");
        direccion1.setCiudad("Santa Fe");
        direccion1.setDepto("4");
        direccion1.setNro("2953");
        direccion1.setPiso("n/a");
        autor1.addToDireccion(direccion1);
        db.begin();
        oql = db.getOQLQuery("SELECT p FROM Autor p");
        results = oql.execute();
        while (results.hasMore()) {
            System.out.println("Objeto encontrado...");
            tmp = (Autor) results.next();
            System.out.println("Sus datos son:");
            System.out.println("-------------");
            System.out.println("   Nombre: " + tmp.getNombre());
            System.out.println(" Apellido: " + tmp.getApellido());
            System.out.println(" Telefono: " + tmp.getTelefono());
            System.out.println("Documento: " + tmp.getTipoDoc() + " " + tmp.getDocumento());
            System.out.println("");
            System.out.println("Eliminando objeto...");
            db.remove(tmp);
            System.out.println("Listo.");
        }
        db.commit();
        db.begin();
        db.create(autor1);
        db.commit();
        db.close();
    }
}
