/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


/*
 -0- 用slower和faster方法判断是否有环；
     设链表的头节点是head，环的入口节点是entry，slower和faster2个指针相遇的节点是meeting;
     L1是head到entry的正向距离
     L2是entry到meeting的正向距离，
     C是环的长度，
     n是faster指针在cycle里遍历的次数(不到一遍算0)
     
 -1- 当slower和faster相遇时，slower已经走了L1 + L2的距离，也即head和meeting的距离;
 -2- 当slower和faster相遇时，faster已经走了L1 + L2 + C的距离;
 -3- 因为slower步进1，而faster步进2，那么当slower和faster第一次相遇时，faster已走的距离是slower已经走的距离的两倍，即
     2* (L1 + L2) = L1 + L2 +  C    ===>   L1 = C - L2
 -4- L1 = C - L2 这个等式表明， head和entry的距离(L1)，等于meeting到entry的正向距离（链表是有遍历方向的）。

 -5- ====>  L1 = C - L2
*/
public class Solution {
    public ListNode detectCycle( ListNode head ){
    
        if( head == null ) return null;
        
        ListNode meet = findMeetingPoint(head);
        if( meet == null ) return null;
        
        ListNode p1 = head;
        ListNode p2 = meet;
        while( p1 != p2 ){
            p1 = p1.next;
            p2 = p2.next;
        }
        
        return p1;
    }
    
    private ListNode findMeetingPoint( ListNode head){
        ListNode res = null;
        
        ListNode s = head;
        ListNode f = head;
        
        while( f.next != null ){
            s = s.next;
            f = f.next.next;
            
            if( f == null ) break;
            if( f == s ) return f;
        }
        
        return res;
    }
}









//=====================================================
//   in case list1 and list2 has cycles
//=====================================================

/*
                   __________
                  |          |     
        1 -> 2 -> 3 -> 4  -> 5
                       |
        6 -> 7 -> 8 -> 9


*/
public class ListIntersectWithCycle {

  public static void main(String[] args ){

    ListNode h1 = new ListNode(1);
    ListNode h2 = new ListNode(2);
    ListNode h3 = new ListNode(3);
    ListNode h4 = new ListNode(4);
    ListNode h5 = new ListNode(5);
    ListNode h6 = new ListNode(6);
    ListNode h7 = new ListNode(7);
    ListNode h8 = new ListNode(8);
    ListNode h9 = new ListNode(9);

    // create 1st list:
    h1.next = h2;
    h2.next = h3;
    h3.next = h4;
    h4.next = h5;
    // h5.next = h3;

    // create 2nd list:
    h6.next = h7;
    h7.next = h8;
    h8.next = h9;
    h9.next = h3;

    // test hasCyle()
    ListNode res = getIntersectionNode( h1, h6 );
    System.out.println(res!=null ? res.val : "null");
  }


  public static ListNode getIntersectionNode(ListNode h1, ListNode h2) {

      if( h1 == null || h2 == null )
          return null;

      ListNode n1 = nodeInCycle(h1);
      ListNode n2 = nodeInCycle(h2);
      System.out.println(n1!=null ? n1.val : "null");
      System.out.println(n2!=null ? n2.val : "null");

      if( n1 == null && n2 != null || n1 != null && n2 == null ){
          return null;
      }else if( n1 != null && n2 != null ){
          ListNode walkNode = n1;
          do {  
              if( walkNode == n2 )
                   return n2;
              walkNode = walkNode.next;
          } while( walkNode != n1 );  // walk for the whole cycle
      }else{
          ListNode p1 = h1;
          ListNode p2 = h2;

          while( p1 != p2 ){
              p1 = p1==null ? h2 : p1.next;
              p2 = p2==null ? h1 : p2.next;
          }
          return p1;
      }
      return null;
    }



  public static ListNode nodeInCycle(ListNode head) {
    
        if( head == null )
            return null;
            
        ListNode s = head;
        ListNode f = head;
        
        while( f.next != null ){
            s = s.next;
            f = f.next.next;
            
            if( s == f )
                return f;
            if( f == null )
                return null;
        }
        
        return null;
    }
}