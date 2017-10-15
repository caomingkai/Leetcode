class Solution {
    public boolean canPermutePalindrome(String s) {
        if( s == null || s.length() == 0 )
            return true;
        
        int l = s.length();
        int[] bucket = new int[256];
        for( int i = 0; i < l; i++ ){
            bucket[s.charAt(i)]++;
        }
        
        int negCnt = 0;
        for( int i = 0; i < 256; i++){
            if(negCnt > 1) return false;
            if( bucket[i]%2 != 0 ) negCnt++;
        }
        return true;
    }
}