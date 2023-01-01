public abstract class Node {

    public void SetNext(Node newNextNode) {
    }

    public void SetPrevious(Node newPrevNode) {
    }

    public abstract boolean IsEmpty();

    public abstract Node Dequeue(ObjectHolder result);
}
