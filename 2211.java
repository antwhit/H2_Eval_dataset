import static net.jadoth.util.chars.VarChar.MediumVarChar;
import net.jadoth.collections.X;
import net.jadoth.collections.types.XEnum;
import net.jadoth.sqlengine.SQL.DATATYPE;
import net.jadoth.sqlengine.retrospection.definition.ColumnDefinition;
import net.jadoth.util.chars.VarChar;

public class MainTestSynchronizeTableStructure<E> {

    static void print(final ColumnDefinition[] source, final ColumnDefinition[] target) {
        final VarChar vc = MediumVarChar();
        print(vc, source);
        System.out.println();
        print(vc, target);
    }

    static void print(final VarChar vc, final ColumnDefinition[] cols) {
        for (int i = 0; i < cols.length; i++) {
            vc.append(cols[i].getName()).append('\t');
        }
        vc.append('\n');
        for (int i = 0; i < cols.length; i++) {
            vc.append(cols[i].getType()).append(' ').append(cols[i].isNotNull() ? 1 : 0).append(' ').append(cols[i].isUnique() ? 1 : 0).append('\t');
        }
        vc.append('\n');
    }

    public static void main(final String[] args) {
        final XEnum<ColumnDefinition> source, target;
        source = X.Enum(new ColumnDefinition("value_1", DATATYPE.INT, 0, false, false, "5"), new ColumnDefinition("value_B", DATATYPE.INT, 0, false, false, "5"), new ColumnDefinition("stringA", DATATYPE.VARCHAR, 0, false, false, "5"));
        target = X.Enum(new ColumnDefinition("value_1", DATATYPE.INT, 0, false, false, "5"), new ColumnDefinition("string1", DATATYPE.VARCHAR, 0, false, false, "5"), new ColumnDefinition("value_2", DATATYPE.INT, 0, false, false, "5"));
        final ColumnDefinition[] linked = null;
        final VarChar vc = MediumVarChar();
        vc.append("input:").lf();
        print(vc, source.toArray(ColumnDefinition.class));
        vc.lf();
        print(vc, target.toArray(ColumnDefinition.class));
        vc.lf();
        vc.append("output:").lf();
        print(vc, source.toArray(ColumnDefinition.class));
        vc.lf();
        print(vc, linked);
        System.out.println(vc);
    }
}
