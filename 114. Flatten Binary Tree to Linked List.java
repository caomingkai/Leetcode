/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
class Solution {
    public void flatten(TreeNode root) {
        if( root != null)
            helper(root);
    }
    
    
    private TreeNode[] helper(TreeNode root){
        
        TreeNode[] res = new TreeNode[2];
        
        if( root.left != null && root.right != null ){
            TreeNode[] leftRes = helper( root.left );
            TreeNode[] rightRes = helper( root.right );
        
            root.left = null;
            root.right = leftRes[0];
            leftRes[1].right = rightRes[0];

            res[0] = root;
            res[1] = rightRes[1];
        }else if( root.left == null && root.right != null){
            TreeNode[] rightRes = helper( root.right );
            res[0] = root;
            res[1] = rightRes[1];
        }else if( root.right == null && root.left != null){
            TreeNode[] leftRes = helper( root.left );
            
            root.left = null;
            root.right = leftRes[0];
            res[0] = root;
            res[1] = leftRes[1];
        }else{
            res[0] = root;
            res[1] = root;
        }
        
        return res;
    }
}
*/


/*

// version 2:
class Solution {
    public void flatten(TreeNode root) {
            if (root == null) return;

            TreeNode left = root.left;
            TreeNode right = root.right;

            root.left = null;

            flatten(left);
            flatten(right);

            root.right = left;
            TreeNode cur = root;
            while (cur.right != null) cur = cur.right;
            cur.right = right;
        }
}
*/

// version 3: easy-reading
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        TreeNode temp = root.left;
        while (temp != null && temp.right != null) {
            temp = temp.right;
        }
        
        flatten(root.right);
        if (temp != null) {
            temp.right = root.right;    
            root.right = root.left;
            root.left = null;
        }
    }
}