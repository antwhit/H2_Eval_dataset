import java.io.*;

public class McpTest {

    public static void main(String[] args) {
        McpSession session = new McpSession(new DataOutputStream(System.out));
        session.printstream = System.out;
        doAllTests(session);
        doSingleLineTest();
        doMultiLineTest();
    }

    public static void doSingleLineTest() {
        try {
            println("original: #$#say 12345 what: \"Hi there!\" from: Biff to: Betty");
            McpMessage test = new McpMessage("#$#say 12345 what: \"Hi there!\" from: Biff to: Betty");
            println("parsed:   " + test);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doMultiLineTest() {
        println("#$#spam 12345 from: Biff text*: \"\" _data-tag: 9b76");
        McpMultiLineMessage test = new McpMultiLineMessage("#$#spam 12345 from: Biff text*: \"\" _data-tag: 9b76");
        if (test.isMultiLine) {
            println("Message is multiline.");
        }
        test.parseNewLineData("#$#* 9b76 text: This is some sample text.");
        test.parseNewLineData("#$#* 9b76 text: ");
        test.parseNewLineData("#$#* 9b76 text: Note that you don't need to quote strings");
        test.parseNewLineData("#$#* 9b76 text: in multiline data.  Also, you can include \"special\"");
        test.parseNewLineData("#$#* 9b76 text: characters like quotes.  Everything after the");
        test.parseNewLineData("#$#* 9b76 text: space after the keyword and colon is considered");
        test.parseNewLineData("#$#* 9b76 text: part of the value.");
        test.parseNewLineData("#$#* 9b76 text:     This means that spaces can also be part of the value.");
        test.isMultiLineComplete = true;
        println("" + test);
    }

    public static void doAllTests(McpSession session) {
        println("Quoted Mcp line");
        session.parseLine("#$\"#$#this isn't: really an: \"out-of-band message\"");
        println("Not valid Mcp Line");
        session.parseLine("#$#say 12345 what: \"Hi there!\" WHAT: \"Hey there...\" from: Biff to: Betty");
        println("Valid Mcp Line");
        session.parseLine("#$#say 12345 what: \"Hi there!\" from: Biff to: Betty");
        println("Multiline Mcp Line");
        session.parseLine("#$#spam 12345 from: Biff text*: \"\" _data-tag: 9b76");
        session.parseLine("#$#* 9b76 text: This is some sample text.");
        session.parseLine("#$#* 9b76 text: ");
        session.parseLine("#$#* 9b76 text: Note that you don't need to quote strings");
        session.parseLine("#$#* 9b76 text: in multiline data.  Also, you can include \"special\"");
        session.parseLine("#$#* 9b76 text: characters like quotes.  Everything after the");
        session.parseLine("#$#* 9b76 text: space after the keyword and colon is considered");
        session.parseLine("#$#* 9b76 text: part of the value.");
        session.parseLine("#$#* 9b76 text:     This means that spaces can also be part of the value.");
        session.parseLine("#$#: 9b76 ");
        println("Version line");
        session.parseLine("#$#mcp version: 2.1 to: 2.1");
        println("Authentication Key Negotiation");
        session.parseLine("#$#mcp authentication-key: 18972163558 version: 1.0 to: 2.1");
        println("Package Negotiation");
        session.parseLine("#$#mcp-negotiate-can 1234 package: edit min-version: 1.0 max-version: 1.0");
        println("Package negotiation for the negotiation package");
        session.parseLine("#$#mcp-negotiate-can 1234 package: mcp-negotiate min-version: 1.0 max-version: 2.0");
        println("Completion of Negotiation");
        session.parseLine("#$#mcp-negotiate-end 1234");
        println("Mcp-Cord package");
        session.parseLine("#$#mcp-negotiate-can 1234 package: mcp-cord min-version: 1.0 max-version: 1.0");
        session.parseLine("$#mcp-cord-open 3487 _id: I12345 _type: whiteboard");
        session.parseLine("#$#mcp-cord 3487 _id: I12345 _message: delete-stroke stroke-id: 12321");
        session.parseLine("#$#mcp-cord-closed 3487 _id: I12345");
    }

    private static String debugLabel = "McpTest: ";

    private static void println(String output) {
        System.out.println(debugLabel + output);
    }
}
