import java.util.*;

public class exprIVString extends exprIV {

    private String value;

    void setValue(String v) {
        this.value = v;
    }

    String getValue() {
        return value;
    }

    private String name;

    void setName(String v) {
        this.name = v;
    }

    String getName() {
        return name;
    }

    exprIVString() {
        super();
    }

    int splitEC() {
        int returnCodeType;
        returnCodeType = stmtNode.CODETYPEC;
        setCodeType(returnCodeType);
        return returnCodeType;
    }

    exprNode extractFuncOper(Vector formals) {
        exprIVString eiv = new exprIVString();
        eiv.setValue(this.value);
        return (exprNode) eiv;
    }

    void extractPrototypesRecurse(Vector list) {
    }

    String exprName() {
        if (name != null) return name;
        StringBuffer str = new StringBuffer();
        str.append("iv_string");
        str.append("_" + retType.typeName());
        Integer i = new Integer(identifier.f_index++);
        str.append(i.toString());
        name = str.toString();
        return name;
    }

    void printC(structuredStream out) throws java.io.IOException {
        out.print("\"" + ((String) value).toString() + "\"");
    }

    void printE(structuredStream out) throws java.io.IOException {
        out.print(((String) value).toString());
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append((String) value);
        return str.toString();
    }
}
