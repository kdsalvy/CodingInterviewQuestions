package workshop.ds.tree;

public class TreeHeight {

    public static void main(String[] args) {
	TreeHeight tt = new TreeHeight();
	TreeNode root = tt.new TreeNode(1);
	root.left = tt.new TreeNode(2);
	root.right = tt.new TreeNode(3);
	root.left.left = tt.new TreeNode(4);
	root.left.right = tt.new TreeNode(5);
	root.right.left = tt.new TreeNode(6);
	root.right.right = tt.new TreeNode(7);
	root.right.left.right = tt.new TreeNode(8);
	root.right.left.left = tt.new TreeNode(9);
	
	System.out.println("Height of the tree: " + tt.height(root));
    }
    
    public int height(TreeNode node){
	if(node == null)
	    return 0;
	return 1 + Math.max(height(node.left), height(node.right));
    }
    
    public class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data){
	    this.data = data;
	    this.left = null;
	    this.right = null;
	}
    }

}
