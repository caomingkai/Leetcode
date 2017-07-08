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
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        return height(root)!=-1;

    }
    public int height(TreeNode node){
        if(node==null){
            return 0;
        }
        int lH=height(node.left);
        if(lH==-1){
            return -1;
        }
        int rH=height(node.right);
        if(rH==-1){
            return -1;
        }
        if(lH-rH<-1 || lH-rH>1){
            return -1;
        }
        return Math.max(lH,rH)+1;
    }


    /*
    // version 2
    private static final int UNBALANCED = -99;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getHeight(root) != UNBALANCED;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        if (l == UNBALANCED || r == UNBALANCED || Math.abs(l-r) > 1) {
            return UNBALANCED;
        }
        return 1 + Math.max(l,r);
    }
    */
}