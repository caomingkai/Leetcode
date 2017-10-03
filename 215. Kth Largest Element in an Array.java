class Solution {
    /*
    // version 1:
    public int findKthLargest(int[] nums, int k) {
        
        // create MaxHeap
        PriorityQueue<Integer> pq = new PriorityQueue<>( (a,b)->(b-a) );
        for( int i = 0; i < nums.length; i++ ){
            pq.add(  nums[i] );
        }
        
        
        // extract until k element
 
        for(int i = 1; i <= k-1; i++ ){
            pq.poll();
        }
        
        return pq.poll();
    }
    */
    
    // version 2:
    public int findKthLargest(int[] nums, int k) {
        
        // create MinHeap
        PriorityQueue<Integer> pq = new PriorityQueue<>( k );
        for( int i = 0; i < k; i++ ){
            pq.add(  nums[i] );
        }
        
        
        // extract until k element
        int l = nums.length;
        for(int i = k; i < l; i++ ){  
            if( nums[i] > pq.peek() ){
                pq.poll();
                pq.offer( nums[i]);
            }
        }
        
        return pq.poll();
    }
}