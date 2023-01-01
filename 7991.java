import static org.junit.Assert.*;
import java.awt.Dimension;
import java.beans.Expression;
import java.beans.Statement;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class RDFEncoder_unittest {

    private RDFEncoder encoder;

    private OutputStream output;

    private StringBuilder string;

    @Before
    public void setUp() throws Exception {
        string = new StringBuilder();
        output = new OutputStream() {

            private boolean closed;

            @Override
            public void write(final int oneByte) throws IOException {
                if (!closed) {
                    string.append((char) oneByte);
                }
            }

            public String toString() {
                return string.toString();
            }

            public void close() {
                closed = true;
            }
        };
        encoder = new RDFEncoder(output);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public final void testClear() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Dimension object = new Dimension(1, 1);
        object.setSize(111, 222);
        encoder.writeObject(object);
        encoder.close();
        encoder.clear();
        Field bindings = RDFEncoder.class.getDeclaredField("bindings");
        Field valueToExpression = RDFEncoder.class.getDeclaredField("valueToExpression");
        Field targetToStatementList = RDFEncoder.class.getDeclaredField("targetToStatementList");
        bindings.setAccessible(true);
        valueToExpression.setAccessible(true);
        targetToStatementList.setAccessible(true);
        Map b = (Map) bindings.get(encoder);
        Map v = (Map) valueToExpression.get(encoder);
        Map t = (Map) targetToStatementList.get(encoder);
        assertTrue("Should be empty", b.isEmpty());
        assertTrue("Should be empty", v.isEmpty());
        assertTrue("Should be empty", t.isEmpty());
    }

    @Test
    public final void testSetOwner() {
        final Object owner = new Object();
        encoder.setOwner(owner);
        assertEquals("Owner should be the same.", owner, encoder.getOwner());
    }

    @Test
    public final void testGetOwner() {
        assertEquals("Owner should be null", encoder.getOwner(), null);
    }

    @Test
    public final void testWriteObjectObject() {
        Dimension object = new Dimension(1, 1);
        object.setSize(111, 222);
        encoder.writeObject(object);
        encoder.close();
        assertTrue("Stream should contains 111 value", output.toString().contains("111"));
        assertTrue("Stream should contains 222 value", output.toString().contains("222"));
    }

    @Test
    public final void testWriteStatementStatement() {
        Dimension object = new Dimension(111, 222);
        encoder.writeStatement(new Statement(object, "setSize", new Object[] { 333, 333 }));
        encoder.writeObject(object);
        encoder.close();
        assertTrue("Stream should contains 333 value", output.toString().contains("333"));
    }

    @Test
    public final void testWriteExpressionExpression() {
        Dimension object = new Dimension(111, 222);
        final Expression exp = new Expression(object, "getHeight", new Object[0]);
        encoder.writeExpression(exp);
        final int exceptedHeight = (int) 222;
        final int realHeight = (int) object.getHeight();
        assertEquals("Values should be the same.", exceptedHeight, realHeight);
    }

    @Test
    public final void testGetValueExpression() {
        final Expression exp = new Expression(222, null, "getNumber", new Object[0]);
        assertEquals("Values should be the same.", 222, encoder.getValue(exp));
    }

    @Test
    public final void testFlush() {
        encoder.flush();
        assertFalse("Stream shouldn't be empty.", output.toString().isEmpty());
    }

    @Test
    public final void testClose() {
        final PrintStream printstream = new PrintStream(output);
        encoder.close();
        printstream.print("testowy_napis");
        assertFalse("Stream shouldn't contains testowy_napis.", output.toString().contains("testowy_napis"));
    }
}
