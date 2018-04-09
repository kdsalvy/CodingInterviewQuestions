package workshop.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TreeTraversal {

    public static void main(String[] args) {
	TreeTraversal tt = new TreeTraversal();
	TreeNode root = tt.new TreeNode(1);
	root.left = tt.new TreeNode(2);
	root.right = tt.new TreeNode(3);
	root.left.left = tt.new TreeNode(4);
	root.left.right = tt.new TreeNode(5);
	root.right.left = tt.new TreeNode(6);
	root.right.right = tt.new TreeNode(7);
	
	System.out.println("Inorder Traversal");
	tt.inorderTraversal(root);
	System.out.println("\nPreorder Traversal");
	tt.preorderTraversal(root);
	System.out.println("\nPostorder Traversal");
	tt.postorderTraversal(root);
	System.out.println("\nLevel Order Traversal");
	tt.levelorderTraversal(root);
	System.out.println("\nVertical Order Traversal");
	Map<Integer,List<Integer>> nodeMap = new TreeMap<>();
	tt.verticalorderTraversal(root, nodeMap, 0);
	for(Map.Entry<Integer, List<Integer>> entry : nodeMap.entrySet()){
	    entry.getValue().forEach(System.out::print);
	    System.out.print("\t");
	}

    }
    
    public void verticalorderTraversal(TreeNode node, Map<Integer,List<Integer>> nodeMap, int hd) {
	if(node == null)
	    return;
	List<Integer> nodeList = null;
	if(nodeMap.containsKey(hd))
	    nodeList = nodeMap.get(hd);
	else
	    nodeList = new ArrayList<>();
	nodeList.add(node.data);
	nodeMap.put(hd, nodeList);
	verticalorderTraversal(node.left, nodeMap, hd - 1);
	verticalorderTraversal(node.right, nodeMap, hd + 1);
    }

    public void levelorderTraversal(TreeNode root) {
	Queue<TreeNode> q = new LinkedList<>();
	q.offer(root);
	while(!q.isEmpty()){
	    TreeNode node = q.poll();
	    System.out.print(node.data + "\t");
	    if(node.left != null)
		q.offer(node.left);
	    if(node.right != null)
		q.offer(node.right);
	}
    }

    public void postorderTraversal(TreeNode root) {
	if(root == null)
	    return;
	
	postorderTraversal(root.left);
	postorderTraversal(root.right);
	System.out.print(root.data + "\t");
    }

    public void preorderTraversal(TreeNode root){
	if(root == null)
	    return;
	System.out.print(root.data + "\t");
	preorderTraversal(root.left);
	preorderTraversal(root.right);
    }
    
    public void inorderTraversal(TreeNode root){
	if(root == null)
	    return;
	inorderTraversal(root.left);
	System.out.print(root.data + "\t");
	inorderTraversal(root.right);
    }
    
    class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data){
	    this.data = data;
	    left = null;
	    right = null;
	}
	
	@Override
	public String toString(){
	    return "[" + left + " " + data + " " + right + "]";
	}
    }
}
