import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author Benny
 * @version 2.22 01.11.2004
 */
public class Ondex_Transfac_Database_Interface {

    private final String FILENAME = new String("TRANSFAC.SQL");

    private final String GENEDIR = new String("gene_data/");

    private final String FACTORDIR = new String("factor_data/");

    private final String MATRIXDIR = new String("matrix_data/");

    public static HashSet writtenConcepts = new HashSet();

    public Ondex_Transfac_Database_Interface(String inputDirValue, String outputDirValue, String taxDirValue, String getObsoletesValue) {
        makeOutputDirs(outputDirValue);
        makeTransfacSQL(outputDirValue);
        System.out.println("Starting transfac parsing...");
        new GeneParser(inputDirValue, outputDirValue, taxDirValue);
        new MatrixParser(inputDirValue, outputDirValue, taxDirValue);
        new FactorParser(inputDirValue, outputDirValue, taxDirValue);
        System.out.println("Transfac parsing finished!");
    }

    private void makeOutputDirs(String outputDirValue) {
        File outputDir = new File(outputDirValue);
        File outputDirFactor = new File(outputDirValue + GENEDIR);
        File outputDirGene = new File(outputDirValue + FACTORDIR);
        File outputDirMatrix = new File(outputDirValue + MATRIXDIR);
        if (!outputDir.exists()) outputDir.mkdir();
        if (!outputDirFactor.exists()) outputDirFactor.mkdir();
        if (!outputDirGene.exists()) outputDirGene.mkdir();
        if (!outputDirMatrix.exists()) outputDirMatrix.mkdir();
    }

    private void makeTransfacSQL(String outputDirValue) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputDirValue + FILENAME));
            bw.write("copy concept(id, taxid, description, url, of_type_FK, element_of) from '" + outputDirValue + FACTORDIR + "concept.dat' with null as '';\n");
            bw.write("copy concept_name(name, id, element_of,name_orig, is_preferred) from '" + outputDirValue + FACTORDIR + "concept_name.dat';\n");
            bw.write("copy concept_acc(element_of, id, concept_accession, ambiguous) from '" + outputDirValue + FACTORDIR + "concept_acc.dat';\n");
            bw.write("copy seq(seq, sequence_type_FK, concept_FK, id) from '" + outputDirValue + FACTORDIR + "sequence.dat';\n");
            bw.write("copy concept(id, taxid, description, url, of_type_FK, element_of) from '" + outputDirValue + GENEDIR + "concept.dat' with null as '';\n");
            bw.write("copy concept_name(name, id, element_of, name_orig, is_preferred) from '" + outputDirValue + GENEDIR + "concept_name.dat';\n");
            bw.write("copy concept_acc(element_of, id, concept_accession, ambiguous) from '" + outputDirValue + GENEDIR + "concept_acc.dat';\n");
            bw.write("copy concept(id, of_type_FK, element_of) from '" + outputDirValue + MATRIXDIR + "concept.dat';\n");
            bw.write("copy gds_concept(attribute_value, attribute_name_FK, concept_FK) from '" + outputDirValue + MATRIXDIR + "gds_concept.dat';\n");
            bw.write("copy relation(to_concept, from_concept, of_type, to_element_of, from_element_of) from '" + outputDirValue + FACTORDIR + "relation.dat';\n");
            bw.write("copy evidence(mapping_method_FK, to_concept_FK, from_concept_FK, of_type_FK) from '" + outputDirValue + FACTORDIR + "evidence.dat';\n");
            bw.close();
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("Transfac SQL file written");
        System.gc();
    }

    public static void main(String[] args) {
        new Ondex_Transfac_Database_Interface("/usr/local/apache2/cgi-bin/biobase/transfac/8.3/data/", "/home/ddrive/Java/OTDI/output/", "/home/ddrive/Java/OTDI/", "Tolle Wurst, dieses Programm!");
        System.exit(0);
    }
}
