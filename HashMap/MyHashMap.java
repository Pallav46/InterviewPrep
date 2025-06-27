import java.util.LinkedList;

/**
 * Custom HashMap Implementation using Separate Chaining
 * 
 * A hash table implementation that handles collisions using linked lists (chaining).
 * Supports generic key-value pairs and automatic resizing when load factor exceeds 0.75.
 * 
 * Key Features:
 * - Generic support for any key-value types
 * - Automatic resizing to maintain performance
 * - Collision handling via separate chaining
 * - Load factor management (0.75 threshold)
 * 
 * Time Complexities:
 * - Put: O(1) average, O(n) worst case (all elements in same bucket)
 * - Get: O(1) average, O(n) worst case
 * - Remove: O(1) average, O(n) worst case
 * - Resize: O(n) - rehashes all elements
 * 
 * Space Complexity: O(n) where n is number of key-value pairs
 * 
 * @param <K> Generic type for keys
 * @param <V> Generic type for values
 */
public class MyHashMap<K, V> {
    
    /**
     * Node class to represent key-value pairs in the hash table
     * Each node can be part of a linked list for collision handling
     */
    class Node {
        K key;          // Key of the key-value pair
        V value;        // Value associated with the key
        Node next;      // Reference to next node (for chaining)

        /**
         * Constructor to create a new node
         * @param key The key for this node
         * @param value The value for this node
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // ==================== INSTANCE VARIABLES ====================
    
    private int n;                          // Number of key-value pairs currently stored
    private int N;                          // Size of the hash table (number of buckets)
    private LinkedList<Node>[] buckets;     // Array of linked lists for separate chaining

    // ==================== CONSTRUCTOR ====================
    
    /**
     * Constructor to initialize HashMap with given capacity
     * Creates an array of LinkedLists to handle collisions via chaining
     * 
     * @param capacity Initial capacity of the hash table
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        this.N = capacity;              // Set initial capacity
        this.n = 0;                     // No elements initially
        this.buckets = new LinkedList[N]; // Create bucket array
        
        // Initialize each bucket with an empty LinkedList
        for (int i = 0; i < N; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // ==================== HASH FUNCTION ====================
    
    /**
     * Hash function to map keys to bucket indices
     * Uses built-in hashCode() and applies bit masking for positive values
     * 
     * Process:
     * 1. Get key's hashCode
     * 2. Apply bit mask (0x7FFFFFFF) to ensure positive value
     * 3. Use modulo to fit within bucket array size
     * 
     * @param key The key to hash
     * @return Index in the bucket array (0 to N-1)
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % N;
    }

    // ==================== CORE OPERATIONS ====================
    
    /**
     * Insert or update a key-value pair in the HashMap
     * 
     * Process:
     * 1. Calculate hash index for the key
     * 2. Search the bucket for existing key
     * 3. If key exists, update value; otherwise add new node
     * 4. Check load factor and resize if necessary
     * 
     * @param key The key to insert/update
     * @param value The value to associate with the key
     * Time Complexity: O(1) average, O(n) worst case
     */
    public void put(K key, V value) {
        int index = hash(key);  // Get bucket index
        
        // Search for existing key in the bucket
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value; // Update existing key's value
                return;
            }
        }
        
        // Key not found, add new node to bucket
        buckets[index].add(new Node(key, value));
        n++; // Increment size
        
