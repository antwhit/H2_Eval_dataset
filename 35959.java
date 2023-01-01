import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class MyTreeModel extends DefaultTreeModel implements TreeModel {

    public boolean isLeaf(Object n) {
        Object userObj = ((DefaultMutableTreeNode) n).getUserObject();
        if (userObj.getClass() == java.lang.String.class) return false; else return ((node) userObj).isLeafP;
    }

    public void addTreeModelListener(TreeModelListener l) {
        super.addTreeModelListener(l);
    }

    public Object getChild(Object parent, int index) {
        return super.getChild(parent, index);
    }

    public int getChildCount(Object parent) {
        return super.getChildCount(parent);
    }

    public int getIndexOfChild(Object parent, Object child) {
        return super.getIndexOfChild(parent, child);
    }

    public Object getRoot() {
        return super.getRoot();
    }

    public void removeTreeModelListener(TreeModelListener l) {
        super.removeTreeModelListener(l);
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
        super.valueForPathChanged(path, newValue);
    }

    public MyTreeModel(TreeNode root) {
        super(root);
    }

    public MyTreeModel(TreeNode root, boolean asksAllowsChildren) {
        super(root, asksAllowsChildren);
    }
}
