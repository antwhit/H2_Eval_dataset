public class OO7_AssemblyImpl extends OO7_DesignObjectImpl implements OO7_Assembly {

    OO7_ComplexAssembly theSuperAssembly;

    OO7_Module theModule;

    public OO7_AssemblyImpl() {
    }

    public void setSuperAssembly(OO7_ComplexAssembly x) {
        theSuperAssembly = x;
    }

    public OO7_ComplexAssembly superAssembly() {
        return theSuperAssembly;
    }

    public void setModule(OO7_Module x) {
        theModule = x;
    }

    public OO7_Module module() {
        return theModule;
    }
}