        // Check if resize is needed (load factor > 0.75)
        if (n > 0.75 * N) {
            resize();
        }
    }

    /**
     * Retrieve value associated with given key
     * 
     * Process:
     * 1. Calculate hash index for the key
     * 2. Search through the bucket's linked list
     * 3. Return value if key found, null otherwise
     * 
     * @param key The key to search for
     * @return Value associated with key, or null if not found
     * Time Complexity: O(1) average, O(n) worst case
     */
    public V get(K key) {
        int index = hash(key);  // Get bucket index
        
        // Search for key in the bucket
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value; // Return value if key found
            }
        }
        return null; // Key not found
    }

    /**
     * Remove key-value pair from HashMap
     * 
     * Process:
     * 1. Calculate hash index for the key
     * 2. Search through bucket to find key
     * 3. Remove node from linked list if found
     * 4. Decrement size counter
     * 
     * @param key The key to remove
     * Time Complexity: O(1) average, O(n) worst case
     */
    public void remove(K key) {
        int index = hash(key);  // Get bucket index
        Node prev = null;
        
        // Search for key in the bucket
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    // Removing first node in the list
                    buckets[index].remove(node);
                } else {
                    // Bypass the node to remove it from linked list
                    prev.next = node.next;
                }
                n--; // Decrement size
                return;
            }
            prev = node; // Keep track of previous node
        }
    }

    /**
     * Check if the HashMap contains the specified key
     * 
     * Process:
     * 1. Calculate hash index for the key
     * 2. Search through the bucket's linked list
     * 3. Return true if key found, false otherwise
     * 
     * @param key The key to search for
     * @return true if key exists in the HashMap, false otherwise
     * Time Complexity: O(1) average, O(n) worst case
     */
    public boolean containsKey(K key) {
        int index = hash(key);  // Get bucket index
        
        // Search for key in the bucket
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true; // Key found
            }
        }
        return false; // Key not found
    }

    /**
     * Get a set of all keys in the HashMap
     * 
     * Process:
     * 1. Create a new ArrayList to store keys
     * 2. Iterate through all buckets
     * 3. Add each key to the list
     * 
     * @return List containing all keys in the HashMap
     * Time Complexity: O(n) - visits all elements
     */
    public java.util.List<K> keySet() {
        java.util.List<K> keys = new java.util.ArrayList<>();
        
        // Iterate through all buckets and collect keys
        for (int i = 0; i < N; i++) {
            for (Node node : buckets[i]) {
                keys.add(node.key); // Add key to the list
            }
        }
        
        return keys; // Return list of all keys
    }

    // ==================== RESIZING OPERATIONS ====================
    
    /**
     * Resize the hash table when load factor exceeds threshold
     * 
     * Process:
     * 1. Create new bucket array with double capacity
     * 2. Rehash all existing key-value pairs
     * 3. Update instance variables to use new buckets
     * 
     * This maintains performance by keeping load factor reasonable
     * Time Complexity: O(n) - must rehash all elements
     */
    private void resize() {
        int newCapacity = N * 2;  // Double the capacity
        
        // Create new bucket array
        @SuppressWarnings("unchecked")
        LinkedList<Node>[] newBuckets = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }

        // Rehash all existing nodes into new buckets
        for (LinkedList<Node> bucket : buckets) {
            for (Node node : bucket) {
                // Calculate new index based on new capacity
                int newIndex = (node.key.hashCode() & 0x7FFFFFFF) % newCapacity;
                newBuckets[newIndex].add(node);
            }
        }

        // Update instance variables
        this.buckets = newBuckets;
        this.N = newCapacity;
    }

    // ==================== UTILITY METHODS ====================
    
    /**
     * Get the current number of key-value pairs
     * @return Number of elements in the HashMap
     * Time Complexity: O(1)
     */
    public int size() {
        return n;
    }

    /**
     * Check if the HashMap is empty
     * @return true if no elements, false otherwise
     * Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Remove all key-value pairs from HashMap
     * Clears all buckets and resets size to 0
     * Time Complexity: O(N) where N is number of buckets
     */
    public void clear() {
        for (int i = 0; i < N; i++) {
            buckets[i].clear(); // Clear each bucket
        }
        n = 0; // Reset size counter
    }

    /**
     * Create string representation of HashMap
     * Format: {key1=value1, key2=value2, ...}
     * 
     * @return String representation of all key-value pairs
     * Time Complexity: O(n) - visits all elements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        // Iterate through all buckets and nodes
        for (int i = 0; i < N; i++) {
            for (Node node : buckets[i]) {
                sb.append(node.key).append("=").append(node.value).append(", ");
            }
        }
        
        // Remove trailing comma and space if map is not empty
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString(); // Return string representation of the map
    }

    // ==================== DEMONSTRATION AND TESTING ====================
    
    /**
     * Main method to demonstrate core HashMap functionality for interviews
     * Tests essential operations that are commonly asked in coding interviews
     */
    public static void main(String[] args) {
        // Create HashMap with initial capacity of 4
        MyHashMap<String, Integer> map = new MyHashMap<>(4);
        
        System.out.println("=== Core HashMap Operations for Interviews ===");
        
        // Test put operation
        System.out.println("\n1. Testing PUT operations:");
        map.put("apple", 5);
        map.put("banana", 3);
        map.put("orange", 7);
        map.put("grape", 2);
        System.out.println("After adding fruits: " + map);
        System.out.println("Size after adding 4 elements: " + map.size());
        
        // Test get operation
        System.out.println("\n2. Testing GET operations:");
        System.out.println("apple: " + map.get("apple"));
        System.out.println("banana: " + map.get("banana"));
        System.out.println("kiwi (not exists): " + map.get("kiwi"));
        
        // Test containsKey operation
        System.out.println("\n3. Testing CONTAINS_KEY operations:");
        System.out.println("Contains 'apple': " + map.containsKey("apple"));
        System.out.println("Contains 'kiwi': " + map.containsKey("kiwi"));

        // Test keySet operation
        System.out.println("\n4. Testing KEY_SET operations:");
        System.out.println("All keys: " + map.keySet());

        // Test update operation
        System.out.println("\n5. Testing UPDATE operations:");
        map.put("apple", 10); // Update existing key
        System.out.println("After updating apple: " + map);
        System.out.println("Updated apple to 10: " + map.get("apple"));

        // Test remove operation
        System.out.println("\n6. Testing REMOVE operations:");
        map.remove("banana");
        System.out.println("After removing banana: " + map);
        System.out.println("Contains 'banana' after removal: " + map.containsKey("banana"));
        System.out.println("Size after removal: " + map.size());

        // Test resize trigger (load factor > 0.75)
        System.out.println("\n7. Testing automatic RESIZE:");
        System.out.println("Current size: " + map.size() + ", Adding more elements to trigger resize...");
        map.put("mango", 4);
        map.put("pineapple", 6);
        map.put("strawberry", 8); // This should trigger resize
        System.out.println("After adding more items: " + map);
        System.out.println("Size after adding more items: " + map.size());
        System.out.println("All keys after resize: " + map.keySet());

        // Test clear operation
        System.out.println("\n8. Testing CLEAR operations:");
        System.out.println("Before clear - Size: " + map.size() + ", Is empty: " + map.isEmpty());
        map.clear();
        System.out.println("After clear: " + map);
        System.out.println("After clear - Size: " + map.size() + ", Is empty: " + map.isEmpty());

        // Test edge cases
        System.out.println("\n9. Testing edge cases:");
        System.out.println("Getting from empty map: " + map.get("nonexistent"));
        System.out.println("Keys from empty map: " + map.keySet());
        
        // Test with different data types
        System.out.println("\n10. Testing with Integer keys:");
        MyHashMap<Integer, String> intMap = new MyHashMap<>(2);
        intMap.put(1, "One");
        intMap.put(2, "Two");
        intMap.put(3, "Three");
        System.out.println("Integer map: " + intMap);
        System.out.println("Integer map keys: " + intMap.keySet());
        System.out.println("Size: " + intMap.size());
    }
}
