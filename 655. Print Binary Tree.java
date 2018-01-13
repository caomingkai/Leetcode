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
     1
    / \
   2   3
    \
     4
*/


// version 1: mine
class Solution {
    
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        if( root == null ) return res;
        
        int r = getHeight( root );
        int c = (int)Math.pow(2,r)-1;
        ArrayList<String> tempRes = new ArrayList<>();
        for( int i = 0; i < c; i++ ) tempRes.add("");
        for( int i = 0; i < r; i++ ) res.add(new ArrayList<>(tempRes));

        posSet( root, 1, 1, c, res );
        
        return res;
    }
    
    private int getHeight( TreeNode root ){
        if( root == null )
            return 0;
        return 1 + Math.max( getHeight(root.left), getHeight(root.right ));
    }
    
    private void posSet( TreeNode root, int rowP, int leftColP, int rightColP, List<List<String>> res ){
        int colP = (rightColP - leftColP )/2 + leftColP;
        List<String> lvlString = res.get(rowP-1);
        lvlString.set( colP-1, String.valueOf(root.val) );
        if( root.left != null ) posSet( root.left, rowP+1, leftColP, colP-1 ,res );
        if( root.right != null ) posSet( root.right, rowP+1, colP+1, rightColP, res );
    }
}



/*
// version 2
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new LinkedList<>();
        int height = root == null ? 1 : getHeight(root);
        int rows = height, columns = (int) (Math.pow(2, height) - 1);
        List<String> row = new ArrayList<>();
        for(int i = 0; i < columns; i++)  row.add("");
        for(int i = 0; i < rows; i++)  res.add(new ArrayList<>(row));
        populateRes(root, res, 0, rows, 0, columns - 1);
        return res;
    }

    public void populateRes(TreeNode root, List<List<String>> res, int row, int totalRows, int i, int j) {
        if (row == totalRows || root == null) return;
        res.get(row).set((i+j)/2, Integer.toString(root.val));
        populateRes(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);
        populateRes(root.right, res, row+1, totalRows, (i+j)/2+1, j);
    }

    public int getHeight(TreeNode root) {
         if (root == null) return 0;
         return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

}
*/