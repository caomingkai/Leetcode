/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// two patterns of recursion variable ( example of inorder recursion ) :
// 1 - global varible : would change for each call of recursion method
// 2 - parameter variable : only change for its following recursion call ( left , right )

public class Solution {
    String commonPath = "";
    List<String> path = new ArrayList<>();
    
    public List<String> binaryTreePaths(TreeNode root) {
        inorder( root, commonPath );
        return path;
    }
    
    private void inorder( TreeNode r, String commonPath ){
        if( r == null ) return;
        if( r.left == null && r.right == null ){
            path.add( commonPath + r.val );
        }else{
            commonPath += r.val + "->";
            inorder( r.left , commonPath );
            inorder( r.right, commonPath );
        }
    } 
}