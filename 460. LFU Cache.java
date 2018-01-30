/*
LRU: 是首先淘汰最长时间未被使用的页面       【只根据“有限的历史信息“进行删除】“有限”指cache大小所对应时间段
LFU: 是先淘汰一定时间内被访问次数最少的页面。【  根据”整体的历史信息“进行删除】”整体“不仅仅考虑了cache所跨时段的信息，还包括之前的也用freq统计完毕了

例子：cache的大小为3，按顺序存入 5，4，5，4，5，7，这时候cache刚好被装满了,此时再put进去一个8，
    如果使用LRU算法，应该将4删除，因为4最久未被使用，
    而如果使用LFU算法，则应该删除7，因为7被使用的次数最少，只使用了一次。

===============================================================
http://bookshadow.com/weblog/2016/11/22/leetcode-lfu-cache/
head --- FreqNode1 ---- FreqNode2 ---- ... ---- FreqNodeN
              |               |                       |               
            first           first                   first             
              |               |                       |               
           KeyNodeA        KeyNodeE                KeyNodeG           
              |               |                       |               
           KeyNodeB        KeyNodeF                KeyNodeH           
              |               |                       |               
           KeyNodeC         last                   KeyNodeI           
              |                                       |      
           KeyNodeD                                 last
              |
            last

*/
// version 1:  HashMap + HashMap< DoubleLinkedList>  --> O(1)
class Solution{
    private int capacity;
    private int count;
    private HashMap<Integer, Tuple> map1;       // map1：[key : Tuple Node]
    private HashMap<Integer, Tuple> finalNodes; // finalNodes[key:Tuple List] the final node of key times
    private Tuple dummyHead;
    private Tuple dummyEnd;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        count = 0;
        map1 = new HashMap<Integer, Tuple>();
        finalNodes = new HashMap<>();
        dummyHead = new Tuple(0, 0, 0);
        dummyEnd = new Tuple(0, 0, 0);
        dummyHead.next = dummyEnd;
        dummyEnd.prev = dummyHead;
    }

    public int get(int key) {
        if (capacity == 0 || !map1.containsKey(key)) {
            return -1;
        }
        Tuple old = map1.get(key);
        set(key, old.value);
        return old.value;
    }

    public void set(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map1.containsKey(key)) { // this key has appeared
            Tuple cur = map1.get(key);
            if (finalNodes.get(cur.times) == cur && finalNodes.get(cur.times + 1) == null) { // the position should not change
                finalNodes.put(cur.times, cur.prev.times == cur.times ? cur.prev : null);
                cur.times++;
                cur.value = value;
                finalNodes.put(cur.times, cur);
                return;
            }
            removeNode(cur); // remove node cur
            if (finalNodes.get(cur.times) == cur) {
                finalNodes.put(cur.times, cur.prev.times == cur.times ? cur.prev : null);
            }
            cur.times++;
            cur.value = value;
            Tuple finalNode = finalNodes.get(cur.times) == null ? finalNodes.get(cur.times - 1) : finalNodes.get(cur.times);
            insertNode(finalNode, cur); 
            finalNodes.put(cur.times, cur); // cur is the final node whitch appeared cur.times
        } else if (count == capacity) { // reach limt of the cache
            Tuple head = dummyHead.next;
            removeNode(head); //remove the first which appeared least times and is the least Used
            map1.remove(head.key);
            if (finalNodes.get(head.times) == head) {
                finalNodes.remove(head.times);
            }
            Tuple cur = new Tuple(key, value, 1);
            if (finalNodes.get(1) == null) {
                insertNode(dummyHead, cur);
            } else {
                Tuple finalNode = finalNodes.get(1);
                insertNode(finalNode, cur);
            }
            finalNodes.put(1, cur);
            map1.put(key, cur);
        } else {
            count++;
            Tuple cur = new Tuple(key, value, 1);
            if (finalNodes.get(1) == null){
               insertNode(dummyHead, cur);
            } else {
                Tuple finalNode = finalNodes.get(1);
                insertNode(finalNode, cur);
            }
            finalNodes.put(1, cur);
            map1.put(key, cur);
        }
    }

    public void insertNode(Tuple t1, Tuple t2) {
        t2.next = t1.next;
        t1.next.prev = t2;
        t1.next = t2;
        t2.prev = t1;
    }

    public void removeNode(Tuple node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    class Tuple {
        int key;
        int value;
        int times;
        Tuple prev;
        Tuple next;
        public Tuple(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }
}



// version 1.1:  HashMap + HashMap< DoubleLinkedList>  --> O(1)
public class LFUCache {
    class Node {
        int key, val, cnt;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            cnt = 1;
        }
    }
    
    class DLList {
        Node head, tail;
        int size;
        DLList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }
        
        void add(Node node) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
            size++;
        }
        
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        
        Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            else return null;
        }
    }
    
    int capacity, size, min;
    Map<Integer, Node> nodeMap;
    Map<Integer, DLList> countMap;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;
        update(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            node.val = value;
            update(node);
        }
        else {
            node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                DLList lastList = countMap.get(min);
                nodeMap.remove(lastList.removeLast().key);
                size--;
            }
            size++;
            min = 1;
            DLList newList = countMap.getOrDefault(node.cnt, new DLList());
            newList.add(node);
            countMap.put(node.cnt, newList);
        }
    }
    
    private void update(Node node) {
        DLList oldList = countMap.get(node.cnt);
        oldList.remove(node);
        if (node.cnt == min && oldList.size == 0) min++; 
        node.cnt++;
        DLList newList = countMap.getOrDefault(node.cnt, new DLList());
        newList.add(node);
        countMap.put(node.cnt, newList);
    }
}


