
class Solution {
    /*
        version 1: use Heap--> O(m*n*logk)
    */
    /*
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
    */
    
    /*
        version 2: binary search（方式二）
        二分法：
            方式一：给定target value，通过index二分查找，缩小该value多处的搜索空间
            方式二：给定target index，通过value二分查找，缩小该index对应的取值范围
                   1. 方式二的问题一定存在解
                   2. 因为找该值的过程是一步一步靠+1、-1来缩小范围的，所以有可能到某个值的时候已经能满足它是第k个
                   
                   5. count：小于等于<=mid的元素的个数
                   6. count>k  : 表明在mid之前的元素的数目>k,该mid一定不是第k个数，需要将high减小1，high= mid-1;
                   7. count=k  : 表明在mid之前的元素的数目=k,该mid一定不是第k个数，需要将high减小1，high= mid-1;
                   8. count<k  : 表明在mid之前的元素的数目<k,该mid可能是第k个数，试探形的将low增大1，low= mid+1;
                                 不必担心target值会因为low=mid+1而被跳过，
                                 因为更新low之前，必须满足count<k，也就是说排在mid前边的元素小于k；
                                 如果mid就是target值，那么count一定会将target统计在内,造成count>=k
                                 这时是对high进行更新，而不是对low进行更新。
                   9. 最后随着窗口的缩小，因为low一定不会调过target，所以target最终一定是low
                   
    */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = getLessEqual(matrix, mid);
            if (count < k) lo = mid + 1;   // 
            else hi = mid - 1;
        }
        return lo;
    }
    
    private int getLessEqual(int[][] matrix, int val) {
        int res = 0;
        int n = matrix.length, i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) i--;
            else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }

    
    
}

 