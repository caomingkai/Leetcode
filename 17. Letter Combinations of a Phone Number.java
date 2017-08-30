class Solution { 
    
    public final String[] DS = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    /*
    // version 1: iterative with queue
    public List<String> letterCombinations(String digits) {
        
        Deque<String> q = new LinkedList<>();
        int len = digits.length();
        if( len == 0 ) return new ArrayList<String>(q);
        

        q.add("");
        
        for(int i = 0; i < len; i++ ){
            int d = Character.digit(digits.charAt(i),10);
            if( d == 0 || d == 1 ) return new ArrayList<String>(q);
            String str = DS[d];
            
            int size = q.size();
            for( int j = 0; j < size; j++ ){
                String temp = q.poll();
                for( int k = 0; k < str.length(); k++ ){
                    q.offer(temp + str.charAt(k));
                }
            }
        }
        return new ArrayList<String>(q);
    }
    */
    
    // version 2: Backtracking with recursion
    private List<String> res = new LinkedList<>();
    
    public List<String> letterCombinations(String digits) {
        
        int l = digits.length();
        if( l == 0 ) return res;
        
        String prefix = "";
        backtrack(digits, 0, l, prefix);
        return res;
    }
    
    private void backtrack( String digits, int index, int l, String prefix ){
        
        // base case
        if( index == l ){
            res.add(prefix);
            return;
        }
        // recursive case
        int d = Character.digit(digits.charAt(index), 10);
        String d2s = DS[d];
        int len = d2s.length();
        for(int i = 0; i < len; i++ ){
            char c = d2s.charAt(i);
            backtrack(digits, index+1, l, prefix+c );
        }
    }
    
    
}