public class B {

    {
        System.out.println("Class B loaded");
    }

    public B() {
        System.out.println("Class B instantiated, CL = " + this.getClass().getClassLoader());
    }
}
