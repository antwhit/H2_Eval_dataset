public class PepcCutoffInfo {

    final int GstatisticCutoffIndex;

    final int PvalueCutoffIndex;

    public final String text;

    public final String smalltext;

    PepcCutoffInfo(Experiment e, int G, int P) {
        GstatisticCutoffIndex = G;
        PvalueCutoffIndex = P;
        Double GStatistic = e.getGStatisticCutoffByIndex(GstatisticCutoffIndex);
        Double PValue = e.getPValueCutoffByIndex(PvalueCutoffIndex);
        double TP = e.getTPbyIndex(GstatisticCutoffIndex, PvalueCutoffIndex);
        double FDR = e.getFDRbyIndex(GstatisticCutoffIndex, PvalueCutoffIndex);
        Double count = e.getNullhypothesisFilterPassCount(G, P);
        double FDRCutoff = 100 * e.getFDRCutoff();
        text = "Found " + count.toString() + " proteins, with " + String.format("%.3f", FDR * count.doubleValue()) + " expected false positives ( " + String.format("%.3f", 100 * FDR) + "% FDR ), at G-statistic >=" + GStatistic.toString() + " and p-value<=" + PValue.toString();
        smalltext = count.toString() + " proteins, " + String.format("%.2f", FDR * count.doubleValue()) + " expected false(" + String.format("%.3f", 100 * FDR) + "% FDR). \n[ Brighter boxes = more true positives, white boxes = FDR > " + String.format("%.2f", FDRCutoff) + "%]";
    }
}
