import java.util.*;

class exprField extends exprNode {

    static final int DOT = 1;

    static final int PTR = 2;

    static final String operators[][] = { { "NONE", "NONE ", " NONE " }, { "DOT", ".", "<INVALID.> " }, { "PTR", "->", "<INVALID->> " } };

    int exprType;

    identifier fieldId;

    exprNode ex1;

    exprField(int t) {
        super();
        this.exprType = t;
        this.fieldId = null;
        this.ex1 = null;
    }

    int getExprType() {
        return exprType;
    }

    void setFieldId(identifier v) {
        this.fieldId = v;
    }

    identifier getFieldId() {
        return fieldId;
    }

    void setEx1(exprNode ex1) {
        this.ex1 = ex1;
    }

    exprNode getEx1() {
        return ex1;
    }

    int splitEC() {
        int returnCodeType;
        int r1 = ex1.splitEC();
        returnCodeType = stmtNode.CODETYPEC;
        setCodeType(returnCodeType);
        return returnCodeType;
    }

    void scanIdentifiers(Vector par, Vector defined) {
        if (ex1 != null) ex1.scanIdentifiers(par, defined);
    }

    void exprForceCRecurse() {
        if (ex1 != null) ex1.forceCAssignment();
    }

    void extractFunctionsRecurse(identifier module) {
        if (ex1 != null) ex1 = ex1.extractFunctions(module);
    }

    exprNode extractFuncOper(Vector formals) {
        exprField ef = new exprField(exprType);
        identifier formal_1 = new identifier("_ecl_a");
        formal_1.setRetType(ex1.retType);
        formals.addElement(formal_1);
        ef.setEx1(createReference(formal_1));
        ef.setFieldId(fieldId);
        return (exprNode) ef;
    }

    void extractParamSet(Vector actuals) {
        actuals.addElement(ex1);
    }

    void extractTypesRecurse(Vector list) {
        if (ex1 != null) ex1.extractTypes(list);
    }

    void extractPrototypesRecurse(Vector list) {
        if (ex1 != null) ex1.extractPrototypes(list);
    }

    String exprName() {
        StringBuffer str = new StringBuffer();
        str.append(operators[exprType][0]);
        str.append("_" + retType.typeName());
        str.append("_" + (ex1.getRetType()).typeName());
        str.append("_" + fieldId.getName());
        return str.toString();
    }

    void printC(structuredStream out) throws java.io.IOException {
        ex1.printC(out);
        out.print(operators[exprType][1]);
        fieldId.printCname(out);
    }

    void printE(structuredStream out) throws java.io.IOException {
        out.print(operators[exprType][2]);
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        switch(exprType) {
            case DOT:
                str.append(ex1 + "." + (fieldId).getName());
                break;
            case PTR:
                str.append(ex1 + "->" + (fieldId).getName());
                break;
            default:
                str.append("<unknown expression 4>");
                break;
        }
        return str.toString();
    }
}
