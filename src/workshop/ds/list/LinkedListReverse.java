package workshop.ds.list;

public class LinkedListReverse {

    public static void main(String[] args){
        LinkedListReverse linkedlistReverse = new LinkedListReverse();
        LinkedList list = linkedlistReverse.new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println("Original List");
        linkedlistReverse.printList(list);
        System.out.println("\n Reversed List - using Iteration");
        linkedlistReverse.reverseIterative(list);
        linkedlistReverse.printList(list);
        System.out.println("\n Reversed List - using recursion");
        list.head = linkedlistReverse.reverseRecursive(list.head);
        linkedlistReverse.printList(list);
    }

    public Node reverseRecursive(Node current){
        if(current.next == null)
            return current;
        Node node = reverseRecursive(current.next);
        Node next = current.next;
        next.next = current;
        current.next = null;
        return node;
    }

    public void reverseIterative(LinkedList list){
        if(list.head == null)
            return;
        Node prev = null;
        Node next = null;
        Node current = list.head;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        list.head = prev;
    }

    private void printList(LinkedList reversedList) {
        Node pointer = reversedList.head;
        while(pointer != null){
            System.out.print(pointer.data + "\t");
            pointer = pointer.next;
        }
    }

    class LinkedList{
        Node head;

        public void add(int data){
            if(head == null){
                head = new Node(data);
            } else {
                Node temp = head;
                while(temp.next != null)
                    temp = temp.next;
                temp.next = new Node(data);
            }
        }
    }

    class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
        }
    }
}

