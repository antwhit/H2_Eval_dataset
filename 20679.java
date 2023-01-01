import org.w3c.dom.Element;

public class AnanovaConfig {

    private String baseuri;

    private static AnanovaConfig ac;

    private AnanovaConfig() {
    }

    public static synchronized AnanovaConfig getInstance() {
        if (ac == null) {
            ac = new AnanovaConfig();
        }
        return ac;
    }

    public void configure(Element acElement) {
        String baseuri = acElement.getAttribute("baseuri");
        if (baseuri != null) {
            System.out.println("Setting baseuri to: " + baseuri);
            this.baseuri = baseuri;
        }
    }

    public String getBaseUri() {
        return baseuri;
    }
}
