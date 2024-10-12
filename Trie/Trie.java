package Trie;
import java.util.ArrayList;
import java.util.List;

class TrieNode {
    char ch;
    TrieNode[] children;
    boolean isEnd;

    public TrieNode(char ch) {
        this.ch = ch;
        this.children = new TrieNode[26];
        this.isEnd = false;
    }
}

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode('\0'); // Initialize root with a dummy character
    }

    public void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a'; // Calculate the index for the character
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(ch); // Assign new TrieNode to the correct index
            }
            node = node.children[index]; // Move to the next node
        }

        node.isEnd = true; // Mark the end of the word
    }

    public boolean search(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }

        return node.isEnd;
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }
    
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEnd) {
                return false; // Word does not exist
            }
            current.isEnd = false;
            return current.children.length == 0; // If no children, it's safe to delete this node
        }

        char ch = word.charAt(index);
        TrieNode node = current.children[ch - 'a'];
        
        if (node == null) {
            return false; // Word does not exist
        }

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);
    
        if (shouldDeleteCurrentNode) {
            current.children[ch - 'a'] = null;
            return current.children.length == 0 && !current.isEnd;
        }
        
        return false;
    }    

    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }

        return true;
    }

    // Updated dfs method to correctly gather words
    public void dfs(TrieNode node, String prefix, List<String> result) {
        if (node.isEnd) {
            result.add(prefix);
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                dfs(node.children[i], prefix + node.children[i].ch, result);
            }
        }
    }

    public List<String> phoneDirectory(String prefix) {
        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return new ArrayList<>();
            }
            node = node.children[index];
        }

        List<String> current = new ArrayList<>();
        dfs(node, prefix, current);
        
        return current;
    }

    // Helper function to print the trie structure
    public void printTrie(TrieNode node, String prefix) {
        if (node == null) return;

        if (node.isEnd) {
            System.out.println(prefix); // Print the word
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                printTrie(node.children[i], prefix + node.children[i].ch);
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert some words into the trie
        trie.insert("apple");
        trie.insert("app");
        trie.insert("ap");
        trie.insert("bat");
        trie.insert("ball");
        trie.insert("batman");
        trie.insert("cat");
        trie.insert("dog");
        trie.insert("doggy");
        trie.insert("doghouse");

        // Test search functionality
        System.out.println("Searching for 'apple': " + trie.search("apple")); // true
        System.out.println("Searching for 'bat': " + trie.search("bat"));     // true
        System.out.println("Searching for 'doggy': " + trie.search("doggy")); // true
        System.out.println("Searching for 'house': " + trie.search("house")); // false

        // Test deletion functionality
        trie.delete("apple");
        System.out.println("After deleting 'apple', searching for 'apple': " + trie.search("apple")); // false
        System.out.println("Searching for 'app': " + trie.search("app")); // true

        // Test startsWith functionality
        System.out.println("Does any word start with 'ba'? " + trie.startsWith("ba")); // true
        System.out.println("Does any word start with 'ap'? " + trie.startsWith("ap")); // true

        // Test phoneDirectory method for prefix "ba"
        List<String> directories = trie.phoneDirectory("ba");
        System.out.println("Phone directory for prefix 'ba': ");

       System.out.println(directories);
        

        // Print entire Trie structure
        System.out.println("Complete Trie structure:");
        trie.printTrie(trie.root, "");
    }
}
