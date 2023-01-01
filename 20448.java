import java.util.*;

public class AstCatchStatement extends AstStatement {

    public AstBody body;

    public AstExpression condition;

    public AstCatchStatement() {
        super();
        body = null;
        condition = null;
    }
}
