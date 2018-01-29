class Solution {
    public String alienOrder(String[] words) {
        
        // corner case
        if( words == null || words.length == 0 )
            return "";

        // general
        // 1. find out directed edges info, and nodes number(HashSet)
        
        int len = words.length;
        Set<Character> nodeSet = new HashSet();
        Set<String> edgeSet = new HashSet();  // 只有primitive， 才能够让set的唯一性发挥作用

        for( int i = 0; i < len; i++ ){
            String curr = words[i];
            updateNodeInfo(curr, nodeSet);
            if( i+1 < len ){
                String next = words[i+1];
                updateNodeInfo(next, nodeSet);
                updateEdgeInfo(curr, next, edgeSet);
            }
        }
        
        // edge case 2:
        String res = "";
        if( words.length == 1 ){
            for( Character c : nodeSet ){
                res += c;
            }
            return res;
        }
            
        // 2. create directed map
        int nodeNum = nodeSet.size();
        int edgeNum = edgeSet.size();
        int[][] graph = new int[26][26];  // directed graph
        int[] degree = new int[26];
        
        for(  String edge : edgeSet ){
            char s = edge.charAt(0);
            char e = edge.charAt(1);
            graph[s-'a'][e-'a'] = 1;
            degree[e-'a']++;
        }

        
        // 3. find topological order of it ( need to check if valid)
        int nodeNumWoCycle = 0;
        Queue<Character> q = new LinkedList();    // all characters in queue has degree 0
        for( int i = 0; i < 26; i++ ){
            char temp = (char)(i+'a');
            if( nodeSet.contains(temp) && degree[i] == 0 ){
                q.offer(temp);
            }
        }
        
        List<Character> list = new ArrayList();
        while( q.size() > 0 ){
            char curChar = q.poll();
            list.add(curChar);
            nodeNumWoCycle++;
            for( int i = 0; i < 26; i++ ){
                if(graph[curChar-'a'][i] == 1){
                    degree[i]--;
                    char temp = (char)(i+'a');
                    if(  nodeSet.contains(temp) && degree[i] == 0)
                        q.offer(temp);
                }
            }
        }
        
        System.out.println(nodeNumWoCycle);
        System.out.println(nodeNum);
        System.out.println(list);
        
        if( nodeNumWoCycle == nodeNum ){
            for( Character c : list )
                res += c;
            return res;
        }
       
        return "";
        
    }
    
    private void updateNodeInfo( String curr, Set<Character> nodeSet ){
        int l = curr.length();
        for( int i = 0; i < l; i++ ){
            nodeSet.add(curr.charAt(i));
        }
    }
    
    private void updateEdgeInfo( String curr, String next, Set<String> edgeSet){
        int l1 = curr.length();
        int l2 = next.length();
        int i = 0;
        
        while( i<l1 && i<l2 && curr.charAt(i) == next.charAt(i) )
            i++;
        if( i != l1 && i != l2 ){
            String res = curr.charAt(i) + "" + next.charAt(i);
            edgeSet.add(res);
        }
    }
}