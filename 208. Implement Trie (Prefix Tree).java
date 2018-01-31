


class Trie {
    class TrieNode{
        boolean isWord;
        int childrenNum;   // denote: from this Node on, carrying how many words
        TrieNode[] children = new TrieNode[26];
        public TrieNode(){ }
    }
    
    TrieNode root;
   
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(); 
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curNode = root;
        for( int i=0; i<word.length(); i++ ){
            char c = word.charAt(i);
            if( curNode.children[ c-'a'] == null )
                curNode.children[ c-'a'] = new TrieNode();
            curNode.childrenNum++;
            curNode = curNode.children[ c-'a'];
        }
        curNode.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curNode = root;
        for( int i=0; i<word.length(); i++ ){
            char c = word.charAt(i);
            if( curNode.children[c-'a'] == null )
                return false;
            curNode = curNode.children[c-'a'];
        }
        return curNode!=null && curNode.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curNode = root;
        for( int i=0; i<prefix.length(); i++ ){
            char c = prefix.charAt(i);
            if( curNode.children[c-'a'] == null )
                return false;
            curNode = curNode.children[c-'a'];
        }
        return curNode != null;
    }
    
    /** Returns if we successfully delete such a word */
    public boolean delete( String word ){
        if( !search(word) )
            return false;
        
        TrieNode curNode = root;
        for( int i=0; i<word.length(); i++ ){
            char c = word.charAt(i);
            if( curNode.children[c-'a'].childrenNum == 1 ){
                curNode.children[c-'a'] = null;
                return true;
            }else{
                curNode.childrenNum--;
                curNode = curNode.children[c-'a']; 
            }
        }
        
        // 能运行至此处，表明Trie中还有别的prefix需要用到word的所有letter前缀；
        // 然而，不同的却是，这个单词不会再存在于trie中了，所以isWord=false
        curNode.isWord = false;
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */