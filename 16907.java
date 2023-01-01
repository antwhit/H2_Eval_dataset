import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemFile;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.io.IChemObjectReader;
import org.openscience.cdk.io.ReaderFactory;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.descriptors.molecular.RuleOfFiveDescriptor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.ChemFileManipulator;

public class Stefanbeispiel {

    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream(args[0]);
            IChemObjectReader cor = new ReaderFactory().createReader(in);
            IChemFile content = (IChemFile) cor.read(DefaultChemObjectBuilder.getInstance().newChemFile());
            System.out.println("Read the file");
            List containers = ChemFileManipulator.getAllAtomContainers(content);
            System.out.println("Got " + containers.size() + " atom containers");
            SmilesGenerator sg = new SmilesGenerator();
            for (int i = 0; i < containers.size(); i++) {
                IAtomContainer container = (IAtomContainer) containers.get(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CDKException e) {
            e.printStackTrace();
        }
    }
}
