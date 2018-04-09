package workshop.ds.list;

/**
 * LinkedList pairwise swap
 */
public class LinkedListPairwiseSwap
{
	public static void main (String[] args) throws Exception
	{
		LinkedListPairwiseSwap ideone = new LinkedListPairwiseSwap();
		LinkedList list = ideone.new LinkedList();
		for(int i = 1; i <= 5; i++){
			list.add(i);
		}
		
		list.head = ideone.reverseListPairwiseIterative(list.head);
		
		list.head = ideone.reverseListPairwiseRecursive(list.head);
		
		Node temp = list.head;
		while(temp!= null){
			System.out.print(temp.data + "\t");
			temp = temp.next;
		}
	}
	
	public Node reverseListPairwiseIterative(Node head){
		if(head == null || head.next == null)
			return head;
			
		Node prev = head;
		Node curr = head.next;
		
		head = curr;
		while(true){
			// Pair swap
			Node next = curr.next;
			curr.next = prev;
			
			// Update pointers
			if(next == null || next.next == null){
				prev.next = next;
				break;
			}
			
			prev.next = next.next;
			prev = next;
			curr = prev.next;
		}
		return head;
	}
	
	public Node reverseListPairwiseRecursive(Node head){
	    if(head == null || head.next == null)
		return head;
	    
	    // store the first two nodes in prev and curr variables
	    Node prev = head;
	    Node curr = head.next;
	    
	    // get the head of remaining list with is already swapped pairwise
	    Node next = reverseListPairwiseRecursive(curr.next);
	    
	    // join current node to prev and prev to the head of remaining list
	    curr.next = prev;
	    prev.next = next;
	  	    
	    return curr;
	}
	
	class LinkedList{
		Node head;
		
		public void add(int data){
			if(head == null){
				head = new Node(data);
				return;
			}
			
			Node temp = head;
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next = new Node(data);
		}
	}
	
	class Node{
		int data;
		Node next;
		
		public Node(int data){
			this.data = data;
			this.next = null;
		}
	}
}
