public class HeadNode extends Node {

    private Node _next;

    public HeadNode() {
        super();
    }

    public Node Dequeue(ObjectHolder result) {
        _next = _next.Dequeue(result);
        _next.SetPrevious(this);
        return this;
    }

    public boolean IsEmpty() {
        return _next.IsEmpty();
    }

    public void SetNext(Node newNextNode) {
        _next = newNextNode;
    }
}
