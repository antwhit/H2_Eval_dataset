import java.util.*;

class typeTypedef extends type implements Cloneable {

    private identifier tag;

    void setTag(identifier i) {
        tag = i;
    }

    identifier getTag() {
        return tag;
    }

    /*********************************************************************
   * Constructors
   */
    typeTypedef() {
        this.tag = null;
    }

    /*********************************************************************
   * Public methods
   */
    boolean autoCast(type t2) {
        return realType().autoCast(t2);
    }

    boolean sameType(type t2) {
        if (!(t2 instanceof typeTypedef)) return false;
        if (!tag.getName().equals(((typeTypedef) t2).tag.getName())) return false;
        return true;
    }

    identifier searchField(String s) {
        return tag.getRetType().searchField(s);
    }

    type realType() {
        return tag.getRetType().realType();
    }

    String typeName() {
        StringBuffer str = new StringBuffer(tag.getName());
        if (str.toString().charAt(0) == '_') {
            return "ecl_" + str.toString();
        }
        return str.toString();
    }

    void printC(structuredStream out, identifier ident) throws java.io.IOException {
        if (tag != null) {
            out.print(tag.getName());
            out.print(" ");
        }
        if (ident != null) {
            ident.printCname(out);
        }
    }

    public String toString() {
        StringBuffer str = new StringBuffer(super.toString());
        if (tag != null) str.append(tag.getName() + " ");
        return str.toString();
    }
}
