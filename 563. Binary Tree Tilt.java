/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int cnt = 0;     // global variable
    public int findTilt(TreeNode root) {
        sum( root ); // update current tree into tree carraying value of sum( itself + leftchild + rightchild )
        DFT( root ); 
        return cnt;
    }
    
    // create another tree, in which a node the value equal to sum of ( itself + leftchild + rightchild )
    private int sum( TreeNode n ){
        if( n == null ) return 0;
        n.val = n.val + sum( n.left ) + sum( n.right); // update node value
        return n.val;
    }
    
    // actually the DFS is only about following lines
    // 1. if( r == null ) return;
    // 2. DFT( r.left );
    // 3. DFT( r.right);
    // it is the most basic lines for a DFS
    private void DFT( TreeNode r ){
        if( r == null ) return;
        if( r.left != null && r.right != null )
            cnt += Math.abs( r.left.val - r.right.val );
        else if( r.left != null && r.right == null )
            cnt += Math.abs(r.left.val);
        else if( r.left == null && r.right != null )
            cnt += Math.abs(r.right.val);
        DFT( r.left );
        DFT( r.right);
    }
}

/*
// version 2: integrate 'sum' and 'DFS' in only one method
public class Solution {
    int result = 0;
    
    public int findTilt(TreeNode root) {
        postOrder(root);
        return result;
    }
    
    private int postOrder(TreeNode root) {
        if (root == null) return 0;
        
        int left = postOrder(root.left);
        int right = postOrder(root.right);
        
        result += Math.abs(left - right);
        
        return left + right + root.val;
    }
}
*/