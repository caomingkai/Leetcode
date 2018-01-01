/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    int res = 0;
    public int longestConsecutive(TreeNode root) {
        
        helper( root, 0 );
        return res;
    }
    
    private void helper( TreeNode root, int sum ){
        if( root == null )
            return;
        
        sum++;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        if( left != null && left.val == root.val+1 ){
            helper(left, sum);
        }else{
            res = res > sum ? res : sum;
            helper(left, 0);
        }
        
        if( right != null && right.val == root.val+1 ){
            helper(right, sum);
        }else{
            res = res > sum ? res : sum;
            helper(right, 0);
        }
        
    }
    
    
    
    /*
    // version 2
    private int maxLength = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root, null, 0);
        return maxLength;
    }

    private void dfs(TreeNode p, TreeNode parent, int length) {
        if (p == null) return;
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        maxLength = Math.max(maxLength, length);
        dfs(p.left, p, length);
        dfs(p.right, p, length);
    }
    */
}