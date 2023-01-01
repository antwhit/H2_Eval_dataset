import java.util.HashMap;

public class AccessRule {

    private String ruleGroup = "";

    private String ruleUrl = "";

    private HashMap<String, String> parameterList = new HashMap<String, String>();

    public AccessRule(String ruleData) throws Exception {
        ruleData = ruleData.trim();
        String[] ruleBits = ruleData.split(":");
        if (ruleBits.length != 3) {
            throw new Exception("Error with rule data (" + ruleData + ")");
        }
        ruleGroup = ruleBits[0];
        ruleUrl = ruleBits[1];
        if ("*".equals(ruleBits[2]) == false) {
            String[] paramBits = ruleBits[2].split("&");
            for (int x = 0; x < paramBits.length; x++) {
                String[] bits = paramBits[x].split("=");
                if (bits.length == 2) {
                    parameterList.put(bits[0], bits[1]);
                }
            }
        }
        System.out.println("Access Rule Loaded = " + this.toString());
    }

    public boolean isMatch(String url, HTTPurl requestData) {
        if (isUrlMatch(url) == false) return false;
        if (parameterList.size() > 0) {
            String[] keys = parameterList.keySet().toArray(new String[0]);
            for (int x = 0; x < keys.length; x++) {
                String matchValue = parameterList.get(keys[x]);
                String paramValue = requestData.getParameter(keys[x]);
                if (paramValue == null || matchValue.equals(paramValue) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isUrlMatch(String url) {
        if ("*".equals(ruleUrl) == true) {
            return true;
        }
        if (url.startsWith(ruleUrl) == true) {
            return true;
        }
        return false;
    }

    public String toString() {
        String param = "";
        if (parameterList.size() > 0) {
            String[] keys = parameterList.keySet().toArray(new String[0]);
            for (int x = 0; x < keys.length; x++) {
                String matchValue = parameterList.get(keys[x]);
                param += "(" + keys[x] + "=" + matchValue + ")";
            }
        } else {
            param = "*";
        }
        return ruleGroup + ":" + ruleUrl + ":" + param;
    }

    public String getRuleGroup() {
        return ruleGroup;
    }
}
