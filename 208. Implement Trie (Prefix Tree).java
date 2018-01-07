


class Trie {
    class TrieNode{
        boolean isWord;
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
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */