public interface McpPackage {

    public String packageName = "Root Package";

    public void handleMessage(McpMessage message);

    public void handleMessage(McpMultiLineMessage message);
}
