class SortedList<T extends Comparable<T>> extends MyLinkedList<T> {

    public void insert(T x) {
        Node<T> node = first;
        while (node != null && x.compareTo(node.item) > 0) node = node.next;
        if (node == null) add(x); else {
            Node<T> newnode = new Node<T>(x);
            if (node.prev == null) first = newnode; else node.prev.next = newnode;
            newnode.next = node;
            newnode.prev = node.prev;
            node.prev = newnode;
        }
    }
}
