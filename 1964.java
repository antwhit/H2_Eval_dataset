import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import org.openscience.cdk.Atom;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.fingerprint.ExtendedFingerprinter;
import org.openscience.cdk.fingerprint.Fingerprinter;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemFile;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.io.IChemObjectReader;
import org.openscience.cdk.io.ReaderFactory;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.descriptors.molecular.RuleOfFiveDescriptor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.HydrogenAdder;
import org.openscience.cdk.tools.manipulator.ChemFileManipulator;

public class Tanimoto {

    public static void main(String[] args) {
        try {
            String fileLocation = "/home/ra/bibliothek/documents/presentations/2008-02-26-poznan/running_example/";
            String target = "000739719_016.sdf";
            String model = "N05AA01_min.sdf";
            String fileModel = fileLocation + model;
            String fileTarget = fileLocation + target;
            AtomContainer modelAC = readFile(fileModel);
            AtomContainer targetAC = readFile(fileTarget);
            HydrogenAdder adder = new HydrogenAdder();
            adder.addExplicitHydrogensToSatisfyValency(targetAC);
            adder.addExplicitHydrogensToSatisfyValency(modelAC);
            System.out.print(targetAC);
            Fingerprinter ef = new Fingerprinter();
            BitSet bs1 = ef.getFingerprint(modelAC);
            BitSet bs2 = ef.getFingerprint(targetAC);
            System.out.println(bs1);
            System.out.println(bs2);
            getTanimoto(bs1, bs2);
            System.out.println(org.openscience.cdk.similarity.Tanimoto.calculate(bs1, bs2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTanimoto(BitSet bs1, BitSet bs2) {
        System.out.println(bs1);
        System.out.println(bs2);
        BitSet c = (BitSet) bs1.clone();
        c.and(bs2);
        System.out.println(c);
        System.out.println(c.cardinality());
        BitSet a = (BitSet) bs1.clone();
        a.andNot(bs2);
        System.out.println(a);
        System.out.println(a.cardinality());
        BitSet b = (BitSet) bs2.clone();
        b.andNot(bs1);
        System.out.println(b);
        System.out.println(b.cardinality());
        float tanimoto = (float) c.cardinality() / ((float) a.cardinality() + (float) b.cardinality() + (float) c.cardinality());
        System.out.println(tanimoto);
    }

    /**
	 * read one file.. return FIRST model (eg sdf or so...)
	 * @param fileName
	 * @return
	 */
    public static AtomContainer readFile(String fileName) {
        try {
            FileInputStream in = new FileInputStream(fileName);
            IChemObjectReader cor = new ReaderFactory().createReader(in);
            IChemFile content = (IChemFile) cor.read(DefaultChemObjectBuilder.getInstance().newChemFile());
            System.out.println("Read the file");
            List containers = ChemFileManipulator.getAllAtomContainers(content);
            System.out.println("Got " + containers.size() + " atom containers");
            SmilesGenerator sg = new SmilesGenerator();
            if (containers.size() > 1) {
                System.out.println("hey raphael... i only want 1 model!");
            }
            return (AtomContainer) containers.get(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (CDKException e) {
            e.printStackTrace();
            return null;
        }
    }
}
