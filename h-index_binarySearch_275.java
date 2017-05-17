// [ 0, 4, 4, 5, 7 ] for sorted array
// In fact, it is a problem to find least element whose value >= N-index
// in essence, it is a "find" problem, so we can utilize binary search -- O(logn)


// Goodie: stop condition for binary search
// 1 - arr[mid] < target
// 2 - head < tail
// depend on pratical condition: return head ,NOT mid! since we need the first(least) one.
public class Solution {
    public int hIndex(int[] citations) {
        
        // O(logn) - binary search
       if(citations.length ==0 || citations[ citations.length-1 ] == 0)  {
           return 0;
       }  
       
       int N = citations.length ;
       int head = 0;
       int tail = N - 1;
       
       while( head < tail ){
            int mid = (head + tail)/2;
            if( citations[mid] >= N - mid){
                tail = mid;
            }else{
                head = mid + 1;
            }
       }
       
       return N-head;
       
       
        // O(n)
        
        /*
        int N = citations.length;
        
        for(int i = 0; i < N; i++ ){
            if( citations[i] >= N - i ){
                return N-i;
            }
        }
        
        return 0;
        */
    }
}