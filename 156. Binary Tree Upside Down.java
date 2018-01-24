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
// version 1: Memory Limit Exceeded!
// O(N)time, O(N) : stack
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        
        if( root == null )
            return null;
        
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while( root.left != null ){
            stack.push( root.right );
            stack.push( root.left );
            root = root.left;
        }

        root = stack.pop();
        TreeNode cur = root;
        while( !stack.isEmpty() ){
            cur.left = stack.pop();
            cur.right = stack.pop();
            cur = cur.right;
        }
        return root;
    }
}
*/


// version 2: 从下往上接子节点，直到找不到父节点 : O(N)time  O(1)space
/*                     null
                      /
            prev     1-----null
                   /  -\- 
          curr    2-----3  temp : track prev' right
                /  -\-
        next   4-----5 
*/

public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode tmp = null;    // track prev' right
        TreeNode next = null;
        
        while(cur != null){
            next = cur.left;
            // why we need tmp to keep the previous right child
            cur.left = tmp;
            tmp = cur.right;
            
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}


/*
// version 3: Recursive
// key Idea: 把左子树继续颠倒，颠倒完后，原来的那个左孩子的 左右孩子指针分别指向 原来的根节点 以及 原来的右兄弟节点 即可。
// Complexity: O(N)time, O(N)space stack

public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {  
        if (root == null)   // edge case, not terminating case
            return null;  
        TreeNode parent = root, left = root.left, right = root.right;  
        if (left != null) {  
            TreeNode ret = upsideDownBinaryTree(left);  
            left.left = right;  
            left.right = parent;  
            return ret;  // general case
        } 
        
        return root;     // root.left == null, return itself -> base case
    }
}
*/
