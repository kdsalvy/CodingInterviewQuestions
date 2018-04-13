package workshop.ds.tree;

public class TreeNodeHeight {

    public static void main(String[] args) {
        TreeNode keyNode = null;

        TreeNodeHeight tt = new TreeNodeHeight();
        TreeNode root = tt.new TreeNode(1);
        root.left = tt.new TreeNode(2);
        root.right = tt.new TreeNode(3);
        root.left.left = tt.new TreeNode(4);
        root.left.right = tt.new TreeNode(5);
        keyNode = tt.new TreeNode(6);
        root.right.left = keyNode;
        root.right.right = tt.new TreeNode(7);
        root.right.left.right = tt.new TreeNode(8);
        root.right.left.left = tt.new TreeNode(9);

        System.out.println("Node Height: " + tt.nodeHeight(root, keyNode, 0));
    }

    public int nodeHeight(TreeNode root, TreeNode node, int height) {
        if (root == null)
            return 0;
        if (root == node)
            return height;

        height = nodeHeight(root.left, node, height + 1);
        if (height != 0) return height;

        return nodeHeight(root.right, node, height + 1);
    }

    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

}
