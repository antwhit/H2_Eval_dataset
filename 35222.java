import RESOLVE.*;
import RESOLVE.Main.Concepts.Standard.Boolean.*;
import RESOLVE.Main.Concepts.Standard.Character.*;
import RESOLVE.Main.Concepts.Standard.Integer.*;
import RESOLVE.Main.Concepts.Standard.Char_Str.*;
import RESOLVE.Main.Concepts.Stack.*;

public class Alt_Rev_Stack {

    Boolean_Template Std_Boolean_Fac = new Std_Boolean_Realiz();

    Character_Template Std_Character_Fac = new Std_Character_Realiz();

    Integer_Template Std_Integer_Fac = new Std_Integer_Realiz();

    Char_Str_Template Std_Char_Str_Fac = new Std_Char_Str_Realiz();

    Stack_Template Stack_Fac = Obvious_Reading_Realiz.createProxy(Std_Integer_Fac.createInteger(), Std_Integer_Fac.createInteger(4), new Obvious_Reading_Realiz.Read_Entry() {

        public void Read_Entry(RType parm1) {
            Std_Integer_Fac.Read((Integer_Template.Integer) parm1);
        }
    }, Obvious_Writing_Realiz.createProxy(Std_Integer_Fac.createInteger(), Std_Integer_Fac.createInteger(4), new Obvious_Writing_Realiz.Write_Entry() {

        public void Write_Entry(RType parm1) {
            Std_Integer_Fac.Write_Line((Integer_Template.Integer) parm1);
        }
    }, new Clean_Array_Realiz(Std_Integer_Fac.createInteger(), Std_Integer_Fac.createInteger(4))));

    public void Main() {
        Stack_Template.Stack S = Stack_Fac.createStack();
        ((Reading_Capability) Stack_Fac).Read(S);
        Std_Char_Str_Fac.Write_Line(Std_Char_Str_Fac.createChar_Str("REVERSED ORDER"));
        ((Writing_Capability) Stack_Fac).Write(S);
    }

    public static void main(String args[]) {
        Alt_Rev_Stack start = new Alt_Rev_Stack();
        start.Main();
    }

    public Alt_Rev_Stack() {
    }
}
