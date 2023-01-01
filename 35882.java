/**
 * @author TruBlu
 */
public class Wunsch {

    private Veranstaltung veranstaltung;

    private int[] wuensche;

    public Wunsch(int[] wuensche) {
        for (int i = 0; i < wuensche.length; i++) {
            this.wuensche[i] = wuensche[i];
        }
    }

    public int givePriority(int index) {
        return wuensche[index];
    }
}
