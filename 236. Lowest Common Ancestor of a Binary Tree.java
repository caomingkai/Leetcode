/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// version_3  vs version_4 => why version_4 is not good ?

// version 1: recursion
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

// version 2: HashMap / HashSet / Stack
// 1- hashmap ( Node : Parent_Node) => O(1) retieve time & Find a way BACK to root!!!!
// 2- Hashset ( store set of node) => even though value might be the same, the left/right child are different
// 3- Stack => used to make BFS traversal, until HashMap contains both the p&q 
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}

// version 3: How to find path from Root to target Node ???
// return type: boolean  --> only store Node with true return value in Stack
// use parameter var to store Nodes
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> pStack = new Stack<TreeNode>();
        Stack<TreeNode> qStack = new Stack<TreeNode>();
        TreeNode target = null;
        if (findPath(root, p, pStack) && findPath(root, q, qStack)) {
            while (!pStack.isEmpty()) {
                TreeNode pNode = pStack.pop();
                if (qStack.contains(pNode))
                    target = pNode;
            }
        } 
        return target;
    }
    private boolean findPath(TreeNode root, TreeNode node, Stack<TreeNode> stack) {
        if (root == null)
            return false;
        if (root == node) {
            stack.push(root);
            return true;
        } else {
            if (findPath(root.left, node, stack) ||  findPath(root.right, node, stack)) {
                stack.push(root);
                return true;
            }
        }
        return false;
    }
}

// version 4:
// return type: void
// use global var to store Nodes
// since when pass Class as parameter variable, it is passed in address, not copy!!!
// this version is intended to use it as copy, so it has to manually copy
// version_3 is intended to pass address, like a global var, so it can save a lot space.
public class Solution {
    
    boolean flag = true;                         // indicate whether we should continue traverse
    List<TreeNode> pathP = new ArrayList<>();    // store path from root to p
    List<TreeNode> pathQ = new ArrayList<>();    // store path from root to q

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        inorderP( root, p, pathP );  // find out the path to p
        flag = true;                 // initialize flag for next call
        inorderQ( root, q, pathQ );  // find out the path to q


        int l = pathP.size() < pathQ.size() ? pathP.size() : pathQ.size();
        int i = 0;
        while( i < l && pathP.get(i) == pathQ.get(i) ){  // find out the 1st pair of different nodes fromo pathQ and pathP
            i++;
        }
        return pathP.get(i-1);
    }
    
    private void inorderP( TreeNode root, TreeNode t, List<TreeNode> path ){
        // have to copy the parameter path to p.
        // otherwise, the 1st call in the following two calls of inorder() would change path , leading to 2nd call fail.
        // applying copy, we can make sure that path would never be modified for both two calls
        List<TreeNode> p = new ArrayList<>();
        for( int i = 0; i < path.size(); i++ ){
            p.add( path.get(i));
        }

        if( flag == true){
            if( root == null ) return;
            if( root == t ){
                 p.add(root);
                for( int i = 0; i < p.size(); i++ ){
                    pathP.add( p.get(i));
                }
                flag = false;
                return;
            }else{
                p.add(root);
                inorderP( root.left,  t, p );
                inorderP( root.right, t, p );
            }
        }
    }

    private void inorderQ( TreeNode root, TreeNode t, List<TreeNode> path ){
        List<TreeNode> p = new ArrayList<>();
        for( int i = 0; i < path.size(); i++ ){
            p.add( path.get(i));
        }

        if( flag == true){
            if( root == null ) return;
            if( root == t ){
                p.add(root);
                for( int i = 0; i < p.size(); i++ ){
                    pathQ.add( p.get(i));
                }
                flag = false;
                return;
            }else{
                p.add(root);
                inorderQ( root.left,  t, p );
                inorderQ( root.right, t, p );
            }
        }
    }
}



