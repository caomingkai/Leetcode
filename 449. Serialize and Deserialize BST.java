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
public class Codec {
    private static final String spliter = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) { // sanity check
            return null;
        }
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(spliter);
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] sa = data.split(spliter);
        root = helper( sa, Integer.MIN_VALUE, Integer.MAX_VALUE );
        return;
    }
    private TreeNode helper( int[] l, int min, int max ){

        TreeNode root = new TreeNode(r);
        root.left = heler( l, )
    }
    
    */
//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {
//         if (data == null || data.length() == 0) {
//             return null;
//         }
//         String[] sa = data.split(spliter);
//         int[] index = new int[] {0};
//         return helper(sa, index, Long.MIN_VALUE, Long.MAX_VALUE);
//     }

//     // construct BST from preorder order traversal;
//     private TreeNode helper(String[] sa, int[] index, long min, long max) {
//         if (index[0] >= sa.length) {
//             return null;
//         }
//         TreeNode root = null;
//         int rootValue = Integer.parseInt(sa[index[0]]);
//         if (rootValue > min && rootValue < max) {
//             root = new TreeNode(rootValue);
//             index[0]++;
//             root.left = helper(sa, index, min, rootValue);
//             root.right = helper(sa, index, rootValue, max);
//         }
//         return root;
//     }
// }

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        serializeDFS(root,sb);
        return sb.toString();
    }

    private void serializeDFS(TreeNode root,StringBuffer sb){
        if(root==null) return;
        sb.append(root.val+" ");
        serializeDFS(root.left, sb);
        serializeDFS(root.right, sb);
    }


    
    // Decodes your encoded data to tree.  
    // pre-order traversal  
    public TreeNode deserialize(String data) {  
        if (data.equals("")) return null;  
        String[] strs = data.split(" ");  
        Queue<Integer> q = new LinkedList<>();  
        for (String e : strs) {  
            q.offer(Integer.parseInt(e));  
        }  
        return getNode(q);  
    }  
      
    // some notes:  
    //   5  
    //  3 6  
    // 2   7  
    private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7  
        if (q.isEmpty()) return null;  
        TreeNode root = new TreeNode(q.poll());//root (5)  
        Queue<Integer> samllerQueue = new LinkedList<>();  
        while (!q.isEmpty() && q.peek() < root.val) {  
            samllerQueue.offer(q.poll());  
        }  
        //smallerQueue : 3,2   storing elements smaller than 5 (root)  
        root.left = getNode(samllerQueue);  
        //q: 6,7   storing elements bigger than 5 (root)  
        root.right = getNode(q);  
        return root;  
    }  

    /*
    // recursion 
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] strArr = data.split(" ");
        List<Integer> list = new ArrayList<>();
        list.add(0);
        return deserializeDFS(strArr,list,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private TreeNode deserializeDFS(String[] data,List<Integer> list,int lower,int upper){
        //获取位置
        int index = list.get(0);
        //如果值不在当前范围返回null
        int val = Integer.parseInt(data[index]);
        if(val<lower||val>upper) return null;
        //如果位置在最后直接返回
        if(index==data.length-1) return new TreeNode(val);

        //新建点,位置+1,可能有左右孩子
        TreeNode node = new TreeNode(val);
        list.set(0,++index);

        // 下一节点在左边
        if(Integer.parseInt(data[list.get(0)])<node.val){
            node.left = deserializeDFS(data,list,lower,node.val);
        }
        // 下一节点在右边
        if(Integer.parseInt(data[list.get(0)])>node.val){
            node.right = deserializeDFS(data,list,node.val,upper);
        }
        return node;
    }
    */
}
