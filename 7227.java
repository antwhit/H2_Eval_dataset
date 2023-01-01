/**
 * Class for the NOTHING_CONNECTS_WITH_ALL_HONORS scoreentry.
 *
 * @author Peter Henschel
 */
public class Scoreentry_NOTHING_CONNECTS_WITH_ALL_HONORS extends Scoreentry {

    /**
     * Create a Scoreentry for a NOTHING_CONNECTS_WITH_ALL_HONORS.
     *
     * This constructor checks the combo array if it build a valid
     * {@code NOTHING_CONNECTS_WITH_ALL_HONORS} score entry. Such an entry is valid
     * if the array contains only 1 element and is done with
     * a nothing-connects combo with 7 honor cards. If the argument or one
     * element is {@code null} a {@link NullPointerException}
     * is thrown. If the combos doesn't match this entry an
     * {@link IllegalArgumentException} exception is thrown.
     *
     * @param combos The combo array.
     * @throws NullPointerException If the argument or one element is null.
     * @throws IllegalArgumentException If the combo doesn't match this entry.
     */
    public Scoreentry_NOTHING_CONNECTS_WITH_ALL_HONORS(Combo[] combos) {
        super(combos);
        if (combos.length != 1) {
            throw new IllegalArgumentException("entry can only build with 1 combos");
        }
        if (combos[0].getType() != Combo.TYPE_NOTHING_CONNECTS) {
            throw new IllegalArgumentException("combo must be a nothing-connects.");
        }
        int count_honor = 0;
        Card[] cards = combos[0].getCards();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].isHonor()) {
                count_honor++;
            }
        }
        if (7 != count_honor) {
            throw new IllegalArgumentException("not enought honor cards.");
        }
        this.credits = 8;
    }
}
