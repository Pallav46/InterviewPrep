package BinarySearchTree;

import java.util.*;

/**
 * Binary Search Tree (BST) Implementation
 * 
 * A binary tree where for each node:
 * - All values in left subtree are less than node's value
 * - All values in right subtree are greater than node's value
 * - Both left and right subtrees are also BSTs
 * 
 * This implementation provides the foundation for understanding tree operations
 * before moving to more advanced trees like AVL or Red-Black trees.
 * 
 * Time Complexities:
 * - Search: O(h) where h is height (O(log n) balanced, O(n) skewed)
 * - Insert: O(h)
 * - Delete: O(h)
 * - Traversals: O(n)
 * 
 * Space Complexity: O(n) for storage, O(h) for recursion stack
 * 
 * @author Interview Preparation
 */
public class BinarySearchTree {
    
    /**
     * Node class representing each element in the BST
     */
    private static class Node {
        int data;
        Node left;
        Node right;
        
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    private Node root;
    private int size;
    
    /**
     * Constructor - creates an empty BST
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }
    
    // ======================= CORE OPERATIONS =======================
    
    /**
     * Insert a value into the BST
     * @param data Value to insert
     */
    public void insert(int data) {
        root = insertHelper(root, data);
    }
    
    /**
     * Helper method for inserting a value
     * @param node Current node in recursion
     * @param data Value to insert
     * @return Root of the updated subtree
     */
    private Node insertHelper(Node node, int data) {
        // Base case: create new node
        if (node == null) {
            size++;
            return new Node(data);
        }
        
        // Recursive case: traverse to correct position
        if (data < node.data) {
            node.left = insertHelper(node.left, data);
        } else if (data > node.data) {
            node.right = insertHelper(node.right, data);
        }
        // Note: Duplicate values are not inserted
        
        return node;
    }
    
    /**
     * Search for a value in the BST
     * @param data Value to search for
     * @return true if found, false otherwise
     */
    public boolean search(int data) {
        return searchHelper(root, data);
    }
    
    /**
     * Helper method for searching a value
     * @param node Current node in recursion
     * @param data Value to search for
     * @return true if found, false otherwise
     */
    private boolean searchHelper(Node node, int data) {
        // Base case: not found
        if (node == null) {
            return false;
        }
        
        // Found the value
        if (data == node.data) {
            return true;
        }
        
        // Recursive search in appropriate subtree
        if (data < node.data) {
            return searchHelper(node.left, data);
        } else {
            return searchHelper(node.right, data);
        }
    }
    
    /**
     * Delete a value from the BST
     * @param data Value to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(int data) {
        int originalSize = size;
        root = deleteHelper(root, data);
        return size < originalSize;
    }
    
    /**
     * Helper method for deleting a value
     * @param node Current node in recursion
     * @param data Value to delete
     * @return Root of the updated subtree
     */
    private Node deleteHelper(Node node, int data) {
        // Base case: value not found
        if (node == null) {
            return null;
        }
        
        // Find the node to delete
        if (data < node.data) {
            node.left = deleteHelper(node.left, data);
        } else if (data > node.data) {
            node.right = deleteHelper(node.right, data);
        } else {
            // Node to be deleted found
            size--;
            
            // Case 1: No children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }
            
            // Case 2: One child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            
            // Case 3: Two children
            // Find inorder successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteHelper(node.right, successor.data);
            size++; // Compensate for extra decrement in recursive call
        }
        
        return node;
    }
    
    /**
     * Find minimum value in a subtree
     * @param node Root of subtree
     * @return Node with minimum value
     */
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * Find maximum value in a subtree
     * @param node Root of subtree
     * @return Node with maximum value
     */
    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    // ======================= TRAVERSAL METHODS =======================
    
