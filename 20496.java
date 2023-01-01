public class GaussianMembershipFunction extends MembershipFunction {

    private static final int NUM_STD_DEVS = 3;

    public GaussianMembershipFunction(double mean, double sigma) {
        lowerBound = mean - sigma * NUM_STD_DEVS;
        sampledValues = new double[(int) (sigma * NUM_STD_DEVS * 2 / RESOLUTION) + 1];
        for (int i = 0; i < sampledValues.length; i++) {
            double x = lowerBound + i * RESOLUTION;
            sampledValues[i] = Math.exp(-0.5 * Math.pow((x - mean) / sigma, 2));
        }
    }
}
