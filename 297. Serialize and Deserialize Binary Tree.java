/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */





// BFS iterative
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
                // NOTICE:  the following sentence mustn't added, there are cases as follows:
                //      1
                //    /  \
                //   2    3
                //       / \
                //      4   5
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
            }else{
                nd.left = null;
            }
            
            if( !rightVal.equals(NN) ){
                nd.right = new TreeNode( Integer.parseInt(rightVal) );
                q.offer(nd.right);
            }else{
                nd.right = null;
            }
            i = i + 2;
        }

        return root;
    }
    
}


/*
// MY DFS iterative
// 下边有精简版的
public class Codec {

    private final String D = ",";
    private final String NULL = "!";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>(); // store nodes to output their right child
        StringBuilder sb = new StringBuilder();

        if( root == null ) return sb.toString();
        
        stack.push(root);
        sb.append(root.val + D );
        TreeNode x = root;
        
        while( !stack.isEmpty() ){
            
            while( x.left != null ){   // keep adding left's left to stack and string
                stack.push(x.left);
                sb.append( x.left.val + D );
                x = x.left;
            }
            
            sb.append(NULL + D);    // x.left is null, we need append "!"
            
            TreeNode n = stack.pop();
            while( n.right == null && !stack.isEmpty()){  // pop() node until find a valid right node
                sb.append(NULL + D);
                n = stack.pop();
            }
                        
            if( n.right != null ){     // for this right child, push it on top of stack, loop again
                x = n.right;
                stack.push(x);
                sb.append( x.val + D );
            }
        }
        return sb.toString();
    }
    
    
    public TreeNode deserialize(String data) {
        if( data.length() == 0 ) return null;
        
        String[] list = data.split(D);
        int l = list.length;
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode root = new TreeNode( Integer.valueOf(list[0]));
        stack.push(root);
        int i = 1;
        TreeNode x = root;
        while( i < l ){
            while( i < l && !list[i].equals(NULL) ){    // as long as there is no NULL, go left till end
                x.left = new TreeNode(Integer.valueOf(list[i]));
                stack.push( x.left );
                x = x.left;
                i++;
            }
                                            // 同时做两件事： 1. 找non-null值； 2.pop到该接non-null值的点
            while( i < l && list[i].equals(NULL) ){  // for Node n, its right child is null
                x = stack.pop();                     // keep stack-poping and list-moving forward
                i++;
            }
            
            if( i < l ){                       // append non-null value to 
                x.right = new TreeNode( Integer.valueOf(list[i]) );
                stack.push(x.right);
                x = x.right;
                i++;
            }
            
        }
        return root;
    }
    
}
*/





/*
// DFS - preorder - recursive
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        dfs(root,sb);
        System.out.println(sb.toString());
        return sb.toString();
    }
    private void dfs(TreeNode x, StringBuilder sb) {
        if (x==null) {
            sb.append("null ");
            return;
        }
        sb.append(String.valueOf(x.val));
        sb.append(' ');
        dfs(x.left,sb);
        dfs(x.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] node=data.split(" ");
        int[] d=new int[1];
        return dfs(node,d);
    }
    private TreeNode dfs(String[] node, int[] d) {   // global parameter, instead of int d
        if (node[d[0]].equals("null")) {             // 目的：希望后边两个函数“共享”d的改变
            d[0]++;
            return null;
        }
        TreeNode x=new TreeNode(Integer.valueOf(node[d[0]]));
        d[0]++;
        x.left=dfs(node,d);   // 该函数调用完毕，后下一个函数的d[0]已经自动加一了，因为这是int[]数组在heap上
        x.right=dfs(node,d);
        return x;
    }

}
*/



/*
// DFS - preorder - iterative
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        TreeNode x=root;
        Deque<TreeNode> stack=new LinkedList<>();
        while (x!=null || !stack.isEmpty()) {
            if (x!=null) {
                sb.append(String.valueOf(x.val));
                sb.append(' ');
                stack.push(x);
                x=x.left;
            }
            else {
                sb.append("null ");
                x=stack.pop();
                x=x.right;
            }
        }
         System.out.println(sb.toString());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length()==0) return null;
        String[] node=data.split(" ");
        int n=node.length;
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(node[0]));
        TreeNode x=root;
        stack.push(x);
        
        int i=1;
        while (i<n) {
            while (i<n && !node[i].equals("null")) {
                x.left=new TreeNode(Integer.valueOf(node[i++]));
                x=x.left;
                stack.push(x);
            }
            while (i<n && node[i].equals("null")) {
                x=stack.pop();
                i++;
            }
            if (i<n) {
                x.right=new TreeNode(Integer.valueOf(node[i++]));
                x=x.right;
                stack.push(x);
            }
        }
        return root;
    }
}
*/
