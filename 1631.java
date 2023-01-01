class ProteinPermutation {

    private final double pValue;

    private final double GStatistic;

    ProteinPermutation(double gt, double pv) {
        this.GStatistic = gt;
        this.pValue = pv;
    }

    double getGStatistic() {
        return GStatistic;
    }

    public double getPValue() {
        return pValue;
    }
}
