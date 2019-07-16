//CSC2001F Lecture Slides 2019
public class BTQueueNode<PowerUser>
{
    BinaryTreeNode<PowerUser> node;
    BTQueueNode<PowerUser> next;

    public BTQueueNode ( BinaryTreeNode<PowerUser> n, BTQueueNode<PowerUser> nxt )
    {
        node = n;
        next = nxt;
    }
}
