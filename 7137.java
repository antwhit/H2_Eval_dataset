import nacaLib.basePrgEnv.BaseProgram;
import nacaLib.mapSupport.*;
import nacaLib.varEx.*;
import nacaLib.program.*;

class TestMap8Map extends Map {

    static TestMap8Map Copy(BaseProgram program) {
        return new TestMap8Map(program);
    }

    static TestMap8Map Copy(BaseProgram program, CopyReplacing rep) {
        Assert("Unimplemented replacing for MAPs");
        return null;
    }

    TestMap8Map(BaseProgram program) {
        super(program);
    }

    Form rs3101f = declare.form("rs31a01$1", 24, 80);

    Edit simpcopn01 = declare.edit("impcopn01", 3).justifyFill(MapFieldAttrFill.BLANK).justify(MapFieldAttrJustify.LEFT).edit();

    Edit facjr121 = declare.edit("facjr121", 1).justifyFill(MapFieldAttrFill.BLANK).justify(MapFieldAttrJustify.LEFT).edit();

    Edit facjr221 = declare.edit("facjr221", 1).justifyFill(MapFieldAttrFill.BLANK).justify(MapFieldAttrJustify.LEFT).edit();

    Edit facjr122 = declare.edit("facjr122", 1).justifyFill(MapFieldAttrFill.BLANK).justify(MapFieldAttrJustify.LEFT).edit();

    Edit facjr222 = declare.edit("facjr222", 1).justifyFill(MapFieldAttrFill.BLANK).justify(MapFieldAttrJustify.LEFT).edit();
}
