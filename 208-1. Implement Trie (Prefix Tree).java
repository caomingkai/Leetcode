

class TrieNode {
    
    TrieNode[] children = new TrieNode[26];
    boolean isLeaf;
    // Constructor
    public TrieNode() {
    }
}

public class Trie {
    
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        insert(word.toCharArray(), 0, root);
    }
    
    public void insert(char[] word, int i, TrieNode node) {
        
        if (i == word.length) {
            node.isLeaf = true;
            return;
        }
        
        char c = word[i];
        if (node.children[c - 'a'] == null) {
            node.children[c - 'a'] = new TrieNode();
        }
        
        insert(word, i + 1, node.children[c - 'a']);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return search(word.toCharArray(), 0, root);
    }
    
    public boolean search(char[] word, int i, TrieNode node) {
        
        if (i == word.length) {
            return node.isLeaf;
        }
        
        char c = word[i];
        
        return node.children[c - 'a'] != null && search(word, i + 1, node.children[c - 'a']);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return startsWith(prefix.toCharArray(), 0, root);
    }
    
    public boolean startsWith(char[] word, int i, TrieNode node) {
        
        if (i == word.length) {
            return true;
        }
        
        char c = word[i];
        return node.children[c - 'a'] != null && startsWith(word, i + 1, node.children[c - 'a']);
    }
}
