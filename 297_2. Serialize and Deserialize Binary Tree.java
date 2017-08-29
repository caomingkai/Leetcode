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

// version 1: pre-order Recursive
// key point 1 :  The NULL value of leaf node has to be record, in order to avoid ambiguity.
// key point 2 :  NULL usually plays as a 'return' role in base case of recursion, to its caller
public class Codec {

    private static final String spliter = ",";
    private static final String NN = "X";
    private StringBuilder sb = new StringBuilder();
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        buildString(root);
        return sb.toString();
    }

    private void buildString(TreeNode node) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left);
            buildString(node.right);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
*/

/*
// version 2: pre-order Iterative
// Serialize :  using a Stack to output
// Deserialize :  using Stack to store nodes needed to append its right child
public class Codec {

    private static final String spliter = ",";
    private static final String NN = "#";
    private StringBuilder sb = new StringBuilder();
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if( root == null ) return "";
        tree2str(root);
        return sb.toString();
    }

    
    private void tree2str(TreeNode node) {
        
        Deque<TreeNode> stack = new LinkedList<>(); 
        stack.push(root);
        while( !stack.empty() ){
            TreeNode top = stack.pop();
            if( top != null ){
                sb.append( top.val ).append(spliter);
                stack.push( top.right );
                stack.push( top.left );
            }else{
                sb.append(NN).append(spliter);
            }
        }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if( data == "" ) return null;
        Deque<String> sq = new LinkedList<>();
        sq.addAll(Arrays.asList(data.split(spliter)));
        return str2tree(sq);
    }
    
    private TreeNode str2tree( Deque<String> sq) {
        Deque<TreeNode> stack = new LinkedList<>();
        
        TreeNode temp = sq.poll()
    }
}
*/

// version 3: BFS iterative
public class Codec {

    private static final String spliter = ",";
    private static final String NN = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if( root == null ) return "";
        
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while( q.size() > 0 ){
            TreeNode nd = q.poll();
            if( nd == null ){
                sb.append(NN).append(spliter);
            }else{
                sb.append(nd.val).append(spliter);
                // if( nd.left != null || nd.right != null ){ // no need to push two NULL for leaf nodes
                    q.offer(nd.left);
                    q.offer(nd.right);
                // }
            }
        }
        return sb.toString();
    }


    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if( data == "" ) return null;
        
        ArrayList<String> nl = new ArrayList<>();
        nl.addAll(Arrays.asList(data.split(spliter)));
        int l = nl.size() - 1;    // get rid of last empty string
        
        TreeNode root = new TreeNode( Integer.parseInt(nl.get(0)));
        Deque<TreeNode> q = new LinkedList<>();
        q.offer( root );
        
        int i = 0;
        while( q.size() > 0 && i < l ){
            TreeNode nd = q.poll();
            
            String leftVal = nl.get(i + 1);
            String rightVal = nl.get(i + 2);
            
            if( !leftVal.equals(NN) ){
                nd.left = new TreeNode( Integer.parseInt(leftVal) );
                q.offer(nd.left);
            }
            
            if( !rightVal.equals(NN) ){
                nd.right = new TreeNode( Integer.parseInt(rightVal) );
                q.offer(nd.right);
            }
            i = i + 2;
        }
        return root;
    }
    

}