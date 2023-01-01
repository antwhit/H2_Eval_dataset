import RESOLVE.*;
import RESOLVE.Main.Concepts.Standard.Boolean.*;
import RESOLVE.Main.Concepts.Standard.Integer.*;

public class Integer_Examples {

    Boolean_Template Std_Boolean_Fac = new Std_Boolean_Realiz();

    Integer_Template Std_Integer_Fac = new Std_Integer_Realiz();

    public void Main() {
        Integer_Template.Integer I1 = Std_Integer_Fac.createInteger();
        Integer_Template.Integer I2 = Std_Integer_Fac.createInteger();
        Integer_Template.Integer I3 = Std_Integer_Fac.createInteger();
        Boolean_Template.Boolean B = Std_Boolean_Fac.createBoolean();
        Std_Integer_Fac.assign(I1, Std_Integer_Fac.createInteger(1));
        Std_Integer_Fac.assign(I2, Std_Integer_Fac.createInteger(2));
        Std_Boolean_Fac.assign(B, Std_Integer_Fac.Are_Equal(I1, I2));
        B = Std_Integer_Fac.Are_Equal(I1, I2);
        Std_Boolean_Fac.assign(B, Std_Integer_Fac.Are_Not_Equal(I1, I2));
        B = Std_Integer_Fac.Are_Not_Equal(I1, I2);
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Replica(I1));
        Std_Integer_Fac.assign(I3, I1);
        Std_Boolean_Fac.assign(B, Std_Integer_Fac.Less_Or_Equal(I1, I2));
        B = Std_Integer_Fac.Less_Or_Equal(I1, I2);
        Std_Boolean_Fac.assign(B, Std_Integer_Fac.Less(I2, I1));
        B = Std_Integer_Fac.Less(I2, I1);
        Std_Boolean_Fac.assign(B, Std_Integer_Fac.Greater_Or_Equal(I1, I2));
        B = Std_Integer_Fac.Greater_Or_Equal(I1, I2);
        Std_Boolean_Fac.assign(B, Std_Integer_Fac.Greater(I2, I1));
        B = Std_Integer_Fac.Greater(I2, I1);
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Sum(I1, I2));
        I3 = Std_Integer_Fac.Sum(I1, I2);
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Negate(I1));
        I3 = Std_Integer_Fac.Negate(I1);
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Difference(I1, I2));
        I3 = Std_Integer_Fac.Difference(I1, I2);
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Product(I1, I2));
        I3 = Std_Integer_Fac.Product(I1, I2);
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Power(I1, I2));
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Quotient(I2, I1));
        I3 = Std_Integer_Fac.Quotient(I2, I1);
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Mod(I2, I1));
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Rem(I1, I2));
        Std_Integer_Fac.assign(I3, Std_Integer_Fac.Div(I1, I2));
    }

    public static void main(String args[]) {
        Integer_Examples start = new Integer_Examples();
        start.Main();
    }

    public Integer_Examples() {
    }
}
