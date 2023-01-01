class TestParser {

    private static SimpleCharStream instream;

    private static ParserTokenManager scanner;

    private static Parser parser;

    public static void main(String args[]) {
        java.io.InputStream infile;
        if (args.length < 1) {
            infile = System.in;
        } else try {
            infile = new java.io.FileInputStream(args[0]);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File " + args[0] + " not found.");
            return;
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Testing file " + args[0]);
        System.out.println("-------------------------------------------------------");
        instream = new SimpleCharStream(infile);
        scanner = new ParserTokenManager(instream);
        parser = new Parser(scanner);
        while (nextParseSkipErrors()) ;
    }

    private static boolean nextParseSkipErrors() {
        try {
            Parser.ResetErrors();
            parser.start();
            if (Parser.AnyErrors()) System.out.println("Parsing failed"); else System.out.println("Parsing succeeded");
        } catch (ParseAbortException e) {
            if (e.isEOF()) return false;
            System.out.println(e.toString());
        } catch (ParseException e) {
        }
        return true;
    }
}
