class Solution {

 /*   
    // version 1: BFS
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }
*/
    
    // version 2 : DP - based on version 1
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
    /*
    // version 3: using visited value, avoid duplicate visit
    public boolean wordBreak(String s, List<String> wordDict) {

        HashSet<String> dict = new HashSet<>(wordDict);
        if( dict.contains(s) )
            return true;

        
        int l = s.length();
        boolean[] existMap= new boolean[l+1]; // contain former i letters, if or not can be segmented into a dictionary sequence 
        existMap[0] = true;
        
        for( int i = 1; i < l; i++ ){     // i is how many letters we pass by
            for( int j = 0; j <= i; j++ ){ // j is internal length in (0~i)
                if( (  existMap[i-j] && dict.contains(s.substring(i-j,i))  )   )
                    existMap[i] = true;
            }
            if(  existMap[i]  ){
                String secondHalf = s.substring(i);
        
                if( dict.contains(secondHalf) )
                    return true;
            } 
        }
        return false;
    }
    */
     
    
    
/*
    // Bad version : recursive  cannot used visited value due to value-already-on-stack, even though I store possible duplicate value as code proceed.
    // need refactor to reuse them.
    HashSet<String> set = new HashSet<>();
    
    public boolean wordBreak(String s, List<String> wordDict) {
         
        set.addAll(wordDict);
        return wordBreakHelper( s, set );
    }
    
    private boolean wordBreakHelper( String s, HashSet<String> set ){
        boolean res = false;
        int len = s.length();
        if( len == 0 )
            return true;
        
        for( int i = 0; i <= len; i++ ){
            String firstHalf = s.substring(0,i);
            String secondHalf = s.substring(i,len);
            if( set.contains(firstHalf) ){
               
                if( set.contains(secondHalf) ){    // intended to used already visited value, but due to stack-store nature, enven though we add duplicate value, we cannot use it.
                    return true;
                }else{
                    res = wordBreakHelper( secondHalf, set );
                    set.add(secondHalf);           // intended to add possible duplicate value to set, for later use, however, since that value has already calculated out by old 'set', so it doesnot work.
                    if( res ) return true;
                }
                
                
                
            }else{
                continue;
            }
            
        }
        
        return false;
       
    }
*/
}