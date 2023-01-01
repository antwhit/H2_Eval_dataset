import org.jgap.*;
import org.jgap.gp.*;
import org.jgap.gp.impl.*;

@SuppressWarnings("serial")
public class Left extends AvolveCommands implements IMutateable {

    public Left(final GPConfiguration a_conf) throws InvalidConfigurationException {
        super(a_conf);
    }

    public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
        Right mutant = new Right(getGPConfiguration());
        return mutant;
    }

    public void execute_void(ProgramChromosome a_chrom, int a_n, Object[] a_args) {
        Map map = getMap(a_chrom);
        map.turnLeft();
    }

    public String toString() {
        return "left";
    }
}
