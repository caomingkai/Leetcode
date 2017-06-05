/*
// version 1: maintain two arraylist: one for stack, one for current min value.
public class MinStack {
    int top;
    ArrayList<Integer> arr;  // store elements
    ArrayList<Integer> min;  // store min for current length array
    
    public MinStack() {
        arr = new ArrayList<Integer>();
        min = new ArrayList<Integer>();
        top = -1;
    }
    
    public void push(int x) {
        arr.add(x);
        if( min.size() == 0 ){
            min.add( x );
        }else{
            int curMin = min.get(top);
            if( x < curMin ){
                min.add(x);
            }else{
                min.add(curMin);
            }
        }
        top++;
    }
    
    public void pop() {
        arr.remove(top);
        min.remove(top);
        top--;
    }
    
    public int top() {
        return arr.get(top);
    }
    
    public int getMin() {
        return min.get(top);
    }
}
*/

// version 2: GOOD design
// design a class Node, containing : value, current min, nextNode fields.
public class MinStack {
    private Node head;
    
    public void push(int x) {
        if(head == null) 
            head = new Node(x, x);
        else 
            head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
    
    private class Node {
        int val;
        int min;
        Node next;
        
        private Node(int val, int min) {
            this(val, min, null);
        }
        
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */