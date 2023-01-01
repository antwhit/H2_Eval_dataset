import org.antlr.runtime.*;
import java.io.*;
import org.antlr.stringtemplate.*;

public class Test {

    public static StringTemplateGroup templates;

    public static void main(String[] args) throws Exception {
        FileReader groupFile = new FileReader("translator/T.stg");
        StringTemplateGroup templates = new StringTemplateGroup(groupFile);
        groupFile.close();
        String file = new String("translator/input");
        ANTLRFileStream input = new ANTLRFileStream(file);
        FireLexer lexer = new FireLexer(input);
        WSFilter filter = new WSFilter(lexer);
        CommonTokenStream tokens = new CommonTokenStream(filter);
        FireParser parser = new FireParser(tokens);
        parser.setTemplateLib(templates);
        FireParser.s_return r = parser.s();
        StringTemplate program = (StringTemplate) r.getTemplate();
        System.out.println(program.toString());
        FileWriter fstream = new FileWriter("code/firegui/FireHelper.java");
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(program.toString());
        out.close();
    }

    public static String escape(String s) {
        StringBuffer r = new StringBuffer();
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == '\n') r.append("\\n"); else if (s.charAt(i) == '\r') r.append("\\r"); else r.append(s.charAt(i));
        return r.toString();
    }
}
