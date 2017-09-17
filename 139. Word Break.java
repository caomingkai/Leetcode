/*
// version 1: DP
// s: 0 ~ l-1
// f(0,l-1) = dict.contains(0,l-1) || f(0,i)&&dict.contains(i,l-1) || f(0,i+1)&&dict.contains(i+1,l-1) || .....
// if s(0,l-1) returns true, e.g. s = ' a b c d e '
// for its fisrt char 'e' , it at most exists in all the following forms:
// 'e'   ,   'de'   ,   'cde'   ,   'bcde'   ,   'abcde'
// for each, we only need to check its prefix return true or not

class Solution {
    
    Set<String> dict;
    Map<String, Boolean> memo =  new HashMap<>();
    
    public boolean wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        int l = s.length();
        
        // edge case
        if( l == 1 ) return dict.contains(s);
        
        // general case
        return DP( s, 0, l );
    }
    
    
    private boolean DP( String s, int lo, int hi ){
        
        boolean res = dict.contains(s.substring(lo,hi));
        
        // base case
        if( lo+1 == hi ){
            memo.put( s.substring(lo,hi), res );
            return res;
        } 
        
        // recursion case
        for( int i = lo+1; i < hi; i++ ){
            String formerS = s.substring(lo,i);
            String latterS = s.substring(i,hi);
            boolean formerRes = false;
            boolean latterRes = false;
            
            if( memo.containsKey(formerS) )
                formerRes = memo.get(formerS);
            else{
                formerRes = DP( s, lo, i);
                memo.put( formerS, formerRes );
            }
            latterRes = dict.contains(latterS);

            res = res || (formerRes && latterRes);
        }

        return res;
    }
}
*/

//version2: DP
// NO NEED FOR HashMap!!
// s: 0 ~ l-1
// f(0,l-1) = dict.contains(0,l-1) || f(0,i)&&dict.contains(i,l-1) || f(0,i+1)&&dict.contains(i+1,l-1) || .....
// if s(0,l-1) returns true, e.g. s = ' a b c d e '
// for its fisrt char 'e' , it at most exists in all the following forms:
// 'e'   ,   'de'   ,   'cde'   ,   'bcde'   ,   'abcde'
// for each, we only need to check its prefix return true or not

class Solution {
    
    Set<String> dict;
    ArrayList<Boolean> memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        dict = new HashSet<>(wordDict);
        memo = new ArrayList<>();
        
        // edge case
        if( l == 1 ) return dict.contains(s);
        
        // general case
        return DP( s, l );
    }
    
    
    private boolean DP( String s, int index ){
        
        boolean res = dict.contains(s.substring(0,index));
        
        // base case
        if(  index == 1 ){
            memo.add(res);
            return res;
        } 
        
        // recursion case
        for( int i = 1; i < index; i++ ){
            String formerS = s.substring(0,i);
            String latterS = s.substring(i,index);
            boolean formerRes = false;
            boolean latterRes = false;
            
            if( i < memo.size() )
                formerRes = memo.get(i);
            else{
                formerRes = DP( s, i);
                memo.add( formerRes );
            }
            latterRes = dict.contains(latterS);

            res = res || (formerRes && latterRes);
        }

        return res;
    }
}