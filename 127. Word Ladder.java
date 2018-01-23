/*
==============================================
version 1.  one-end BFS  -- graph
==============================================

    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot", dog","lot","log","cog"]
             
                  /- dot -- dog --\
        hit-- hot     |      |     cog
                  \_ lot -- log __/
        
    traverse by level, whenever a letter change, level=level+1
    words transform:   hit -> hot -> lot -> log -> cog
              level:    1      2      3      4      5
            
            
//  --- 为什么BFS要快？ 为什么DFS会超时？？
//  --- 因为如果存在有环的情况：
//     -- BFS + visted[] 仅遍历一边数组就能找到最短路径 （ 长度为‘1’ 的变， 垂挂下来，找队短路径 ）
//     -- DFS 则会因为环的存在，对图中的点进行重复遍历
//  "aaa"
//  "abe"
//  ["aba","eaa","abe","eba"]
//   如下图： 
//
//       /------------ aba -------\
//    aaa               |         abe
//       \--eaa - eba --|
//
//==============================================
*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if( wordList == null || wordList.size() == 0 )
            return 0;
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 1;
        int l = endWord.length();
        
        while( !q.isEmpty() ){
            level++;
            int size = q.size();
            for( int i = 0; i < size; i++ ){
                String str = q.poll();
                for( char c = 'a'; c <= 'z'; c++ ){
                    for( int j = 0; j < l; j++ ){
                        // better way to change letter
                        char[] word = str.toCharArray();
                        word[j] = c;
                        String newStr = new String(word);
                        if( wordSet.contains(newStr) ){
                            if( newStr.equals(endWord) )
                                return level;
                            q.offer(newStr);
                            wordSet.remove(newStr);    // avoid visiting again, avoid deal loop
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}


/*
==============================================
// version 2: two-end BFS   
==============================================
    
b^(d/2) + b^(d/2) is much less than b^d. ( b is branch factor, d is depth )

"The idea behind bidirectional search is to run two simultaneous searches—one forward from
the initial state and the other backward from the goal—hoping that the two searches meet in
the middle. The motivation is that b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth. "

1. From two ends: beginWord and endWord, change a letter each time
2. whenever create a modified word which is in WordList, check if it exists in another set
        -- here we use set for fast lookup
3. keep two sets grow equally, this way can achieve better time complexity

*/

/*
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if( wordList == null || wordList.size() == 0 )
            return 0;
        
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> begSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        
        begSet.add( beginWord );
        endSet.add( endWord );
        if( !wordSet.contains( endWord ) )
            return 0;
        
        int cnt = 1;
        int l = endWord.length();
        
        // while( !wordSet.isEmpty() ){ //wrong! since there is case: begin/end don't exist in wordList
        while( !begSet.isEmpty() && !endSet.isEmpty() ){
            if( begSet.size() > endSet.size() ){
                Set<String> cur = begSet;
                begSet = endSet;
                endSet = cur;
            }
            
            Set<String> temp = new HashSet<String>();
            for( String str: begSet ){      // update begSet/ endSet with next level's words
                char[] chars = str.toCharArray();
                for( int i = 0; i < l; i++ ){
                    for( char c = 'a'; c <= 'z'; c++ ){
                        char old = chars[i];
                        chars[i] = c;
                        String newStr = new String(chars);
                        if( endSet.contains(newStr) )
                            return cnt+1;
                        if( wordSet.contains(newStr) ){
                            temp.add(newStr);
                            wordSet.remove(newStr);
                        }
                        chars[i] = old;
                    }
                }
            }
            begSet = temp;
            cnt++;     // for next loop
        }
        return 0;
    }
    

}
*/


/*

//==============================================
//  version 3: DFS --- TLE 
//  
//  "aaa"
//  "abe"
//  ["aba","eaa","abe","eba"]
//   如下图： 
//
//  --- 为什么DFS会超时？？
//  --- 因为如果存在有环的情况： 
//     -- BFS + visted[] 仅遍历一边数组就能找到最短路径 （ 长度为‘1’ 的变， 垂挂下来，找队短路径 ）
//     -- DFS 则会因为环的存在，对图中的点进行重复遍历
//
//       /------------ aba -------\
//    aaa               |         abe
//       \--eaa - eba --|
//
//==============================================

class Solution {
    
    private Set<String> wordSet;
    private Set<String> prefix = new HashSet<>();
    private Set<Set<String>> resList = new HashSet<>(); // used for test
    private int res;
    private int depthLimit;
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if( wordList == null || wordList.size() == 0 )
            return 0;
        
        wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord))
            return 0;
        
        depthLimit = wordList.size();
        res = Integer.MAX_VALUE;
        int l = beginWord.length();
        prefix.add(beginWord);
        
        for( int i = 0; i < l; i++ )
            backtrack(beginWord, i, endWord );
        
        // System.out.println(resList);
        return res==Integer.MAX_VALUE ? 0 : res;
    }
    
    
    // change the letter on position "pos" of String str
    private void backtrack( String str, int pos, String target ){
        int l = target.length();
        if( prefix.size() > depthLimit )  return;        // 进行深度限制，最短路径不可能比字典长度还长
        
        for( char c = 'a'; c <= 'z'; c++ ){
            String newTemp = str.substring(0,pos) + c + str.substring(pos+1);
            if( newTemp.equals(str) )            // avoid wordList contains beginWord, don't count it in
                continue;
            
            if( newTemp.equals(target) ){ 
                    prefix.add(newTemp);
                    resList.add(new HashSet<>(prefix) );  // used for test
                    res = Math.min( res, prefix.size() );
                    prefix.remove(newTemp);
                    return;
            } 
            
            if( wordSet.contains(newTemp) ){
                if( prefix.add( newTemp ) ){   
                    for( int i = 0; i < l; i++ ){
                                              // we have to put stopping contidion before if condition
                        if( i != pos )        // don't need to modify the position just beenmodified
                            backtrack( newTemp, i, target );
                    }
                    prefix.remove( newTemp );
                }                
            }
        }
    }
}
*/