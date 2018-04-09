package workshop.ds.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TreeView {
    public static int MAX_LEVEL = 0;

    public static void main(String[] args) {
        TreeView tt = new TreeView();
        TreeNode root = tt.new TreeNode(1);
        root.left = tt.new TreeNode(2);
        root.right = tt.new TreeNode(3);
        root.left.left = tt.new TreeNode(4);
        root.left.right = tt.new TreeNode(5);
        root.right.left = tt.new TreeNode(6);
        root.right.right = tt.new TreeNode(7);

        tt.leftViewIterative(root);
        System.out.println("");
        MAX_LEVEL = 0;
        tt.leftViewRecursive(root, 1);
        System.out.println("");
        tt.rightViewIterative(root);
        System.out.println();
        MAX_LEVEL = 0;
        tt.rightViewRecursive(root, 1);
        System.out.println("");
        Map<Integer, Integer> map = new TreeMap<>();
        tt.topView(root, map, 0);
        map.entrySet().forEach(x -> System.out.print(x.getValue() + "\t"));
    }

    public void topView(TreeNode root, Map<Integer, Integer> map, int hd) {
        if (root == null)
            return;
        if (!map.containsKey(hd)) {
            map.put(hd, root.data);
        }
        topView(root.left, map, hd - 1);
        topView(root.right, map, hd + 1);
    }

    public void rightViewRecursive(TreeNode root, int level) {
        if (root == null)
            return;

        if (MAX_LEVEL < level) {
            System.out.print(root.data + "\t");
            MAX_LEVEL = level;
        }

        rightViewRecursive(root.right, level + 1);
        rightViewRecursive(root.left, level + 1);
    }

    public void rightViewIterative(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode temp = q.poll();
                if (i == n - 1)
                    System.out.print(temp.data + "\t");
                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);
            }
        }
    }

    public void leftViewRecursive(TreeNode root, int level) {
        if (root == null)
            return;

        if (MAX_LEVEL < level) {
            System.out.print(root.data + "\t");
            MAX_LEVEL = level;
        }
        leftViewRecursive(root.left, level + 1);
        leftViewRecursive(root.right, level + 1);
    }

    public void leftViewIterative(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode temp = q.poll();
                if (i == 0)
                    System.out.print(temp.data + "\t");
                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);
            }
        }
    }

    public class TreeNode {
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
