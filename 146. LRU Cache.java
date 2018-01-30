/*  
    1. “O(1)"get时间：HashMap 存key:value键值对
    2. “最不常用”表明：LinkedList是很好的选择，但是如何将后期hit中的值调整到链表前端是个问题
        - 链表中查找相应key值，时间为O(n)
        - 但是，我们可以在HashMap的val中存入它在链表中相应的位置，这样时间为O(n)
        
             _____      _____       _____
             | 1 |      | 2 |       | 3 |
             |---|      |---|       |---|
             ``\``      ``\``       ``|``
          +-----\----------\----------+
        __|__    \_____     \_____ 
        | 3 | --> | 1 | ---> | 2 |
        | C | <-- | A | <--- | B | 
        `````     `````      `````  
         越往前，数据越新； 越往后，数据越旧
*/
public class LRUCache {
    private Map<Integer, Node> map;
    private Node head, tail;
    private int capacity = 0;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToTail(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            moveToTail(node);
        } else {
            if (map.size() == capacity) {
                map.remove(head.key);
                head = head.next;
                if (head != null) head.prev = null;     
                else tail = null;               
            }
            Node node = new Node(key, value);
            moveToTail(node);
            map.put(key, node);
        }
    }
    
    private void moveToTail(Node node) {
        if (head == null && tail == null) {
            head = node;
            tail = node;
            return;
        }
        
        if (map.containsKey(node.key)) {
            if (node == tail) return;  // node is tail
            
            if (node == head) {    // node is head;
                head = head.next;
                head.prev = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }    
        }
        
        node.prev = tail;
        tail.next = node;
        node.next = null;
        tail = node;
    }
    
    private class Node {
        int key;
        int val;
        Node prev;
        Node next;
        
        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */