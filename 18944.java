import java.util.*;

public class exprIVLong extends exprIV {

    Long value;

    exprIVLong() {
        super();
    }

    void setValue(Integer v) {
        this.value = new Long(v.longValue());
    }

    void setValue(Long v) {
        this.value = v;
    }

    Long getValue() {
        return value;
    }

    int constEval() {
        return value.intValue();
    }

    int splitEC() {
        int returnCodeType;
        if (ecl.argEcl.preferC) {
            returnCodeType = stmtNode.CODETYPEC;
        } else {
            returnCodeType = stmtNode.CODETYPEE;
        }
        setCodeType(returnCodeType);
        return returnCodeType;
    }

    exprNode extractFuncOper(Vector formals) {
        exprIVLong eiv = new exprIVLong();
        eiv.setValue(this.value);
        return (exprNode) eiv;
    }

    void extractPrototypesRecurse(Vector list) {
    }

    String exprName() {
        StringBuffer str = new StringBuffer();
        str.append("iv_long");
        str.append("_" + retType.typeName());
        str.append(value.toString());
        return str.toString();
    }

    void printC(structuredStream out) throws java.io.IOException {
        out.print(value.toString());
    }

    void printE(structuredStream out) throws java.io.IOException {
        out.print(value.toString());
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(value);
        return str.toString();
    }
}
