public class CastLookahead extends nodeBase {

    public CastLookahead(int id) {
        super(id);
    }

    public CastLookahead(JavaParser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public String getNodeTypeString() {
        return "castLookahead";
    }
}
