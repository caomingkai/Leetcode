/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// version 0 : recursion
// start point of the recursion should be root.left and root.right, not root!

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root==null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right){
        if(left==null || right==null)
            return left==right;
        if(left.val!=right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }
}

// version 1: treat root.left and root.right as two tree
// key point: maintain two Queue for both left tree and right tree
// when push node into queue, do opposite manipulations to left and right:
// left queue  ---> push node.left, then node.right
// right queue ---> push node.right, then node.left
// every time pop out the 1st node, push its children node into its own queue
// this way, the sequece for left and right are easy to compare.
/*
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if( root == null ) return true;
        
        LinkedList<TreeNode> lQueue = new LinkedList<>();
        LinkedList<TreeNode> rQueue = new LinkedList<>();
        
        if(root.left != null )  lQueue.add( root.left );
        if(root.right != null ) rQueue.add( root.right );
        
        while( lQueue.size() > 0 && rQueue.size() > 0){
            TreeNode l = lQueue.remove();
            TreeNode r = rQueue.remove();
            if( l.val != r.val ) return false;
            if(  l.left == null && r.right != null || 
                 l.left != null && r.right == null || 
                 l.right == null && r.left != null || 
                 l.right != null && r.left == null   )  return false;
            else{
                if( l.left != null ) lQueue.add(l.left);
                if( l.right != null) lQueue.add(l.right);
                if( r.right != null) rQueue.add(r.right);
                if( r.left != null ) rQueue.add(r.left);
            }
        }
        // 
        if( lQueue.size() == 0 && rQueue.size() == 0 ) return true;
        
        return false;
    }
}
*/

/*
// version 2: same idea with version 1, but use only one stack
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null)  return true;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode left, right;
        if(root.left!=null){
            if(root.right==null) return false;
            stack.push(root.left);
            stack.push(root.right);
        }
        else if(root.right!=null){
            return false;
        }

        while(!stack.empty()){
            if(stack.size()%2!=0)   return false;
            right = stack.pop();
            left = stack.pop();
            if(right.val!=left.val) return false;

            if(left.left!=null){
                if(right.right==null)   return false;
                stack.push(left.left);
                stack.push(right.right);
            }
            else if(right.right!=null){
                return false;
            }

            if(left.right!=null){
                if(right.left==null)   return false;
                stack.push(left.right);
                stack.push(right.left);
            }
            else if(right.left!=null){
                return false;
            }
        }

        return true;
    }
}
*/