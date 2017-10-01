class Solution {
    public int findLongestChain(int[][] pairs) {
        
        if( pairs == null || pairs.length == 0 )
            return 0;
        int l = pairs.length;
        
        Arrays.sort(pairs, new PairComp() );
        
        int res = 1;
        int nextFirst = pairs[l-1][0];
        for( int i = l-2; i >=0; i-- ){
            int prevLast = pairs[i][1];
            if( nextFirst > prevLast ){
                res++;
                nextFirst = pairs[i][0];
            }
        }
        return res;
        
        
    }
    
    
    class PairComp implements Comparator<int[]>{
        
        @Override
        public int compare(int[] a1, int[]a2){
            if( a1[0] > a2[0] || (a1[0] == a2[0] && a1[1] > a2[1]) )
                return 1;
            else if( a1[0] < a2[0] || (a1[0] == a2[0] && a1[1] < a2[1]) )
                return -1;
            else
                return 0;
        }
    }
}