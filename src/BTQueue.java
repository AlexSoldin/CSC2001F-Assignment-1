//CSC2001F Lecture Slides 2019
public class BTQueue<PowerUser> {

    BTQueueNode<PowerUser> head;
    BTQueueNode<PowerUser> tail;

    public BTQueue ()
    {
        head = null;
        tail = null;
    }

    public BinaryTreeNode<PowerUser> getNext ()
    {
        if (head == null)
            return null;
        BTQueueNode<PowerUser> qnode = head;
        head = head.next;
        if ( head == null )
            tail = null;
        return qnode.node;
    }

    public void enQueue ( BinaryTreeNode<PowerUser> node )
    {
        if (tail == null)
        {
            tail = new BTQueueNode<PowerUser> (node, null);
            head = tail;
        }
        else
        {
            tail.next = new BTQueueNode<PowerUser> (node, null);
            tail = tail.next;
        }
    }
}