    /**
     * Inorder traversal (Left -> Root -> Right)
     * Results in sorted order for BST
     */
    public void inorderTraversal() {
        System.out.print("Inorder: ");
        inorderHelper(root);
        System.out.println();
    }
    
    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.data + " ");
            inorderHelper(node.right);
        }
    }
    
    /**
     * Preorder traversal (Root -> Left -> Right)
     */
    public void preorderTraversal() {
        System.out.print("Preorder: ");
        preorderHelper(root);
        System.out.println();
    }
    
    private void preorderHelper(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderHelper(node.left);
            preorderHelper(node.right);
        }
    }
    
    /**
     * Postorder traversal (Left -> Right -> Root)
     */
    public void postorderTraversal() {
        System.out.print("Postorder: ");
        postorderHelper(root);
        System.out.println();
    }
    
    private void postorderHelper(Node node) {
        if (node != null) {
            postorderHelper(node.left);
            postorderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    /**
     * Level order traversal (BFS)
     */
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Level order: Tree is empty");
            return;
        }
        
        java.util.Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        System.out.print("Level order: ");
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");
            
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        System.out.println();
    }
    
    // ======================= UTILITY METHODS =======================
    
    /**
     * Get the height of the tree
     * @return Height of the tree
     */
    public int getHeight() {
        return getHeightHelper(root);
    }
    
    private int getHeightHelper(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeightHelper(node.left), getHeightHelper(node.right));
    }
    
    /**
     * Get the size of the tree
     * @return Number of nodes in the tree
     */
    public int size() {
        return size;
    }
    
    /**
     * Check if the tree is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Find minimum value in the tree
     * @return Minimum value
     */
    public int findMinValue() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return findMin(root).data;
    }
    
    /**
     * Find maximum value in the tree
     * @return Maximum value
     */
    public int findMaxValue() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return findMax(root).data;
    }
    
    /**
     * Count total number of nodes (alternative implementation)
     * @return Number of nodes
     */
    public int countNodes() {
        return countNodesHelper(root);
    }
    
    private int countNodesHelper(Node node) {
        if (node == null) return 0;
        return 1 + countNodesHelper(node.left) + countNodesHelper(node.right);
    }
    
    /**
     * Check if tree is a valid BST
     * @return true if valid BST, false otherwise
     */
    public boolean isValidBST() {
        return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean isValidBSTHelper(Node node, int min, int max) {
        if (node == null) return true;
        
        if (node.data <= min || node.data >= max) return false;
        
        return isValidBSTHelper(node.left, min, node.data) &&
               isValidBSTHelper(node.right, node.data, max);
    }
    
    // ======================= ADVANCED OPERATIONS =======================
    
    /**
     * Find kth smallest element in BST
     * @param k Position (1-indexed)
     * @return kth smallest element
     */
    public int kthSmallest(int k) {
        List<Integer> result = new ArrayList<>();
        inorderForKth(root, result, k);
        if (k <= result.size()) {
            return result.get(k - 1);
        }
        throw new IllegalArgumentException("k is larger than tree size");
    }
    
    private void inorderForKth(Node node, List<Integer> result, int k) {
        if (node == null || result.size() >= k) return;
        
        inorderForKth(node.left, result, k);
        result.add(node.data);
        inorderForKth(node.right, result, k);
    }
    
    /**
     * Find lowest common ancestor of two nodes
     * @param p First value
     * @param q Second value
     * @return LCA value
     */
    public int findLCA(int p, int q) {
        Node lca = findLCAHelper(root, p, q);
        if (lca == null) throw new IllegalArgumentException("One or both values not found");
        return lca.data;
    }
    
    private Node findLCAHelper(Node node, int p, int q) {
        if (node == null) return null;
        
        // If both p and q are smaller, LCA is in left subtree
        if (p < node.data && q < node.data) {
            return findLCAHelper(node.left, p, q);
        }
        
        // If both p and q are greater, LCA is in right subtree
        if (p > node.data && q > node.data) {
            return findLCAHelper(node.right, p, q);
        }
        
        // If one is smaller and one is greater, current node is LCA
        return node;
    }
    
    /**
     * Convert BST to sorted array
     * @return Sorted array of BST elements
     */
    public int[] toSortedArray() {
        List<Integer> result = new ArrayList<>();
        inorderToArray(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    private void inorderToArray(Node node, List<Integer> result) {
        if (node != null) {
            inorderToArray(node.left, result);
            result.add(node.data);
            inorderToArray(node.right, result);
        }
    }
    
    /**
     * Find range of values between min and max
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return List of values in range
     */
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }
    
    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;
        
        // If current node is in range, add it
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }
        
        // Recursively search left subtree if current node is greater than min
        if (node.data > min) {
            rangeQueryHelper(node.left, min, max, result);
        }
        
        // Recursively search right subtree if current node is less than max
        if (node.data < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }
    
    /**
     * Display tree structure
     */
    public void displayTree() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        
        System.out.println("Tree structure:");
        displayTreeHelper(root, "", true);
    }
    
    private void displayTreeHelper(Node node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.data);
            
            String newPrefix = prefix + (isLast ? "    " : "│   ");
            
            if (node.left != null || node.right != null) {
                if (node.right != null) {
                    displayTreeHelper(node.right, newPrefix, node.left == null);
                }
                if (node.left != null) {
                    displayTreeHelper(node.left, newPrefix, true);
                }
            }
        }
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing BST operations
     */
    public static void main(String[] args) {
        System.out.println("=== Binary Search Tree Implementation Demo ===\n");
        
        BinarySearchTree bst = new BinarySearchTree();
        
        // Test insertions
        System.out.println("1. Testing Insertions:");
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        
        for (int value : values) {
            System.out.println("Inserting: " + value);
            bst.insert(value);
        }
        
        System.out.println("\nTree after insertions:");
        bst.displayTree();
        System.out.println("Tree size: " + bst.size());
        System.out.println("Tree height: " + bst.getHeight());
        System.out.println("Is valid BST: " + bst.isValidBST());
        
        // Test traversals
        System.out.println("\n2. Testing Traversals:");
        bst.inorderTraversal();
        bst.preorderTraversal();
        bst.postorderTraversal();
        bst.levelOrderTraversal();
        
        // Test search
        System.out.println("\n3. Testing Search:");
        int[] searchValues = {40, 55, 10, 100};
        for (int value : searchValues) {
            System.out.println("Search " + value + ": " + bst.search(value));
        }
        
        // Test min/max
        System.out.println("\n4. Testing Min/Max:");
        System.out.println("Minimum value: " + bst.findMinValue());
        System.out.println("Maximum value: " + bst.findMaxValue());
        
        // Test advanced operations
        System.out.println("\n5. Testing Advanced Operations:");
        System.out.println("3rd smallest element: " + bst.kthSmallest(3));
        System.out.println("LCA of 20 and 40: " + bst.findLCA(20, 40));
        System.out.println("Range query [30, 50]: " + bst.rangeQuery(30, 50));
        
        // Convert to sorted array
        System.out.println("BST as sorted array: " + Arrays.toString(bst.toSortedArray()));
        
        // Test deletions
        System.out.println("\n6. Testing Deletions:");
        int[] deleteValues = {25, 30, 50}; // Test all deletion cases
        
        for (int value : deleteValues) {
            System.out.println("\nDeleting: " + value);
            boolean deleted = bst.delete(value);
            System.out.println("Deleted: " + deleted);
            bst.displayTree();
            System.out.println("Size after deletion: " + bst.size());
            bst.inorderTraversal();
        }
        
        // Test edge cases
        System.out.println("\n7. Testing Edge Cases:");
        
        // Empty tree operations
        BinarySearchTree emptyBST = new BinarySearchTree();
        System.out.println("Empty tree search 10: " + emptyBST.search(10));
        System.out.println("Empty tree delete 10: " + emptyBST.delete(10));
        System.out.println("Empty tree is empty: " + emptyBST.isEmpty());
        
        // Single node tree
        BinarySearchTree singleNodeBST = new BinarySearchTree();
        singleNodeBST.insert(42);
        System.out.println("Single node tree height: " + singleNodeBST.getHeight());
        System.out.println("Single node tree size: " + singleNodeBST.size());
        singleNodeBST.inorderTraversal();
        
        // Duplicate insertion
        System.out.println("Inserting duplicate 42:");
        int originalSize = singleNodeBST.size();
        singleNodeBST.insert(42);
        System.out.println("Size after duplicate insert: " + singleNodeBST.size() + 
                         " (should be same as before: " + originalSize + ")");
        
        System.out.println("\n=== BST Demo Complete ===");
    }
}
