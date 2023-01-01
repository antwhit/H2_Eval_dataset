import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ListaDeAlarmas extends LinkedList<Alarma> implements java.io.Serializable {

    public Alarma getProximaAlarma() {
        limpiarAlarmasVencidas();
        Iterator<Alarma> it = this.iterator();
        Alarma al = null;
        Alarma al2;
        while (it.hasNext()) {
            al2 = it.next();
            if (al != null) {
                if (al2.getFecha().before(al.getFecha())) {
                    al = al2;
                }
            } else {
                al = al2;
            }
        }
        ;
        return al;
    }

    public Alarma getPrimerAlarma() {
        Iterator<Alarma> it = this.iterator();
        Alarma al = null;
        Alarma al2;
        while (it.hasNext()) {
            al2 = it.next();
            if (al != null) {
                if (al2.getFecha().before(al.getFecha())) {
                    al = al2;
                }
            } else {
                al = al2;
            }
        }
        ;
        return al;
    }

    private void limpiarAlarmasVencidas() {
        Iterator<Alarma> it = this.iterator();
        Alarma al = null;
        System.out.println(this.size());
        Date FechaActual = new Date();
        boolean huboCambios = false;
        while (it.hasNext()) {
            al = it.next();
            if (al.getFecha().before(FechaActual)) {
                it.remove();
                huboCambios = true;
            }
        }
        AdministradorDeArchivos FileAdmin = new AdministradorDeArchivos();
        if (huboCambios) {
            FileAdmin = new AdministradorDeArchivos();
            FileAdmin.AlmacenarLista(this);
        }
    }
}
