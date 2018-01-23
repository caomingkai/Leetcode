/*
 =====This is a problem about Graph===
 == nodes are the words int list
 == edges are nodes can transform into each other only by changing one character


version 1: BFS --- store all paths till meeting endWord ( means paths till this level are all shortest)

    1. need a queue to store each possible paths from beginWord
    2. spread out from beginWord, by level, toward the endWord
        2.1 nodes on current level can be shared by last level's nodes
        2.2 after handling all current level's nodes, delete them altogether
    3. till some level, we encouter the endWord, we don't go forward, and put valid path into res
        3.1 after we finish handling this level, break out the loop
        3.2 so we need a flag to signaling when to break out
    4. if there is no endWord, the queue will eventually become empty, and res is null;
*/

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new LinkedList<>();
        if( wordList == null || wordList.size() == 0 )
            return res;
        
        // used to lookup if one word having 'edge' to another word, Set is O(1) for lookup
        Set<String> wordSet = new HashSet<>(wordList);
        if( !wordSet.contains(endWord) )
            return res;
        
        Queue<List<String>> q = new LinkedList<>();
        List<String> prefix = new ArrayList<>();
        prefix.add(beginWord);
        q.offer(prefix);
        int l = beginWord.length();
        boolean breakFlag = false;
       
        
        while( !q.isEmpty() ){
            Set<String> toBeDeleted = new HashSet<>();
            int size = q.size();                        // handle by level
            for( int i = 0; i < size; i++ ){            // deal with last level's nodes (path)
                List<String> curPrefix = q.poll();      // get the path 
                String last = curPrefix.get(curPrefix.size()-1);      // get path's last word
                char[] chars = last.toCharArray();      // convert to char[]
                for( int j = 0; j < l; j++ ){           // find if this last word has adjacent nodes
                    for( char c = 'a'; c <= 'z'; c++ ){
                        char old = chars[j];
                        chars[j] = c;
                        String newLast = new String(chars);
                        if( wordSet.contains(newLast) ){    // check if has this new word
                            toBeDeleted.add(newLast);       // if have, store it for later deletion
                            List<String> newPrefix = new ArrayList<>(curPrefix);
                            newPrefix.add(newLast);         // update prefix path
                            if( newLast.equals(endWord) ){  // check if we meet the endWord        
                                res.add(new ArrayList<>(newPrefix) );
                                breakFlag = true;
                            }
                            q.offer(new ArrayList<>(newPrefix) );
                        }
                        chars[j] = old;                       // change back for next modification
                    }
                }
            }
            // after handling current level's node, if endWord resides on this level, terminate loop
            if( breakFlag )  return res;
            
            // delete words in toBeDeleted from wordSet
            wordSet.removeAll(toBeDeleted);
        }
        
        return res;
    }
}



