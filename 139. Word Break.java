class Solution {

  /*
    // version 1: BFS with a memo, avoiding duplicate visit
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {     // ensure duplicate visited will be avoided
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

    /*
        
   // version recursion with memo:
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
    */
        
/*
    
    // version 2 : DP - based on version 1
    //  dp[i] means: from 0 to i, whether or not the substring is exist
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
    */
        
        
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
     
    
    

    // version 5: recursive  
    HashMap<String, Boolean> memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);
        memo = new HashMap<String, Boolean>();
        return wordBreakHelper( s, set );
    }
    
    private boolean wordBreakHelper( String s, HashSet<String> set ){
        boolean res = false;
        int len = s.length();
        if( len == 0 )
            return true;
        
        if( memo.containsKey(s) )
            return memo.get(s);
        
        for( int i = 0; i <= len; i++ ){
            String firstHalf = s.substring(0,i);
            String secondHalf = s.substring(i,len);
            if( set.contains(firstHalf) ){
                if( set.contains(secondHalf) ){ 
                    memo.put(s,true);
                    return true;
                }else{
                    res = wordBreakHelper( secondHalf, set );
                    if( res ) {
                        memo.put(s,true);
                        return true;
                    }
                } 
            }
        }
        memo.put(s,false);
        return false;
       
    }

}