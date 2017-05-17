// 0 1 3 5 6
// Using Array.sort() ---> O(nlogn)
// loop from backend, check if value larger than step index


import java.util.Arrays;

public class Solution {
    public int hIndex(int[] citations) {
        
        if( citations.length == 0 ){
            return 0;
        }
        
        Arrays.sort( citations );
        
        int h = 1;
        while(  citations.length - h  >= 0  && citations[ citations.length - h ] >= h){
            h++;
        }
        
        return --h;

        // another way: loop from frontend, check current value larger than N-i (citations[i]>=N-i)
        /* 
        int N = citations.length;
        Arrays.sort(citations);
        for(int i=0;i<N;i++){
            if(citations[i]>=N-i)   return N-i;
        }
        return 0;
        */
    }
}