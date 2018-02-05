/*
    问题转化：
    1. 将每个valid的DNA想象成graph中的一个Node
    2. DNA经过 one mutation可以变为另外一个valid DNA，则二者之间有Edge
    3. 相当于从 start DNA找最短路径到 end DNA，（edge长度都为“1” ）
    4. BFS：from Start DNA to End DNA 的最短level数 
    
    
    思路：
    1 - 从起始节点找经one mutation的下一层valid nodes
         -如何找 one mutation valid nodes ？
         +方案一：对该节点每个位置的字符编程其他3种【A,C,G,T】,看是否valid
         +方案二：扫描bank中所有nodes，看是否可以one mutation到达（太大）
    2 - 对可以one mutation达到的nodes，压入queue，形成一层level（该点都可以经one mutation达到该level上nodes）
    3 - 对level中的nodes依次往外poll，找该level所有nodes的下层Nodes
    4 - 找nodes对应的下层level nodes时，检查是否是 end node
        
*/

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if( bank.length == 0 || start == null || end == null || start.length() != end.length() ) 
            return -1;
        
        Set<String> set = new HashSet<>();
        int len = bank.length;
        for( int i = 0; i < len; i++ )
            set.add(bank[i]);
        
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        
        char[] mutArr = {'A','C','G','T'};
        
        int lvl = 0;
        
        while( !q.isEmpty() ){
            lvl++;
            int size = q.size();
            for( int k = 0; k < size; k++ ){
                String node = q.poll();
                char[] startArr = node.toCharArray();
                for( int i = 0; i < startArr.length; i++ ){
                    char old = startArr[i];      //该位置尝试完所有情况，进行下一个位置之前，需要再变回去
                    for( int j = 0; j < mutArr.length; j++ ){
                        startArr[i] = mutArr[j];
                        String muteStr = new String(startArr);
                        if( set.contains(muteStr) ){
                            if( muteStr.equals(end) ) 
                                return lvl;
                            q.offer(muteStr);
                            set.remove(muteStr);
                        }
                    }
                    startArr[i] = old;       //该位置尝试完所有情况，进行下一个位置之前，再变回去
                }
            }
        }
        return -1;
    }
}