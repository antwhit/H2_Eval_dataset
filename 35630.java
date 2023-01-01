import java.math.BigInteger;

class CombinationGenerator {

    private int[] a;

    private int[] orderSizes;

    private int nBioRepsTimesTwo;

    private int nBioReps;

    private BigInteger numLeft;

    private BigInteger total;

    private BigInteger halfway;

    public CombinationGenerator(int nBioReps) {
        nBioRepsTimesTwo = nBioReps * 2;
        this.nBioReps = nBioReps;
        if (this.nBioReps > nBioRepsTimesTwo) {
            throw new IllegalArgumentException();
        }
        if (nBioRepsTimesTwo < 1) {
            throw new IllegalArgumentException();
        }
        a = new int[this.nBioReps];
        orderSizes = new int[this.nBioReps];
        BigInteger nFact = getFactorial(this.nBioRepsTimesTwo);
        BigInteger rFact = getFactorial(this.nBioReps);
        total = nFact.divide(rFact.multiply(rFact));
        halfway = total.divide(new BigInteger("2"));
        reset();
        while (hasMore()) {
            CombinationPair p = getNext();
            orderSizes[p.getOrder()]++;
        }
        reset();
    }

    private void reset() {
        for (int i = 0; i < this.nBioReps; i++) {
            a[i] = i;
        }
        numLeft = new BigInteger(total.toString());
    }

    public BigInteger getTotal() {
        return halfway;
    }

    public int getOrderSize(int o) {
        return orderSizes[o];
    }

    public boolean hasMore() {
        return numLeft.compareTo(halfway) == 1;
    }

    private static BigInteger getFactorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = n; i > 1; i--) {
            fact = fact.multiply(new BigInteger(Integer.toString(i)));
        }
        return fact;
    }

    private CombinationPair makeCombinationPair() {
        Combination leftside = new Combination(a);
        Combination rightside = leftside.inverse();
        return new CombinationPair(leftside, rightside);
    }

    public CombinationPair getNext() {
        advance();
        return makeCombinationPair();
    }

    private void advance() {
        if (numLeft.equals(total)) {
            numLeft = numLeft.subtract(BigInteger.ONE);
            return;
        }
        int i = nBioReps - 1;
        while (a[i] == nBioReps + i) {
            i--;
        }
        a[i] = a[i] + 1;
        for (int j = i + 1; j < nBioReps; j++) {
            a[j] = a[i] + j - i;
        }
        numLeft = numLeft.subtract(BigInteger.ONE);
        return;
    }
}
