import java.util.ArrayList;

/**
 * @author Christian 'TruBlu' Peper
 */
public class Tutorium implements ITutorium {

    private int soft;

    private int hard;

    private int nummer;

    private String raum;

    private java.util.Date termin;

    private Tutor tutor;

    private ArrayList studenten;

    public int getSoftLimit() {
        return soft;
    }

    public int getHardLimit() {
        return hard;
    }

    /**
   * @return
   */
    public int getNummer() {
        return nummer;
    }

    /**
   * @return Name des Raumes in dem das Tutorium stattfindet.
   */
    public String getRaum() {
        return raum;
    }

    /**
   * @return Termin an dem das Tutorium stattfindet
   */
    public java.util.Date getTermin() {
        return termin;
    }

    /**
   * @return Name des veranstaltenden Tutors
   */
    public Tutor getTutor() {
        return tutor;
    }

    /**
   * @param i
   */
    public void setNummer(int i) {
        nummer = i;
    }

    /**
   * @param string Raum in dem das Tutorium stattfinden soll.
   */
    public void setRaum(String string) {
        raum = string;
    }

    /**
   * @param string
   */
    public void setTermin(java.util.Date date) {
        termin = date;
    }

    /**
   * @param string
   */
    public void setTutor(Tutor myTutor) {
        tutor = myTutor;
    }

    public void removeStudent(int index) {
        studenten.remove(index);
    }

    public void addStudent(Student student) {
        studenten.add(student);
    }

    /**
   * @param i
   */
    public void setHardLimit(int i) {
        hard = i;
    }

    /**
   * @param i
   */
    public void setSoftLimit(int i) {
        soft = i;
    }
}
