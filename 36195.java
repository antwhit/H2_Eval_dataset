import jp.ne.so_net.ga2.no_ji.jcom.*;

public class testTypeInfo {

    /**
		You look at the ITypeInfo of the ProgcId which is  appointed
	*/
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: testTypeInfo <ProgID>");
            System.out.println("Example testTypeInfo Excel.Application");
            return;
        }
        String progID = args[0];
        ReleaseManager rm = new ReleaseManager();
        try {
            IDispatch disp = new IDispatch(rm, progID);
            ITypeInfo typeInfo = disp.getTypeInfo();
            String[] docs = typeInfo.getDocumentation(-1);
            System.out.println("docs[0]=" + docs[0]);
            System.out.println("docs[1]=" + docs[1]);
            System.out.println("docs[2]=" + docs[2]);
            System.out.println("docs[3]=" + docs[3]);
            ITypeInfo.TypeAttr attr = typeInfo.getTypeAttr();
            System.out.println("TYPEATTR.IID        = " + attr.getIID());
            System.out.println("TYPEATTR.tpekind    = " + attr.getTypeKind());
            System.out.println("TYPEATTR.cFuncs     = " + attr.getFuncs());
            System.out.println("TYPEATTR.cVars      = " + attr.getVars());
            System.out.println("TYPEATTR.cImplTypes = " + attr.getImplTypes());
            IPersist persist = (IPersist) disp.queryInterface(IPersist.class, IPersist.IID);
            GUID clsid = persist.getClassID();
            System.out.println("CLSID=" + clsid);
            String progid = Com.getProgIDFromCLSID(clsid);
            System.out.println("ProgID=" + progid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rm.release();
        }
    }
}
