/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    
    /*
    // “保存next” --“断开原指向” --“指向前节点”
    // 从前往后扫描：将遇到的每个node的next改变指向，指向前一个节点，两步工作
    //  1 - 保存node原来的next指向的节点，供后续使用
    //  2 - 将next重新指向prev节点， 直到node为null
    
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        
        while(cur!=null){
            ListNode next = cur.next; 
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    */

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        
        while(cur!=null){
            ListNode next = cur.next; 
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
  
    // 2- recursion version 
    /*
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        
        return h ( null, head);
    }
    */
    /*                   1 -> 2 -> 3 -> 4
            1st: null <- 1  + h(1, 2)
            2nd  null <- 1 <- 2 + h(2, 3)
            
            跟iterative原理一样：
            1. 传入 prev值， 以及curr链表
            2. 函数内部做两件事：
                - 将curr链next断开，指向prev
                - 更新prev、curr
            3. 直到curr的值是null，返回prev
    */

    /*
    private ListNode h(ListNode prev, ListNode curr){
        if( curr == null ){
            return prev;
        }
        
        ListNode next = curr.next;
        curr.next = prev;
        return h( curr, next );
    }
    */



    // version 3: reverse linkedlist with cycle
    public ListNode reverseList(ListNode head) {
        if (head == null || head == head.next)
            return head;
        
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode anchor = head.next; // 必须设置一个anchor，
                                     // 不能直接使用head.next作为while结束条件
                                     // 因为head.next的指向改变了

        do{
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }while( curr != anchor );   // 不能直接使用head.next作为while结束条件！

        return prev.next; // 因为pre最终指向的head是原来的最小值
                          // 改变指向后，prev[head] next指向的是最大值
    }

}