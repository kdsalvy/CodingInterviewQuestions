package workshop.ds.list;

public class LinkedListLoop {


    public static void main(String[] args) {
        LinkedListLoop lil = new LinkedListLoop();
        LinkedList list = lil.new LinkedList();
       for(int i = 1; i <= 6; i++){
           list.add(i);
       }

       list = lil.linkLastAndMiddleNode(list);
       Node loopStartingNode = lil.getStartingNodeOfLoop(list);
       loopStartingNode.next = null;
       System.out.println(loopStartingNode);
    }

    public LinkedList linkLastAndMiddleNode(LinkedList list) {
        if (list.head == null)
            return list;
        Node mNode = getMiddleNode(list);
        Node lNode = getLastNode(list);

        lNode.next = mNode;
        return list;
    }

    private Node getLastNode(LinkedList list) {
        Node p = list.head;
        while (p.next != null)
            p = p.next;
        return p;
    }

    private Node getMiddleNode(LinkedList list) {
        Node p1 = list.head.next;
        Node p2 = list.head;

        while (p1 != null) {
            p1 = p1.next;
            if (p1 != null) {
                p2 = p2.next;
                p1 = p1.next;
            }
        }
        return p2;
    }

    public Node getStartingNodeOfLoop(LinkedList list) {
        if (list.head == null || list.head.next == null)
            return list.head;
        Node n1 = list.head;
        Node n2 = list.head;
        Node n3 = list.head;

        n1 = n1.next;
        n2 = n2.next.next;

        // n1 = n2 will confirm that the loop exists in the list
        while (n1 != null && n2 != null && n1 != n2) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        // n2 and n3 will reach the meeting point together as if m is the distance from head till
        // start of the loop, k is the distance from start of the loop till the meeting point and
        // n is the length of the loop then m+k will be a multiple of n if we solve the equation
        // distance travelled by fast pointer = 2 * distance travelled by slow pointer
        if(n1 == n2) {
            while (n3.next != n1.next) {
                n3 = n3.next;
                n1 = n1.next;
            }
        }

        if(n3.next == n1.next)
            return n3.next;
        else
            return null;
    }

    class LinkedList{
        Node head;

        public void add(int data){
            if(head == null)
                head = new Node(data);
            else {
                Node temp = head;
                while(temp.next != null)
                    temp = temp.next;
                temp.next = new Node(data);
            }
        }

        @Override
        public String toString(){
            return head.toString();
        }
    }

    class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
        }

        @Override
        public String toString(){
            return "[ " + data + " ] -> " + next;
        }
    }
}
