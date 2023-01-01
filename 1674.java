import jargs.gnu.CmdLineParser;

public class import_brenda {

    /** Creates a new instance of import_brenda */
    public import_brenda() {
    }

    private static void printUsage() {
        System.out.println("usage: import_brenda [{-i,--inputdir} input files directory] " + "[{-o,--outputdir} output files directory] [{-t,--taxdir} taxonomy file] " + "[{-g,--getobsoletes} TRUE/FALSE]");
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
        String inputDirValue = "d:/temp/";
        String outputDirValue = "d:/temp/output/";
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
        if (!inputDirValue.endsWith("/")) inputDirValue = inputDirValue + "/";
        if (!outputDirValue.endsWith("/")) outputDirValue = outputDirValue + "/";
        if (!taxDirValue.endsWith("/")) taxDirValue = taxDirValue + "/";
        process_brenda brenda = new process_brenda();
        brenda.start(inputDirValue, outputDirValue, taxDirValue);
    }
}
