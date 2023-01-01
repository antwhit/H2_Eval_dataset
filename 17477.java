import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Lab3 {

    private static FileReader sourceFile;

    private static SymbolTable symbols = new SymbolTable();

    private static LiteralTable lTable = new LiteralTable();

    private static File outputFile;

    private static File listingOutputFile;

    private static boolean entExtAllowed = true;

    /**
	 * @param args
	 *            args[0] is the Assembly File to be converted into an Object
	 *            File for the Linker/Loader. args[1] is the Output File to
	 *            which the assembler writes the header and text records to.
	 *            args[2] is the Listing Output File that contains the formatted
	 *            object code as well as the source file.
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception {
        try {
            File source = new File(args[0]);
            if (source.exists() && source.length() > 0 && source.isFile()) {
                sourceFile = new FileReader(args[0]);
            } else {
                System.err.println("Source File is not valid or does not exist ... Exiting...");
                System.exit(1);
            }
        } catch (Throwable e) {
            System.out.println("Argument(s) " + e);
        }
        PassOne passOne = new PassOne(sourceFile, symbols, lTable);
        passOne.parse();
        try {
            File source = new File(args[0]);
            sourceFile = new FileReader(args[0]);
            outputFile = new File(args[1]);
            listingOutputFile = new File(args[2]);
            FileWriter listingWriter = new FileWriter(listingOutputFile);
            listingWriter.write("CSE 560 Assembler Listing for source file " + source.getName() + '\n');
            listingWriter.write("Objcode            Source\n\n");
            listingWriter.close();
            source.getPath();
        } catch (Throwable e) {
            System.out.println("Argument(s) " + e + " ... Exiting...");
            System.exit(1);
        }
        PassTwo passTwo = new PassTwo(passOne, sourceFile, outputFile, listingOutputFile);
        passTwo.OutputObjectFile();
    }
}
