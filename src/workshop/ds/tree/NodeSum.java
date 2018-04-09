package workshop.ds.tree;

public class NodeSum {

    public static void main(String[] args) {
	NodeSum tt = new NodeSum();
	TreeNode root = tt.new TreeNode(1);
	root.left = tt.new TreeNode(2);
	root.right = tt.new TreeNode(3);
	root.left.left = tt.new TreeNode(4);
	root.left.right = tt.new TreeNode(5);
	root.right.left = tt.new TreeNode(6);
	root.right.right = tt.new TreeNode(7);
	root.right.left.right = tt.new TreeNode(8);
	root.right.left.left = tt.new TreeNode(9);
	
	System.out.println("Node Sum: " + tt.nodeSum(root));
    }
    
    public int nodeSum(TreeNode root){
	if(root == null)
	    return 0;
	
	return root.data + nodeSum(root.left) + nodeSum(root.right);
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