// version 2 :  HashMap + TreeMap  --> O(logn)
class Solution{
    private int capacity;
    private int stamp;
    private HashMap<Integer, Tuple> hashMap;
    private TreeMap<Tuple, Integer> treeMap;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        stamp = 0;
        hashMap = new HashMap<Integer, Tuple>(); 
        treeMap = new TreeMap<Tuple, Integer>(new Comparator<Tuple>() {
            public int compare(Tuple t1, Tuple t2) {
                if (t1.times == t2.times) {
                    return t1.stamp - t2.stamp;
                }
                return t1.times - t2.times;
            }
        });
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!hashMap.containsKey(key)) {
            return -1;
        }
        Tuple old = hashMap.get(key);
        treeMap.remove(old);
        Tuple newTuple = new Tuple(old.value, stamp++, old.times + 1);
        treeMap.put(newTuple, key);
        hashMap.put(key, newTuple);
        return old.value;
    }

    public void set(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (hashMap.containsKey(key)) {
            Tuple old = hashMap.get(key);
            Tuple newTuple = new Tuple(value, stamp++, old.times + 1);
            treeMap.remove(old);
            hashMap.put(key, newTuple);
            treeMap.put(newTuple, key);
        } else {
            if (treeMap.size() == capacity) {
                int endKey = treeMap.pollFirstEntry().getValue();
                hashMap.remove(endKey);
            }
            Tuple newTuple = new Tuple(value, stamp++, 1);
            hashMap.put(key, newTuple);
            treeMap.put(newTuple, key);
        }
    }
    class Tuple {
        int value;
        int times;
        int stamp;
        public Tuple (int value, int stamp, int times) {
            this.value = value;
            this.stamp = stamp;
            this.times = times;
        }
    }
}


// version 3:  HashMap + PriorityQueue --> O(n)
class Solution{
    long stamp;
    int capacity;
    int num;
    PriorityQueue<Pair> minHeap;
    HashMap<Integer, Pair> hashMap;

    // @param capacity, an integer
    public LFUCache(int capacity) {
        // Write your code here
        this.capacity = capacity;
        num = 0;
        minHeap = new PriorityQueue<Pair>();
        hashMap = new HashMap<Integer, Pair>();
        stamp = 0;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (capacity == 0) {
            return;
        }
        // Write your code here
        if (hashMap.containsKey(key)) {
            Pair old = hashMap.get(key);
            minHeap.remove(old);
            
            Pair newNode = new Pair(key, value, old.times + 1, stamp++);
            
            hashMap.put(key, newNode);
            minHeap.offer(newNode);
        } else if (num == capacity) {
            Pair old = minHeap.poll();
            hashMap.remove(old.key);
            
            Pair newNode = new Pair(key, value, 1, stamp++);
            
            hashMap.put(key, newNode);
            minHeap.offer(newNode);
        } else {
            num++;
            Pair pair = new Pair(key, value, 1, stamp++);
            hashMap.put(key, pair);
            minHeap.offer(pair);
        }
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // Write your code here
        if (hashMap.containsKey(key)) {
            Pair old = hashMap.get(key);
            minHeap.remove(old);
            
            Pair newNode = new Pair(key, old.value, old.times + 1, stamp++);
            
            hashMap.put(key, newNode);
            minHeap.offer(newNode);
            return hashMap.get(key).value;
        } else {
            return -1;
        }
    }

    class Pair implements Comparable<Pair> {
        long stamp;
        int key;
        int value;
        int times;
        public Pair(int key, int value, int times, long stamp) {
            this.key = key;
            this.value = value;
            this.times = times;
            this.stamp = stamp;
        }
        
        public int compareTo(Pair that) {
            if (this.times == that.times) {
                return (int)(this.stamp - that.stamp);
            } else {
                return this.times - that.times;    
            }
        }
    }
}
