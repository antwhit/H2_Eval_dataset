/**
 * Store price preferences.
 */
public class PricePreference {

    private float minPerDay;

    private float maxPerDay;

    public PricePreference() {
        this(50f, 200f);
    }

    public PricePreference(float min, float max) {
        minPerDay = min;
        maxPerDay = max;
    }

    public float getMinPerDay() {
        return minPerDay;
    }

    public float getMaxPerDay() {
        return maxPerDay;
    }
}
