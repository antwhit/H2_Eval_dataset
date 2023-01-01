import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.filter.ElementFilter;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 * Demonstrates the use of {@link Parent#getDescendants}.
 */
public class DescendantDemo {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java DescendantDemo [web.xml]");
            return;
        }
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(args[0]);
        System.out.println("All content:");
        Iterator itr = doc.getDescendants();
        while (itr.hasNext()) {
            Content c = (Content) itr.next();
            System.out.println(c);
        }
        System.out.println();
        System.out.println("Only elements:");
        itr = doc.getDescendants(new ElementFilter());
        while (itr.hasNext()) {
            Content c = (Content) itr.next();
            System.out.println(c);
        }
        System.out.println();
        System.out.println("Everything that's not an element:");
        itr = doc.getDescendants(new ElementFilter().negate());
        while (itr.hasNext()) {
            Content c = (Content) itr.next();
            System.out.println(c);
        }
        System.out.println();
        System.out.println("Only elements with localname of servlet:");
        itr = doc.getDescendants(new ElementFilter("servlet"));
        while (itr.hasNext()) {
            Content c = (Content) itr.next();
            System.out.println(c);
        }
        System.out.println();
        System.out.println("Only elements with localname of servlet-name or servlet-class:");
        itr = doc.getDescendants(new ElementFilter("servlet-name").or(new ElementFilter("servlet-class")));
        while (itr.hasNext()) {
            Content c = (Content) itr.next();
            System.out.println(c);
        }
        System.out.println();
        System.out.println("Remove elements with localname of servlet:");
        itr = doc.getDescendants(new ElementFilter("servlet"));
        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }
        XMLOutputter outp = new XMLOutputter();
        outp.output(doc, System.out);
    }
}
