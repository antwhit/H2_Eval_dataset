public abstract class test182j {

    public String tstPublic() {
        return "tstPublic";
    }

    protected String tstProtected() {
        return "tstProtected";
    }

    protected final String tstFinalProtected() {
        return "tstFinalProtected";
    }

    public final String tstFinalPublic() {
        return "tstFinalPublic";
    }

    public String tstOverridePublic() {
        return "tstOverridePublic";
    }

    protected String tstOverrideProtected() {
        return "tstOverrideProtected";
    }

    protected final String tstOverrideFinalProtected() {
        return "tstOverrideFinalProtected";
    }

    public final String tstOverrideFinalPublic() {
        return "tstOverrideFinalPublic";
    }

    public abstract String tstAbstractPublic();

    protected abstract String tstAbstractProtected();

    public abstract String tstOverrideAbstractPublic();

    protected abstract String tstOverrideAbstractProtected();
}
