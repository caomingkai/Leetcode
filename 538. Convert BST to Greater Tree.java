/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// version 1;
public class Solution {

    int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }
    
    public void convert(TreeNode cur) {
        if (cur == null) return;
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }
    
} 

/*
// version 2
public class Solution {
    public TreeNode convertBST(TreeNode root) {
        if( root == null ) return null;
        root.left.val = root.val + sum( root.right);
        root.val = root.val + sum( root.right );
        root.right.val = root.right.val + sum( root.right.right );
        return root;
    }
    
    private int sum( TreeNode r ) {
        int s = 0;
        if( r != null ){
            s += r.val + sum( r.left ) + sum( r.right );
        }
        return s;
    }
}
*/