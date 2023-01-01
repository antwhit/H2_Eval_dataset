import org.maverickdbms.basic.mvProgram;
import org.maverickdbms.basic.mvProperties;
import org.maverickdbms.basic.mvSession;
import org.maverickdbms.basic.mvString;
import org.maverickdbms.basic.mvVariable;

class helloWorld extends mvProgram {

    public mvVariable run(mvVariable[] arg) {
        mvString A = getString();
        A.set("Hello World!");
        getIO().PRINT(A, true);
        return null;
    }

    public void initVariables() {
    }

    public static void main(String[] argv) {
        mvProperties prop = new mvProperties();
        mvSession session = new mvSession(prop);
        helloWorld hw = new helloWorld();
        hw.init(session);
        hw.run(null);
    }
}

;
