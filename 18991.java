import sjp.hg.*;

public class PrintTodo extends VisitorAdapter implements Apply.Processor {

    public Element process(Element elt) {
        elt.accept(this);
        return null;
    }

    public void visit(Comment comment) {
        String text = comment.getValue();
        if (text.startsWith("TODO")) {
            System.out.println(text);
        }
    }
}
