package workshop.ds.list;

public class BinarySearchTreeDeleteNode {

    public static void main(String[] args) {
	BinarySearchTreeDeleteNode tt = new BinarySearchTreeDeleteNode();
	TreeNode root = tt.new TreeNode(4);
	root.left = tt.new TreeNode(3);
	root.right = tt.new TreeNode(6);
	root.left.left = tt.new TreeNode(1);
	root.left.right = tt.new TreeNode(2);
	root.right.left = tt.new TreeNode(5);
	root.right.right = tt.new TreeNode(7);
	
	System.out.println(tt.deleteNode(root, 5));
    }
    
    public TreeNode deleteNode(TreeNode root, int delNo) {
	if(root == null)
	    return root;
	
	if(delNo < root.data)
	    root.left = deleteNode(root.left, delNo);
	else if(delNo > root.data)
	    root.right = deleteNode(root.right, delNo);
	else{
	    // if root is having 1 childNode or no child node
	    if(root.right == null)
		return root.left;
	    else if(root.left == null)
		return root.right;
	    
	    // if root is having two children
	    root.data = getMin(root.right);
	    
	    // delete the minRec
	    deleteNode(root.right, root.data);
	}
	return root;
    }

    private int getMin(TreeNode right) {
	if(right.left == null)
	    return right.data;
	return getMin(right.left);
    }

    class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data){
	    this.data = data;
	    this.left = this.right = null;
	}
	
	@Override
	public String toString(){
	    return "[ " + left + " " + data + " " + right + " ]";
	}
    }

}
