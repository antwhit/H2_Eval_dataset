public class Example {

    Example owned;

    Example someone;

    /** This method is pure and does not 
     * modify the object state.
     * Only pure methods can be called on
     * readonly references.
     */
    public String toString() {
        return "My example program.";
    }

    /** This method returns a rep reference
     * and can therefore only be called through
     * "this".
     */
    public Example getRep() {
        return owned;
    }

    public void doSomething() {
        owned = new Example();
        someone = owned;
        Example[] xe = new Example[10];
        xe[0] = owned;
        xe[1] = someone;
        Example[] xb = new Example[20];
        Example[] xf;
        xf = xe;
        xf = xb;
        xf = new Example[1];
    }
}
