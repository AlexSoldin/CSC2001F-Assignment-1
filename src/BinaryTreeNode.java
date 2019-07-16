//CSC2001F Lecture Slides 2019
public class BinaryTreeNode<PowerUser> {
    PowerUser data;
    BinaryTreeNode<PowerUser> left;
    BinaryTreeNode<PowerUser> right;

    public BinaryTreeNode ( PowerUser d, BinaryTreeNode<PowerUser> l, BinaryTreeNode<PowerUser> r ){
        data = d;
        left = l;
        right = r;
    }

    public PowerUser getData(){
        return data;
    }

    BinaryTreeNode<PowerUser> getLeft () { return left; }
    BinaryTreeNode<PowerUser> getRight () { return right; }

}





