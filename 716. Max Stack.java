/*  
    用另外一个maxStack记录：与stack中元素【对应的】“当前看到的最大值”
      【 7 4 2 8 3 】
    | 3 |       | 8 |
    | 8 |       | 8 |
    | 2 |       | 7 |
    | 4 |       | 7 |
    |_7_|       |_7_|
    stack       maxStack
    
    注意点： 1. pop(), 同时删除两个栈的栈顶元素
            2. popMax()，删除最大值时，也需要将stack中相应值删除
*/

class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }

    // 对两个栈同时进行操作： 确保stack中每个元素，在maxStack中都有相应的当前最大值
    public void push(int x) {
        int max = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(max > x ? max : x);
        stack.push(x);
    }
    
    // 对两个栈同时进行操作： 确保stack中每个元素，在maxStack中都有相应的当前最大值
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack();
        while (top() != max) buffer.push(pop());
        pop();
        while (!buffer.isEmpty()) push(buffer.pop());
        return max;
    }
}


/*
    version 2: TreeMap + DoubleLinkedList
                1- 因为stack本来就是由 DoubleLinkedList来实现的，所以Stack普通操作就用DLL来实现
                2- peekMax()， 需要直到当前 DLL中的max值，TreeMap.lastKey() 来实现，TreeMap具有in-order特性
                3- popMax()，由TreeMap根据key找到max对应在DLL中的Node ref，然后进行删除
*/

/*
class MaxStack {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack() {
        map = new TreeMap();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
    }
}

class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x;
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}

*/