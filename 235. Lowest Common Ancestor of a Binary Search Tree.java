/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// version 2: specifically for BST( iteration )
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        int max = Math.max( p.val, q.val);
        int min = Math.min( p.val, q.val );
        
        return BS( root, max, min );
    }
    
    private TreeNode BS( TreeNode root, int max, int min ){
        while ( root.val != max  &&  root.val != min ) {
            if( root.val > max ) root = root.left;
            else if( root.val < min ) root = root.right;
            else return root;
        } 
        return root;
    }
}


// version 3: specifically for BST( recursion )
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right,p,q);
        }
        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left,p,q);
        }
        else {
            return root;
        }
    }
}

/*
// version 1: not only for BST, for general tree structure
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
*/



