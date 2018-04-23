package gs.interview;

public class CustomSortedDataStructure {
    class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
        }

        public String toString() {
            return data + " --> " + next;
        }
    }

    Node head;

    public void insert(int data) {
        if (head == null || data < head.data) {
            Node temp = head;
            head = new Node(data);
            head.next = temp;
        } else {
            Node temp = head;
            Node prev = null;
            while (temp.next != null){
                if(temp.data > data) {
                    Node next = temp.next;
                    temp.next = new Node(data);
                    temp.next.next = next;
                    break;
                }
                if(temp.data < data) {
                    prev.next = new Node(data);
                    prev.next.next = temp;
                    break;
                }
                temp = temp.next;
            }
            Node newNode = new Node(data);
            prev.next = newNode;
            prev = prev.next;
            prev.next = temp;
        }
    }

    public static void main(String[] args) {
        CustomSortedDataStructure csds = new CustomSortedDataStructure();
        csds.insert(3);
        csds.insert(2);
        csds.insert(9);
        csds.insert(5);
        csds.insert(1);
        csds.insert(4);
        csds.insert(8);

        System.out.print(csds.toString());
    }
}