/*
// Version 2.1  -- BFS 
// Key Point  :  1. 从 end 发散向前找 start，过程中存储每个点的后驱节点，用HashMap<String, List<String>> 存
//               2. 最后backtrack 所有valid路径

class Solution {

    class StringWithLevel {  
       String str;  
       int level;  
       public StringWithLevel(String str, int level) {  
          this.str = str;  
          this.level = level;  
       }  
    }  
    
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {  
       ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();  
       HashSet<String> unvisitedSet = new HashSet<String>();  
       unvisitedSet.addAll(dict);  
       unvisitedSet.add(start);  
       unvisitedSet.remove(end); 
       
       Map<String, List<String>> nextMap = new HashMap<String, List<String>>();  
       for (String e : unvisitedSet) {  
          nextMap.put(e, new ArrayList<String>());  
       }  
       LinkedList<StringWithLevel> queue = new LinkedList<StringWithLevel>();  
       queue.add(new StringWithLevel(end, 0));  
       boolean found = false;  
       int finalLevel = Integer.MAX_VALUE;  
       int curLevel = 0;  
       int preLevel = 0;  
       HashSet<String> visitedCurLevel = new HashSet<String>();  
       
       while (!queue.isEmpty()) {  
          StringWithLevel cur = queue.poll();  
          String curStr = cur.str;  
          curLevel = cur.level;  
          if(found && curLevel > finalLevel) {  
             break;  
          }  
          if (curLevel > preLevel) {  
             unvisitedSet.removeAll(visitedCurLevel);  
          }  
          preLevel = curLevel;  
          char[] curStrCharArray = curStr.toCharArray();  
          for (int i = 0; i < curStr.length(); ++i) {  
             char originalChar = curStrCharArray[i];  
             boolean foundCurCycle = false;  
             for (char c = 'a'; c <= 'z'; ++c) {  
                curStrCharArray[i] = c;  
                String newStr = new String(curStrCharArray);  
                if(c != originalChar && unvisitedSet.contains(newStr)) {  
                   nextMap.get(newStr).add(curStr);  
                   if(newStr.equals(start)) {  
                      found = true;  
                      finalLevel = curLevel;  
                      foundCurCycle = true;  
                      break;  
                   }  
                   if(visitedCurLevel.add(newStr)) {  
                      queue.add(new StringWithLevel(newStr, curLevel + 1));  
                   }  
                }  
             }  
             if(foundCurCycle) {  
                break;  
             }  
             curStrCharArray[i] = originalChar;  
         }  
       }  
       if(found) {  
           ArrayList<String> list = new ArrayList<String>();  
           list.add(start);  
           getPaths(start, end, list, finalLevel + 1, nextMap, res);  
       }  
       return res;  
    }  
    
    
    // 从起点起，根据每个点对应的后驱节点，backtrack得到最终路径
    private void getPaths(String cur, String end, ArrayList<String> list, int level, Map<String, List<String>> nextMap, ArrayList<ArrayList<String>> res) {  
       if(cur.equals(end)){  
          res.add(new ArrayList<String>(list));  
       }  
       else if(level > 0){  
          List<String> childrenSet = nextMap.get(cur);  
          for (String child : childrenSet) {  
             list.add(child);  
             getPaths(child, end, list, level - 1, nextMap, res);  
             list.remove(list.size() - 1);  
          }  
       }  
    }  
}
*/
    
    

/*
// Version 2:  DFS -- TLE 
//      KEY IDEA:  
//   1- find all valid path, store in a set
//   2- find the shortest length;
//   3- from the stored set, select the path with shortest length
class Solution {
    
    private Set<String> wordSet;
    private List<String> prefix = new ArrayList<>();
    private Set<List<String>> resList = new HashSet<>(); // used for test
    private int minLen;
    private int depthLimit;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if( wordList == null || wordList.size() == 0 )
            return new ArrayList<>();
        
        wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord))
            return new ArrayList<>();
        
        depthLimit = wordList.size();
        minLen = Integer.MAX_VALUE;
        int l = beginWord.length();
        prefix.add(beginWord);
        
        for( int i = 0; i < l; i++ )
            backtrack(beginWord, i, endWord );
        
        // System.out.println(resList);
        List<List<String>> res = new ArrayList<>();
        if( minLen ==Integer.MAX_VALUE)
            return new ArrayList<>();
        else{
            for( List<String> item: resList ){
                if( item.size() == minLen )
                    res.add(item);
            }
        }
        return res;
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
                    resList.add(new ArrayList<>(prefix) );  // used for test
                    minLen = Math.min( minLen, prefix.size() );
                    prefix.remove(prefix.size()-1);
                    return;
            } 
            
            if( wordSet.contains(newTemp) ){
                if( !prefix.contains( newTemp ) ){   
                    prefix.add( newTemp );
                    for( int i = 0; i < l; i++ ){
                                              // we have to put stopping contidion before if condition
                        if( i != pos )        // don't need to modify the position just beenmodified
                            backtrack( newTemp, i, target );
                    }
                    prefix.remove(prefix.size()-1);
                }                
            }
        }
    }
}
*/