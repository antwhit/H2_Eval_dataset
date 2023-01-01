import java.util.Vector;

public class TreeElement {

    public static final int NODE = 0;

    public static final int LEAF = 1;

    private String name;

    private String users;

    private String description;

    private boolean node;

    private boolean expanded;

    private boolean selected;

    private TreeElement[] nodes = new TreeElement[0];

    private TreeElement[] leaves = new TreeElement[0];

    public TreeElement(int type) {
        this(type, "");
    }

    public TreeElement(int type, String name) {
        this(type, name, "", "");
    }

    public TreeElement(int type, String name, String u, String desc) {
        this.node = TreeElement.NODE == type;
        this.name = name;
        this.users = u;
        this.description = desc;
    }

    public String getName() {
        return name;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String u) {
        this.users = u;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public boolean isNode() {
        return node;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean hasLeaves() {
        return (leaves.length > 0);
    }

    public boolean hasNodes() {
        return (nodes.length > 0);
    }

    public void addNodes(String[] newnodes) {
        TreeElement[] tmp = new TreeElement[nodes.length + newnodes.length];
        for (int i = 0; i < nodes.length; i++) {
            tmp[i] = nodes[i];
        }
        for (int i = nodes.length; i < nodes.length + newnodes.length; i++) {
            tmp[i] = new TreeElement(TreeElement.NODE, newnodes[i - nodes.length]);
        }
        nodes = tmp;
    }

    public void addLeaves(String[] newleaves) {
        TreeElement[] tmp = new TreeElement[leaves.length + newleaves.length];
        for (int i = 0; i < leaves.length; i++) {
            tmp[i] = leaves[i];
        }
        for (int i = leaves.length; i < leaves.length + newleaves.length; i++) {
            tmp[i] = new TreeElement(TreeElement.LEAF, newleaves[i - leaves.length]);
        }
        leaves = tmp;
    }

    public void addNode(String newnode) {
        TreeElement[] tmp = new TreeElement[nodes.length + 1];
        for (int i = 0; i < nodes.length; i++) {
            tmp[i] = nodes[i];
        }
        tmp[nodes.length] = new TreeElement(TreeElement.NODE, newnode);
        nodes = tmp;
    }

    public void addLeaf(String newleaf) {
        TreeElement[] tmp = new TreeElement[leaves.length + 1];
        for (int i = 0; i < leaves.length; i++) {
            tmp[i] = leaves[i];
        }
        tmp[leaves.length] = new TreeElement(TreeElement.LEAF, newleaf);
        leaves = tmp;
    }

    public TreeElement[] getAllNodes() {
        return nodes;
    }

    public TreeElement[] getAllLeaves() {
        return leaves;
    }

    public String[] getAllNodeNames() {
        String[] out = new String[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            out[i] = nodes[i].getName();
        }
        return out;
    }

    public String[] getAllLeaveNames() {
        String[] out = new String[leaves.length];
        for (int i = 0; i < leaves.length; i++) {
            out[i] = leaves[i].getName();
        }
        return out;
    }

    public TreeElement getNode(String name) {
        int i = 0;
        while ((i < nodes.length) && (!nodes[i].getName().equals(name))) {
            i++;
        }
        if (i == nodes.length) {
            return new TreeElement(TreeElement.NODE, "empty");
        }
        return (TreeElement) nodes[i];
    }

    public TreeElement getLeaf(String name) {
        int i = 0;
        while ((i < leaves.length) && (!leaves[i].getName().equals(name))) {
            i++;
        }
        if (i == leaves.length) {
            return new TreeElement(TreeElement.LEAF, "empty");
        }
        return (TreeElement) leaves[i];
    }
}
