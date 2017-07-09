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
    // version 1
    // main idea:
    // -- see if a Node is ancestor of p or q
    // ----- if YES, return p/q (to prove it)
    // ----- if NO , return null
    // -- if a Node has left child ( ancestor of p )
    // -- meanwhile,has right child( ancestro of q )
    // -- we could say it is the shortest ancestor
    // -- if a Node only has left child( or right child ) as ancestor, then we have to wait to see the status of its right child
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root;
        // the line below has many functionalities:
        // 1 - if left and right has one null, return non-null
        // 2 - if left and right are both null, return null
        return left != null ? left : right;
    }
    
}


