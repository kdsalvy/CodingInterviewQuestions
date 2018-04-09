package workshop.ds.tree;

public class NodeCount {

    public static void main(String[] args) {
	NodeCount tt = new NodeCount();
	TreeNode root = tt.new TreeNode(1);
	root.left = tt.new TreeNode(2);
	root.right = tt.new TreeNode(3);
	root.left.left = tt.new TreeNode(4);
	root.left.right = tt.new TreeNode(5);
	root.right.left = tt.new TreeNode(6);
	root.right.right = tt.new TreeNode(7);
	root.right.left.right = tt.new TreeNode(8);
	root.right.left.left = tt.new TreeNode(9);
	
	System.out.println("Node Count: " + tt.nodeCount(root));
    }
    
    public int nodeCount(TreeNode node){
	if(node == null)
	    return 0;
	
	return 1 + nodeCount(node.left) + nodeCount(node.right);
    }

    class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data){
	    this.data = data;
	    this.left = this.right = null;
	}
    }
}
