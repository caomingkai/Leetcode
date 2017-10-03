
class Solution {
    
   
    public int kthSmallest(int[][] matrix, int k) {
        
        if( matrix == null || matrix.length == 0 ) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, 
        new Comparator<Integer>(){
            @Override
            public int compare(Integer a,Integer b){
                return b - a;
            }
        }
                                                       );
        
        int res = 0;
        int n = matrix.length;
        int cnt = 0;
        for( int i = 0; i < n; i++ ){
            for( int j = 0; j < n; j++ ){
                if( cnt < k ){
                    pq.offer(matrix[i][j]);
                }else{
                    if( matrix[i][j] < pq.peek() ){
                        pq.poll();
                        pq.offer(matrix[i][j]);
                    }
                }
                cnt++;
            }
        }
        return pq.poll();
    }   
    
    
}

 