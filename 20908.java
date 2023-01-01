/**
 * This class represents a "substitution variable" which can be incorporated 
 * into a template document.
 */
public class TemplateVar {

    private String name;

    private String prompt;

    private String helpText = "";

    private String defaultValue = "";

    private String value = "";

    public TemplateVar(String name) {
        this.name = name;
        this.prompt = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameVal) {
        name = nameVal;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String promptVal) {
        prompt = promptVal;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpTextVal) {
        helpText = helpTextVal;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValueVal) {
        defaultValue = defaultValueVal;
    }

    public String getValue() {
        if (value.equals("")) {
        }
        return value;
    }

    public void setValue(String valueVal) {
        value = valueVal;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Name: " + name + "\n");
        sb.append("... Prompt: " + prompt + "\n");
        sb.append("... HelpText: " + helpText + "\n");
        sb.append("... DefaultValue: " + defaultValue + "\n");
        sb.append("... Value: " + value + "\n");
        return sb.toString();
    }
}
