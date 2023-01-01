public interface ParserConstants {

    int EOF = 0;

    int PREDICATE = 5;

    int FUNCTION = 6;

    int PRINTLN = 7;

    int PRINT = 8;

    int REPEAT = 9;

    int RETURN = 10;

    int WHILE = 11;

    int UNTIL = 12;

    int FROM = 13;

    int THEN = 14;

    int ELIF = 15;

    int ELSE = 16;

    int NUM = 17;

    int CHAR = 18;

    int NEXT = 19;

    int STOP = 20;

    int STR = 21;

    int SET = 22;

    int OBJ = 23;

    int ISA = 24;

    int FOR = 25;

    int SUB = 26;

    int END = 27;

    int TO = 28;

    int DO = 29;

    int OD = 30;

    int IF = 31;

    int FI = 32;

    int BY = 33;

    int IN = 34;

    int TRUE = 35;

    int FALSE = 36;

    int NULL = 37;

    int LESS = 38;

    int LESSEQ = 39;

    int GREATER = 40;

    int GREATEQ = 41;

    int NOTCOMP = 42;

    int EQUAL = 43;

    int NOTEQ = 44;

    int ISIN = 45;

    int NOTIN = 46;

    int PLUS = 47;

    int MINUS = 48;

    int PROD = 49;

    int DIV = 50;

    int MOD = 51;

    int AND = 52;

    int OR = 53;

    int NOT = 54;

    int ASSIGN = 55;

    int LPAREN = 56;

    int RPAREN = 57;

    int LCURLY = 58;

    int RCURLY = 59;

    int LSQUARE = 60;

    int RSQUARE = 61;

    int COMMA = 62;

    int SEMICOL = 63;

    int IDENTIFIER = 64;

    int LETTER = 65;

    int DIGIT = 66;

    int NUMBER = 67;

    int CHARACTER = 68;

    int ESCAPE_SEQUENCE = 69;

    int STRING = 70;

    int SINGLE_LINE_COMMENT = 71;

    int COMMENT_START = 72;

    int COMMENT_END = 73;

    int COMMENT_START_NESTED = 74;

    int DEFAULT = 0;

    int IN_COMMENT = 1;

    String[] tokenImage = { "<EOF>", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"predicate\"", "\"function\"", "\"println\"", "\"print\"", "\"repeat\"", "\"return\"", "\"while\"", "\"until\"", "\"from\"", "\"then\"", "\"elif\"", "\"else\"", "\"num\"", "\"char\"", "\"next\"", "\"stop\"", "\"str\"", "\"set\"", "\"obj\"", "\"isa\"", "\"for\"", "\"sub\"", "\"end\"", "\"to\"", "\"do\"", "\"od\"", "\"if\"", "\"fi\"", "\"by\"", "\"in\"", "\"true\"", "\"false\"", "\"null\"", "\"<\"", "\"<=\"", "\">\"", "\">=\"", "\"<>\"", "\"==\"", "\"!=\"", "\"=in\"", "\"!in\"", "\"+\"", "\"-\"", "\"*\"", "\"/\"", "\"%\"", "\"&\"", "\"|\"", "\"!\"", "\"=\"", "\"(\"", "\")\"", "\"{\"", "\"}\"", "\"[\"", "\"]\"", "\",\"", "\";\"", "<IDENTIFIER>", "<LETTER>", "<DIGIT>", "<NUMBER>", "<CHARACTER>", "<ESCAPE_SEQUENCE>", "<STRING>", "<SINGLE_LINE_COMMENT>", "\"/*\"", "\"*/\"", "\"/*\"", "<token of kind 75>", "\".\"", "\":\"" };
}
