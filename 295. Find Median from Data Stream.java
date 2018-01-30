/*
    1.最小堆： 放大一些的数
    2.最大堆： 放小一些的数
*/
// version 1 :  two heaps
class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); 
    }
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        if(minHeap.size() >= maxHeap.size()+2) {
            maxHeap.add(minHeap.remove());
        } else if(maxHeap.size() >= minHeap.size()+2){ 
            minHeap.add(maxHeap.remove());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(minHeap.size() == maxHeap.size()) {
            return (double)(minHeap.peek()+maxHeap.peek())/2;
        } else if(minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }
}

/*
// version 1.2:  neater one with two heaps
class MedianFinder {
    // max queue is always larger or equal to min queue
    PriorityQueue<Integer> min = new PriorityQueue();
    PriorityQueue<Integer> max = new PriorityQueue(1000, Collections.reverseOrder());
    // Adds a number into the data structure.
    public void addNum(int num) {
        max.offer(num);                 // 该数放到最大堆中比较，堆顶是这里边的最小值
        min.offer(max.poll());          // 最大堆中的最小值，放到最小堆里边，堆顶是这里边的最大值
        if (max.size() < min.size()){   // 此时两个堆顶都拥有了各自的最值，平衡堆大小
            max.offer(min.poll());  
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (max.size() == min.size()) return (max.peek() + min.peek()) /  2.0;
        else return max.peek();
    }
}
*/

/*

// version 2:  BST
class MedianFinder {
    class TreeNode{
        int val;
        TreeNode parent,left,right;
        TreeNode(int val, TreeNode p){
            this.val=val;
            this.parent=p;
            left=null;
            right=null;
        }
        void add(int num){
            if(num>=val){
                if(right==null)
                    right=new TreeNode(num,this);
                else
                    right.add(num);
            }else{
                if(left==null)
                    left=new TreeNode(num,this);
                else
                    left.add(num);
            }
        }
        TreeNode next(){
            TreeNode ret;
            if(right!=null){
                ret=right;
                while(ret.left!=null)
                    ret=ret.left;
            }else{
                ret=this;
                while(ret.parent.right==ret)
                    ret=ret.parent;
                ret=ret.parent;
            }
            return ret;
        }
        
        TreeNode prev(){
            TreeNode ret;
            if(left!=null){
                ret=left;
                while(ret.right!=null)
                    ret=ret.right;
            }else{
                ret=this;
                while(ret.parent.left==ret)
                    ret=ret.parent;
                ret=ret.parent;
            }
            return ret;
        }
    }
    int n;
    TreeNode root, curr;
    // Adds a number into the data structure.
    public void addNum(int num) {
        if(root==null){
            root = new TreeNode(num,null);
            curr=root;
            n=1;
        }else{
            root.add(num);
            n++;
            if(n%2==1){
                if(curr.val<=num)
                    curr=curr.next();
            }else
                if(curr.val>num)
                    curr=curr.prev();
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(n%2==0){
            return ((double)curr.next().val+curr.val)/2;
        }else
            return curr.val;
    }
}
*/