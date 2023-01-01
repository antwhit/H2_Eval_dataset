import polyglot.ast.*;
import polyglot.visit.*;
import polyglot.types.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl.types.*;
import polyglot.util.Position;

public class ASTTransformer {

    public Node transformAst(Node n) {
        NodeVisitor unfin = new Unfinalizer();
        n = n.visitChildren(unfin);
        NodeVisitor printer = new PrintVisitor();
        n = n.visitChildren(printer);
        return n;
    }
}

class Unfinalizer extends NodeVisitor {

    public Node override(Node n) {
        n = n.visitChildren(this);
        return changeNode(n);
    }

    private Node changeNode(Node n) {
        Node r = null;
        if (n instanceof FieldDecl) {
            r = convertFieldDecl((FieldDecl) n);
        } else if (n instanceof Field_c) {
            r = convertField((Field_c) n);
        }
        return r;
    }

    private Node convertFieldDecl(FieldDecl fd) {
        Flags fl = fd.flags();
        if (fl.isFinal()) {
            fl = fl.clearFinal();
        }
        return (Node) fd.flags(fl);
    }

    private Node convertField(Field_c f) {
        FieldInstance fi = f.fieldInstance();
        TypeSystem ts = fi.typeSystem();
        Position pos = fi.position();
        ReferenceType container = fi.container();
        Flags flags = fi.flags();
        if (flags.isFinal()) {
            flags = flags.clearFinal();
        }
        Type type = fi.type();
        String name = fi.name();
        FieldInstance newfi = new FieldInstance_c(ts, pos, container, flags, type, name);
        return (Node) f.fieldInstance(newfi);
    }
}

class PrintVisitor extends NodeVisitor {

    private String tab = "";

    public Node override(Node n) {
        String xstr = getExtraString(n);
        System.out.println(">" + tab + "\\__" + n + xstr);
        String oldtab = tab;
        tab += "|  ";
        n.visitChildren(this);
        tab = oldtab;
        return n;
    }

    private String getExtraString(Node n) {
        if (n instanceof Field) {
            FieldInstance fi = ((Field) n).fieldInstance();
            return "  [FIELDINSTANCE " + fi.isConstant() + " " + fi.constantValue() + "]";
        }
        return "";
    }
}
