/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 /*
 // version 1:  find out the diff length, and start at the point with same distance to the end, loop util find intersection item
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if( headA == null || headB == null ) return null;
        
        int la = length( headA );
        int lb = length( headB );
        
        // adjust headA align with headB
        while( la > lb ){
            headA = headA.next;
            la--;
        }
        
        // adjust headA align with headB
        while( la < lb ){
            headB = headB.next;
            lb--;
        }
        
        while( headA != headB ){
            headA = headA.next;
            headB = headB.next;
        }
        if( headA == null ) return null;
        
        return headA;
    }
    
    private int length( ListNode head ){
        int l = 0;
        while( head != null ){
            l++;
            head = head.next;
        }
        return l;
    }
}
*/

 // version 2:  no need find the length diff
 // let two pointer( headA headB ) loop through itself, and THEN loop the other list when it reach its end;
 // this way, headA and headB both go (lenghA + lengthB) step.
 // --- in the process, they both point to the other list, the two pointer will synchorously step forward until encouter target
 public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    
        if( headA == null || headB == null ) return null;
        ListNode a = headA;
        ListNode b = headB;
        
        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b ){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;// when headA reach its end, it point to headB, continue loop
            b = b == null ? headA : b.next;
        }
        
        return a;   // even if they don't have intersection, at last they point to 'null'
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