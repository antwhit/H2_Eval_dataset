import jargs.gnu.CmdLineParser;

public class import_dra {

    /** Creates a new instance of import_go */
    public import_dra() {
    }

    private static void printUsage() {
        System.out.println("usage: import_dra [{-i,--inputdir} input files directory] " + "[{-o,--outputdir} output files directory] [{-t,--taxdir} taxonomy file] " + "[{-g,--getobsoletes} TRUE/FALSE]");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CmdLineParser parser = new CmdLineParser();
        CmdLineParser.Option inputDir = parser.addStringOption('i', "inputdir");
        CmdLineParser.Option outputDir = parser.addStringOption('o', "outputdir");
        CmdLineParser.Option taxDir = parser.addStringOption('t', "taxdir");
        CmdLineParser.Option getObsoletes = parser.addStringOption('g', "getobsoletes");
        String inputDirValue = "/vol/projects/ondex/data/importdata/GO/";
        String outputDirValue = "/vol/projects/ondex/main/import/GO/output/";
        String taxDirValue = "/vol/projects/ondex/data/importdata/taxonomy";
        String getObsoletesValue = "TRUE";
        try {
            parser.parse(args);
        } catch (CmdLineParser.OptionException e) {
            System.out.println(e.getMessage());
            printUsage();
            System.exit(2);
        }
        if ((args.length > 0) && (!args[0].startsWith("-"))) {
            printUsage();
            System.exit(2);
        }
        if (args.length > 0) {
            inputDirValue = (String) parser.getOptionValue(inputDir);
            outputDirValue = (String) parser.getOptionValue(outputDir);
            taxDirValue = (String) parser.getOptionValue(taxDir);
            getObsoletesValue = (String) parser.getOptionValue(getObsoletes);
        }
        System.out.println("Create process_dra object..");
        process_dra dra = new process_dra();
        System.out.println("created!");
        System.out.println("Call process_dra start method..");
        System.out.println("with:");
        System.out.println(inputDirValue);
        System.out.println(outputDirValue);
        System.out.println(taxDirValue);
        dra.start(inputDirValue, outputDirValue, taxDirValue, getObsoletesValue);
        System.out.println("called!");
    }
}
