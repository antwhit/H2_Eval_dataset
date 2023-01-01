import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import org.joone.helpers.factory.JooneTools;
import org.joone.io.FileInputSynapse;
import org.joone.net.NeuralNet;
import org.joone.util.NormalizerPlugIn;

/**
 * This Misuse detector detects 5 attacks
 * 
 * ABOUT THE TRAING DATA
 *  - 10 lines of Optimized_BufferOverflow
 *  - 10 lines of Optimized_GuessPassword
 *  - 10 lines of Optimized_Neptune
 *  - 10 lines of Optimized_Normal
 *  - 10 lines of Optimized_PortSweep
 *  - 10 lines of Optimized_Satan
 * 
 * @author Nicholas Pike
 *
 */
public class MultipleMisuse {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        int trainingRows = 60;
        FileInputSynapse fileIn = new FileInputSynapse();
        fileIn.setInputFile(new File("data/JOONE-MULTIPLEMISUSE.DATA"));
        fileIn.setAdvancedColumnSelector("1-35");
        NormalizerPlugIn normIn = new NormalizerPlugIn();
        normIn.setAdvancedSerieSelector("1-34");
        normIn.setMin(-1);
        normIn.setMax(1);
        fileIn.addPlugIn(normIn);
        NormalizerPlugIn normDes = new NormalizerPlugIn();
        normDes.setAdvancedSerieSelector("35");
        fileIn.addPlugIn(normDes);
        double[][] inputTrain = JooneTools.getDataFromStream(fileIn, 1, trainingRows, 1, 34);
        double[][] desiredTrain = JooneTools.getDataFromStream(fileIn, 1, trainingRows, 35, 35);
        double[][] inputTest = JooneTools.getDataFromStream(fileIn, trainingRows + 1, 127, 1, 34);
        double[][] desiredTest = JooneTools.getDataFromStream(fileIn, trainingRows + 1, 127, 35, 35);
        NeuralNet nnet = JooneTools.create_standard(new int[] { 34, 15, 5 }, JooneTools.LOGISTIC);
        double rmse = JooneTools.train(nnet, inputTrain, desiredTrain, 5000, 0.01, 0, null, false);
        System.out.println("Training complete.");
        double[][] out = JooneTools.compare(nnet, inputTest, desiredTest);
        System.out.println("Comparion of the last " + out.length + " rows:");
        int cols = out[0].length / 2;
        for (int i = 0; i < out.length; ++i) {
            System.out.print("\nOutput: ");
            for (int x = 0; x < cols; ++x) {
                System.out.print(out[i][x] + " ");
            }
            System.out.print("\tTarget: ");
            for (int x = cols; x < cols * 2; ++x) {
                System.out.print(out[i][x] + " ");
            }
        }
    }
}
