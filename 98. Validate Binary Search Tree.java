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
    
    ArrayList<Integer> list = new ArrayList<>();
    
    public boolean isValidBST(TreeNode root) {
        
        if( root == null ) return true;
        inorder( root );
        
        for( int i = 0; i < list.size()-1; i++ ){
            if( list.get(i) >= list.get(i+1) )
                return false;
        }
        return true;
    }
    
    private void inorder( TreeNode root ){
        if( root != null ){
            inorder( root.left );
            list.add( root.val );
            inorder( root.right );
        }
    }
}

/*
// version 2

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}
*/