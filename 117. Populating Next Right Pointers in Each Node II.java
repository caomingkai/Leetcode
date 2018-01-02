/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {

        
        TreeLinkNode lvlStart = root;
        boolean startFlag = true;
        
        while( lvlStart != null ){
            
            TreeLinkNode iter = lvlStart;
            TreeLinkNode curPos = null;
            TreeLinkNode nxtPos = null;
            
            while( iter != null ){
                if( iter.left != null ){
            
                    if( curPos == null  ){
                        curPos = iter.left;
                        lvlStart = iter.left;
                    }else {
                        nxtPos = iter.left;
                        curPos.next = nxtPos;
                        curPos = nxtPos;
                    }
                }
                
                if( iter.right != null ){
                    
                    if( curPos == null){
                        curPos = iter.right;
                        lvlStart = iter.right;
                    }else {
                        nxtPos = iter.right;
                        curPos.next = nxtPos;
                        curPos = nxtPos;
                    }
                }
                
                iter = iter.next;
            }
            
            if( curPos == null )
                lvlStart = null;
        }
        
        return;
    }
}