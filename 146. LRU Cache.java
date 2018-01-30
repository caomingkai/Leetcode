/*  
    1. “O(1)"get时间：HashMap 存key:value键值对
    2. “最不常用”表明：LinkedList是很好的选择，但是如何将hit中的值调整到链表前端是个问题
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
         越往前，数据越噬待移除； 越往后，数据越新



Get操作: 
    当key不在HashMap中返回-1
    当key在HashMap中，我们取得这个node，将node移动到双链表尾部，返回node.val

Set操作:
    当key在HashMap中，我们取得这个node，更新node.val，将node移动到双链表尾部
    否则我们用map.size()和capacity来检查是否cache容量已满
        1. 假如已满，我们从map和双链表中移除首节点，再从尾部添加新节点。
            - 从双链表中移除使用了head = head.next，然后新的head非空时head.prev = null。
            - 当新的head为空时，说明之前的head = tail，这时我们也设tail = null。
        2. 否则我们直接从尾部添加新节点
        3. 将新节点加入到map中

heler:moveToTail()方法
    将node移动到尾部，或是从尾部添加新节点。这里需要注意的点如下:
        1. head和tail均为空，我们直接设置head = node, tail = node并且返回
        2. 当map中包含node.key时，说明这不是一个新节点。我们要先处理其在双链表中的前节点和后节点，再将其加入到双链表尾部并更新tail。要注意如下：
            - node = tail，我们并不用做改变，直接return
            - node = head，我们更新head，并且设置新head.prev = null
            - 否则node既有前节点，又有后节点，我们将node.prev和node.next连接，将node加入到双链表尾部并且更新tail
        3. 否则，这是一个新节点，我们将其加入到双链表尾部，并且更新tail
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