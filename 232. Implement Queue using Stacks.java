
// versioin 1: make 'Stack top' be the 'Queue front'; push() need more manipulation
public class MyQueue {
    Stack<Integer> s;  // stack
    /** Initialize your data structure here. */
    public MyQueue() {
        s = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        Stack<Integer> t = new Stack<>();  // temp stack
        while( !s.empty() ){
            int p = s.pop();
            t.push(p);
        }
        t.push(x);
        while( !t.empty() ){
            int p = t.pop();
            s.push(p);
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return s.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s.empty();
    }
}
/*
// versioin 2: make 'Stack top' be the 'Queue end';   pop() , peek() need more manipulation
public class MyQueue {
    Stack<Integer> s;  // stack
    Stack<Integer> t;  // temp stack
    
    // Initialize your data structure here. 
    public MyQueue() {
        s = new Stack<>();
        t = new Stack<>();
    }
    
    // Push element x to the back of queue. 
    public void push(int x) {
        s.push(x);
    }
    
    //Removes the element from in front of queue and returns that element. 
    public int pop() {
        while( !s.empty() ){
            int p = s.pop();
            t.push(p);
        }
        int res = t.pop();
        while( !t.empty() ){
            int p = t.pop();
            s.push(p);
        }
        return res;
    }
    
    // Get the front element. 
    public int peek() {
        while( !s.empty() ){
            int p = s.pop();
            t.push(p);
        }
        int res = t.peek();
        while( !t.empty() ){
            int p = t.pop();
            s.push(p);
        }
        return res;
    }
    
    //Returns whether the queue is empty. 
    public boolean empty() {
        return s.empty();
    }
}

*/

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
 
