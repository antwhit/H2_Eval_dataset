import idea.onlinePrgEnv.OnlineProgram;
import nacaLib.varEx.*;
import nacaLib.program.*;

/**
 * @author U930DI
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestOccurs extends OnlineProgram {

    DataSection WorkingStorage = declare.workingStorageSection();

    Var from_Zone = declare.level(1).var();

    Var from_Switch = declare.level(5).picX(1).valueSpaces().var();

    Var from_Passage = declare.level(5).picX(150).valueSpaces().var();

    Var from_Help = declare.level(5).var();

    Var help_Pocsr = declare.level(10).picS9(7).comp3().valueZero().var();

    Var help_Noerr = declare.level(10).picX(4).valueSpaces().var();

    Var help_Nomasqs = declare.level(10).picX(7).valueSpaces().var();

    Var from_Masque = declare.level(5).var();

    Var filler$2053 = declare.level(10).picX(12).valueSpaces().var();

    Var filler$2054 = declare.level(10).picX(7).valueSpaces().var();

    Var help_Idtrt = declare.level(10).picX(6).valueSpaces().var();

    Var filler$2055 = declare.level(10).picX(73).valueSpaces().var();

    Var help_Cdtrans = declare.level(10).picX(4).valueSpaces().var();

    Var filler$241 = declare.level(10).picX(2299).var();

    Var pos_Cursor = declare.level(5).picS9(4).comp().var();

    Var res_Annul = declare.level(5).picX(1).var();

    Var Array = declare.level(5).occurs(16).var();

    Var A = declare.level(10).picX(4).var();

    Var B = declare.level(10).picX(3).var();

    Var C = declare.level(10).var();

    Var C1 = declare.level(15).picX(1).var();

    Var C2 = declare.level(15).picX(4).var();

    Var C3 = declare.level(15).picX(3).var();

    Var v1 = declare.level(1).var();

    Var sv_Adrnos = declare.level(5).var();

    Var sv_Adrnol = declare.level(10).occurs(3).var();

    Var sv_Adrno00 = declare.level(15).occurs(7).picX(3).var();

    public void procedureDivision() {
        setAssertActive(true);
        perform(TestsOccurs);
        CESM.returnTrans();
    }

    Paragraph TestsOccurs = new Paragraph(this) {

        public void run() {
            TestsOccurs();
        }
    };

    void TestsOccurs() {
        String cs = "abc";
        move(cs, sv_Adrno00.getAt(3, 5));
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 7; j++) {
                cs = "" + i + "," + j;
                move(cs, sv_Adrno00.getAt(i, j));
            }
        }
        cs = help_Pocsr.toString();
        int n1 = 0;
        int nC = C.DEBUGgetBodyAbsolutePosition();
        int nC1 = C1.DEBUGgetBodyAbsolutePosition();
        assertIfFalse(nC == nC1);
        Var vVC1100 = C1.getAt(1);
        int nVC1100 = vVC1100.DEBUGgetBodyAbsolutePosition();
        Var vVC11 = C1.getAt(1);
        int nVC11 = vVC11.DEBUGgetBodyAbsolutePosition();
        assertIfFalse(nC == nVC11);
        Var vVC21 = C2.getAt(1);
        int nVC21 = vVC21.DEBUGgetBodyAbsolutePosition();
        assertIfFalse(nC + 1 == nVC21);
        Var vVC31 = C3.getAt(1);
        int nVC31 = vVC31.DEBUGgetBodyAbsolutePosition();
        assertIfFalse(nC + 1 + 4 == nVC31);
        Var vVC12 = C1.getAt(2);
        int nVC12 = vVC12.DEBUGgetBodyAbsolutePosition();
        assertIfFalse(nC + 1 * (4 + 3 + 1 + 4 + 3) == nVC12);
        Var vVC22 = C2.getAt(2);
        int nVC22 = vVC22.DEBUGgetBodyAbsolutePosition();
        assertIfFalse(nC + 1 * (4 + 3 + 1 + 4 + 3) + 1 == nVC22);
        Var vVC32 = C3.getAt(2);
        int nVC32 = vVC32.DEBUGgetBodyAbsolutePosition();
        assertIfFalse(nC + 1 * (4 + 3 + 1 + 4 + 3) + 1 + 4 == nVC32);
        move("toto", C1.getAt(1));
        String cs2 = C2.getAt(1).getString();
        int n = 0;
    }
}
