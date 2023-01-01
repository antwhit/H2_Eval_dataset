import game.rapdiminer.proces.RapidMinerProces;
import game.rapdiminer.proces.RapidMinerProcesOutput;
import game.rapidminer.proces.exception.RapidMinerProcessException;
import java.io.File;
import com.rapidminer.Process;
import com.rapidminer.operator.OperatorException;

public class Test {

    public static void main(String[] args) {
        RapidMinerProces rmp = new RapidMinerProces();
        try {
            RapidMinerProcesOutput rmpo = rmp.process(new File("/home/vlada/validace.xml"));
        } catch (RapidMinerProcessException e) {
            e.printStackTrace();
        }
    }
}
