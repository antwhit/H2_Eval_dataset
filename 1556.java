import java.io.*;
import java.util.*;

class PpaDecls {

    class Decl {

        private String name;

        private String type;

        public Decl(String n, String t) {
            name = n;
            type = t;
        }

        public void print(PrintStream file) {
            file.println("\tcreatePPF<userPPF::" + type + ">(\"" + name + "\");");
        }

        public void printInclude(PrintStream file) {
            file.println("#include \"llt_" + type + ".hh\"");
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

    class NotImportedException extends Exception {

        private String badType;

        public NotImportedException(String type) {
            badType = type;
        }

        public String getBadType() {
            return badType;
        }
    }

    private LinkedList declList;

    public PpaDecls() {
        declList = new LinkedList();
    }

    public void validate(LinkedList importTypes) throws NotImportedException {
        Iterator decls = declList.iterator();
        int listSize = declList.size();
        for (int i = 0; i < listSize; i++) {
            Decl decl = (Decl) decls.next();
            if (!importTypes.contains(decl.getType())) {
                throw new NotImportedException(decl.getType());
            }
        }
    }

    public void print(PrintStream file) {
        Iterator decls = declList.iterator();
        int listSize = declList.size();
        for (int i = 0; i < listSize; i++) {
            Decl decl = (Decl) decls.next();
            decl.print(file);
        }
        file.println();
    }

    public void printIncludes(PrintStream file) {
        Iterator decls = declList.iterator();
        int listSize = declList.size();
        LinkedList doneList = new LinkedList();
        for (int i = 0; i < listSize; i++) {
            Decl decl = (Decl) decls.next();
            if (!doneList.contains(decl.getType())) {
                decl.printInclude(file);
                doneList.addLast(decl.getType());
            }
        }
        file.println();
    }

    public void addDecl(Decl d) {
        declList.addLast(d);
    }

    public HashMap getEnv() {
        Iterator decls = declList.iterator();
        int listSize = declList.size();
        HashMap env = new HashMap();
        LinkedList doneList = new LinkedList();
        for (int i = 0; i < listSize; i++) {
            Decl decl = (Decl) decls.next();
            env.put(decl.getName(), decl.getType());
        }
        return env;
    }
}
