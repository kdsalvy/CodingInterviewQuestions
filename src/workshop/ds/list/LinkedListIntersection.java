package workshop.ds.list;

public class LinkedListIntersection {

    public static void main(String[] args) {
        LinkedListIntersection listIntersection = new LinkedListIntersection();

        // Create 2 Linked Lists
        LinkedList list1 = listIntersection.new LinkedList();
        for (int i = 1; i <= 10; i++)
            list1.add(i);
        LinkedList list2 = listIntersection.new LinkedList();
        for (int i = 11; i <= 13; i++)
            list2.add(i);

        // Create Intersection
        int i = 1;
        Node temp = list1.head;
        while (i <= 3) {
            temp = temp.next;
            i++;
        }

        Node temp2 = list2.head;
        while (temp2.next != null)
            temp2 = temp2.next;
        temp2.next = temp;

        // Get the intersection point
        Node intersectionPoint = listIntersection.detectAndFindIntersectionPoint(list1, list2);
        if (intersectionPoint == null)
            System.out.println("No Intersection Found");
        else
            System.out.println("Intersection point: " + intersectionPoint.data);
    }

    public Node detectAndFindIntersectionPoint(LinkedList list1, LinkedList list2) {
        // join the lists
        Node temp = list1.head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = list2.head;
        // if intersection is there loop will be formed
        // if present, detect the intersection points
        return findLoopStartingPoint(list1.head);
    }

    public Node findLoopStartingPoint(Node node) {
        if (node == null || node.next == null) {
            return null;
        }

        Node slow = node;
        Node fast = node;

        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (fast != null && fast != slow);

        Node result = null;

        /* If loop exists */
        if (slow == fast) {
            slow = node;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            /* since fast->next is the looping point */
            fast.next = null; /* remove loop */
            result = fast;
        }
        return result;
    }

    class LinkedList {
        Node head;

        public void add(int data) {
            if (head == null)
                head = new Node(data);
            else {
                Node temp = head;
                while (temp.next != null)
                    temp = temp.next;
                temp.next = new Node(data);
            }
        }
    }

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
