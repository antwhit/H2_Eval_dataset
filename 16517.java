class Program {

    String source;

    Token[] tokens;

    Symbol[] symbols;

    Tree tree;

    Program(String text) {
        source = text;
        tokens = null;
        symbols = null;
        tree = null;
    }

    static class Range {

        int start;

        int end;
    }

    Range findRange(Tree tree) {
        System.out.println("Program.range() undefined");
        return new Range();
    }
}

;
