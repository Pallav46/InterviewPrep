package Trie;
import java.util.ArrayList;
import java.util.List;

public class Name_Phone {
    static class Contact {
        String name;
        String phone;

        public Contact(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "[" + name + ", " + phone + "]";
        }
    }

    static class TrieNode {
        TrieNode[] children;
        List<Contact> contacts; // List to store contacts that end at this node
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26]; // for a-z lowercase letters
            this.contacts = new ArrayList<>();
            this.isEnd = false;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Insert based on name
        public void insert(Contact contact) {
            TrieNode node = root;

            for (char ch : contact.name.toLowerCase().toCharArray()) {
                int index = ch - 'a'; // Calculate index for the character
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode(); // Create a new TrieNode if it doesn't exist
                }
                node = node.children[index];
            }

            node.isEnd = true;
            node.contacts.add(contact); // Add the contact to the node's list
        }

        // DFS to find all contacts from a given node
        private void dfs(TrieNode node, List<Contact> result) {
            if (node.isEnd) {
                result.addAll(node.contacts); // Add all contacts at the end of a word
            }
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    dfs(node.children[i], result); // Continue DFS for each child
                }
            }
        }

        // Search for contacts based on name prefix
        public List<Contact> searchByNamePrefix(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toLowerCase().toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return new ArrayList<>(); // Return an empty list if prefix is not found
                }
                node = node.children[index];
            }

            // Collect all contacts starting with the prefix
            List<Contact> result = new ArrayList<>();
            dfs(node, result); // Perform DFS to gather all matching contacts
            return result;
        }

        // Search for contacts based on phone prefix
        public List<Contact> searchByPhonePrefix(List<Contact> contacts, String phonePrefix) {
            List<Contact> result = new ArrayList<>();
            for (Contact contact : contacts) {
                if (contact.phone.startsWith(phonePrefix)) {
                    result.add(contact); // Add contact if phone starts with the given prefix
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        // Create a list of contacts
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Pallav", "+91 6200845646"));
        contacts.add(new Contact("Pallav", "+91 7903751464"));
        contacts.add(new Contact("Ishaan", "+91 9876543210"));
        contacts.add(new Contact("Ubaid", "+44 7896541230"));
        contacts.add(new Contact("Priya", "+91 8509876543"));
        contacts.add(new Contact("Olivia", "+1 2345678901"));

        // Create a Trie and insert contacts by name
        Trie trie = new Trie();
        for (Contact contact : contacts) {
            trie.insert(contact); // Insert contact name into the Trie
        }

        // Example 1: Search by name prefix
        String namePrefix = "P";
        System.out.println("Contacts with name prefix '" + namePrefix + "':");
        List<Contact> nameMatches = trie.searchByNamePrefix(namePrefix);
        for (Contact contact : nameMatches) {
            System.out.println(contact);
        }

        // Example 2: Search by phone prefix
        String phonePrefix = "+91";
        System.out.println("\nContacts with phone prefix '" + phonePrefix + "':");
        List<Contact> phoneMatches = trie.searchByPhonePrefix(contacts, phonePrefix);
        for (Contact contact : phoneMatches) {
            System.out.println(contact);
        }
    }
}
