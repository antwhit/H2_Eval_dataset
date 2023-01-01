import java.net.*;

public class LinkedListCli {

    NodeCli head, tail, temp, retur;

    public LinkedListCli() {
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void add(Socket client, String name) {
        if (head == null) {
            head = new NodeCli();
            head.client = client;
            head.name = name;
            tail = head;
        } else {
            temp = new NodeCli();
            temp.client = client;
            temp.name = name;
            tail.next = temp;
            tail = tail.next;
        }
    }

    public int size() {
        NodeCli temp2 = head;
        int number = 0;
        while (temp2 != null) {
            number++;
            temp2 = temp2.next;
        }
        return number;
    }

    public ClientObj[] pop() {
        NodeCli temp2 = head;
        ClientObj[] list = new ClientObj[size()];
        for (int i = 0; i < list.length; i++) {
            ClientObj temp = new ClientObj();
            temp.client = temp2.client;
            temp.name = temp2.name;
            list[i] = temp;
            temp2 = temp2.next;
        }
        return list;
    }
}

class NodeCli {

    Socket client;

    String name;

    NodeCli next;
}
