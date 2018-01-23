/*
    1. scan from begin to end word by word, to temporarily store them in a ArrayList
    2. when stop ? the scan pointer point to a word will exceed the L, we stop scan
    3. handle the readin cache, put result in output
    4. after finish handling, we go to the beginning of the loop
    5. corner case: last line
        5.1 how to identify last line? pointer >= total num of words, while putting them into casch
        5.2 based on flag set by 5.1, we handle sperately
        
    6. helper(): make justification
        6.1 must know total num of characters: nc ; and total words nw
        6.2 space = (L-nc) / (nw-1) + 1
        6.3 for last one we add (L-nc) - (nw-1)*space
*/

class Solution {
    boolean isLastLine = false;   // too many parameters need to be passed out, so make it global
    public List<String> fullJustify(String[] words, int maxWidth) {
        
        List<String> res = new ArrayList<>();
        if( words == null && words.length == 0 ) return res;
        int l = words.length;
        int i = 0;
        
        while(  i < l ){
            List<String> cache = new ArrayList<>();
            int sum = 0;
            String word = words[i];
            i = getOneLine( i, words, cache, maxWidth);  // i already increament inside method     
            
            justify( cache, maxWidth, res );             // justify the cached letters
            if(isLastLine) break;
        }
        return res;
    }
    
    // from ith word to form a line
    private int getOneLine( int i, String[] words,  List<String> cache, int maxWidth ){
        int l = words.length;
        int nwValid = 0;
        int nc = words[i].length()+1;
        while( nc-1 <= maxWidth ){
            cache.add( words[i] );
            if( nc-1 == maxWidth ) return i+1;
            if( i == l-1 ) {
                isLastLine = true;
                i++;
                break;   // don't go to last statement
            }
            i++;
            nc += words[i].length()+1;
        }
        return i;
    }
    
    private void justify( List<String> cache, int L, List<String> res ){
        List<String> temp = new ArrayList<>();
        int nw = cache.size();                // num of words
        int nc = 0;                           // num of characters
        for( String s : cache )  nc += s.length();
        int nsTrail = (L-nc) - (nw-1);
        if(isLastLine){
            int i = 0;
            while( i < nw-1 ){   // not the lastSpace
                String w = cache.get(i++);             
                temp.add( w + " " );
            }
            temp.add( cache.get(i) + createSpace(nsTrail));// last space
            String oneLine = "";
            for( String item: temp )
                oneLine += item;
            res.add( oneLine );
            return;
        }
        
        int i = 0;
        if( nw == 1 ){
             String w = cache.get(i);
             temp.add( w + createSpace(L-nc) );
        }else{
            int curSum = 0;                         // currently assign how many spaces
            while( i < nw-1 ){
                String w = cache.get(i);
                int ns = 0;                           // num of spaces, when only one word
                if( (L-nc-curSum) % (nw-1-i) == 0 )
                    ns = (L-nc-curSum) / (nw-1-i);
                else
                    ns = (L-nc-curSum) / (nw-1-i) + 1;
                curSum += ns;
                temp.add( w + createSpace(ns) );
                i++;
            }
            temp.add(cache.get(i));                // last word: no space to its right
        }
        String oneLine = "";
        for( String item: temp )
            oneLine += item;
        res.add( oneLine );
    }
    
    
    // create whitespace
    private String createSpace( int n ){
        String s = "";
        for( int i = 0; i < n; i++ )
            s += " ";
        return s;
    }
}