//CSC2001F Lecture Slides 2019
//All the same as lecture slides except for addition of the opCount variable in the find method (see below)

public class BinarySearchTree<PowerUser extends Comparable<? super PowerUser>> extends BinaryTree<PowerUser> {

    public int opCount = 0;

    public void insert ( PowerUser d ){
        if (root == null)
            root = new BinaryTreeNode<PowerUser> (d, null, null);
        else
            insert (d, root);
    }

    public void insert (PowerUser d, BinaryTreeNode<PowerUser> node){
        if (d.compareTo (node.data) <= 0){
            if (node.left == null)
                node.left = new BinaryTreeNode<PowerUser> (d, null, null);
            else
                insert (d, node.left);
        }
        else{
            if (node.right == null)
                node.right = new BinaryTreeNode<PowerUser> (d, null, null);
            else
                insert (d, node.right);
        }
    }

    public BinaryTreeNode<PowerUser> find (PowerUser d){
        if (root == null)
            return null;
        else return find (d, root);
    }

    public BinaryTreeNode<PowerUser> find (PowerUser d, BinaryTreeNode<PowerUser> node){
        if (d.compareTo (node.data) == 0) {
            opCount++; //opCount is used to determine the total number of operations used by the BST to find the element
            return node;
        }
        else if (d.compareTo (node.data) < 0) {
            opCount++;
            return (node.left == null) ? null : find(d, node.left);
        }
        else {
            opCount++;
            return (node.right == null) ? null : find(d, node.right);
        }
    }

    public void delete ( PowerUser d ){
        root = delete (d, root);
    }

    public BinaryTreeNode<PowerUser> delete ( PowerUser d, BinaryTreeNode<PowerUser> node ){
        if (node == null)
            return null;
        if (d.compareTo (node.data) < 0)
            node.left = delete (d, node.left);
        else if (d.compareTo (node.data) > 0)
            node.right = delete (d, node.right);
        else if (node.left != null && node.right != null ){
            node.data = findMin (node.right).data;
            node.right = removeMin (node.right);
        }
        else
            if (node.left != null)
                node = node.left;
            else
                node = node.right; return node;

    }

    public BinaryTreeNode<PowerUser> findMin ( BinaryTreeNode<PowerUser> node ){
        if (node != null)
            while (node.left != null)
                node = node.left;
        return node;
    }

    public BinaryTreeNode<PowerUser> removeMin ( BinaryTreeNode<PowerUser> node ){
        if (node == null)
            return null;
        else if (node.left != null){
            node.left = removeMin (node.left);
            return node;
        }
        else
            return node.right;
    }
}
