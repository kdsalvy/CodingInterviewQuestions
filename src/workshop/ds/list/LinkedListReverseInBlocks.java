package workshop.ds.list;

public class LinkedListReverseInBlocks {

    public static void main(String[] args) {
	LinkedListReverseInBlocks llReverseInBlocks = new LinkedListReverseInBlocks();
	LinkedList list = llReverseInBlocks.new LinkedList();
	for(int i = 1; i <= 10; i++)
	    list.add(i);
	list.head = llReverseInBlocks.reverseListInBlocksOfKNodes(list.head, 3);
	
	Node temp = list.head;
	while(temp != null){
	    System.out.print(temp.data + "\t");
	    temp = temp.next;
	}
    }
    
    public Node reverseListInBlocksOfKNodes(Node node, int k){
	if(node == null || node.next == null)
	    return node;
	
	Node prev = null;
	Node curr = node;
	Node next = null;
	int count = 1;
	
	// reverse first k nodes
	while(count <= k &&  curr != null){
	    next = curr.next;
	    curr.next = prev;
	    prev = curr;
	    curr = next;
	    count++;
	}
	
	if(next != null)
	    node.next = reverseListInBlocksOfKNodes(next, k);
	
	return prev;
	
    }
    
    class Node{
	int data;
	Node next;
	
	public Node(int data){
	    this.data = data;
	    this.next = null;
	}
    }
    
    class LinkedList{
	Node head;
	
	public void add(int data){
	    if(head == null){
		head = new Node(data);
		return;
	    }
	    
	    Node temp = head;
	    while(temp.next != null)
		temp = temp.next;
	    
	    temp.next = new Node(data);
	}
    }

}
