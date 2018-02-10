/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// version 1: iterative inorder traversal，维护一个prev node
//            inorder遍历的过程中，curr node值 与 prev node的值进行比较
class Solution {
    public boolean isValidBST(TreeNode root) {
       if (root == null) return true;
       Stack<TreeNode> stack = new Stack<>();
       TreeNode pre = null;
       while (root != null || !stack.isEmpty()) {
          while (root != null) {
             stack.push(root);
             root = root.left;
          }
          root = stack.pop();
          if(pre != null && root.val <= pre.val) return false;
          pre = root;
          root = root.right;
       }
       return true;
    }

}

/*
// version 2: recursion 
class Solution {
    
    public boolean isValidBST(TreeNode root) {
        if( root == null ) return true;
        
        if( !compare(root.left, root.val, true ) ) return false;  // 左子树比root.val要小
        if( !compare(root.right, root.val, false ) ) return false;// 右子树比root.val要大
        if( !isValidBST(root.left) ) return false;                // 左子树 也满足这个条件么？
        if( !isValidBST(root.right) ) return false;               // 右子树 也满足这个条件么？
        
        return true;                                            // 上述条件都满足，一定是valid
    }
    
    // 
    private boolean compare( TreeNode n, int v, boolean f ){
        if( n == null ) return true;
        if(f){
            if(n.val >= v) return false;
            if( !compare(n.left, v, f) ) return false;
            if( !compare(n.right, v, f) ) return false;
        }else{
            if(n.val <= v) return false;
            if( !compare(n.left, v, f) ) return false;
            if( !compare(n.right, v, f) ) return false;
        }
        return true;
    }
}
*/


/*

// version 2:  BST --> list 
//           因为 DFS inorder traversal 具有“还原顺序”功能，
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
*/