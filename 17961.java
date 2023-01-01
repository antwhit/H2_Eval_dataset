public class nodeLabel extends nodeBase {

    public nodeLabel(int id) {
        super(id);
    }

    public nodeLabel(JavaParser p, int id) {
        super(p, id);
    }

    public nodeLabel(csParser p, int i) {
        super(p, i);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public String getNodeTypeString() {
        return "nodeLabel";
    }
}
