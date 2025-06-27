package AVLTree;

/**
 * AVL Tree (Adelson-Velsky and Landis Tree) Implementation
 * 
 * An AVL tree is a self-balancing binary search tree where the heights of the two child
 * subtrees of any node differ by at most one. This ensures O(log n) time complexity for
 * all basic operations (search, insert, delete).
 * 
 * Key Properties:
 * - Height-balanced: |height(left) - height(right)| ≤ 1 for all nodes
 * - Self-balancing through rotations
 * - Maintains BST property: left < root < right
 * 
 * Time Complexities:
 * - Search: O(log n)
 * - Insert: O(log n)
 * - Delete: O(log n)
 * - All operations guaranteed due to balanced height
 * 
 * @author Interview Preparation
 */
public class AVLTree {
    
    /**
     * Node class representing each element in the AVL tree
     */
    private static class Node {
        int data;
        Node left;
        Node right;
        int height;
        
        /**
         * Constructor for creating a new node
         * @param data The value to store in the node
         */
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1; // New nodes start with height 1
        }
    }
    
    private Node root;
    private int size;
    
    /**
     * Constructor - creates an empty AVL tree
     */
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }
    
    // ======================= UTILITY METHODS =======================
    
    /**
     * Get the height of a node
     * @param node The node to get height for
     * @return Height of the node (0 if node is null)
     */
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }
    
    /**
     * Calculate balance factor of a node
     * Balance factor = height(left subtree) - height(right subtree)
     * @param node The node to calculate balance factor for
     * @return Balance factor (-1, 0, 1 for balanced; outside range means unbalanced)
     */
    private int getBalanceFactor(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }
    
    /**
     * Update the height of a node based on its children
     * @param node The node to update height for
     */
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }
    
    /**
     * Find the node with minimum value in a subtree (leftmost node)
     * @param node Root of the subtree
     * @return Node with minimum value
     */
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // ======================= ROTATION METHODS =======================
    
    /**
     * Right rotation (used for Left-Left case)
     * 
     *     y                x
     *    / \              / \
     *   x   T3    -->    T1  y
     *  / \                  / \
     * T1  T2               T2  T3
     * 
     * @param y The root of subtree to rotate
     * @return New root after rotation
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        // Perform rotation
        x.right = y;
        y.left = T2;
        
        // Update heights (order matters: y first, then x)
        updateHeight(y);
        updateHeight(x);
        
        return x; // New root
    }
    
    /**
     * Left rotation (used for Right-Right case)
     * 
     *   x                    y
     *  / \                  / \
     * T1  y        -->     x   T3
     *    / \              / \
     *   T2  T3           T1  T2
     * 
     * @param x The root of subtree to rotate
     * @return New root after rotation
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        // Perform rotation
        y.left = x;
        x.right = T2;
        
        // Update heights (order matters: x first, then y)
        updateHeight(x);
        updateHeight(y);
        
        return y; // New root
    }
    
    /**
     * Left-Right rotation (used for Left-Right case)
     * @param node The root of subtree to rotate
     * @return New root after rotation
     */
    private Node rotateLeftRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }
    
    /**
     * Right-Left rotation (used for Right-Left case)
     * @param node The root of subtree to rotate
     * @return New root after rotation
     */
    private Node rotateRightLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }
    
    // ======================= CORE OPERATIONS =======================
    
    /**
     * Insert a value into the AVL tree
     * @param data The value to insert
     */
    public void insert(int data) {
        root = insertHelper(root, data);
        size++;
    }
    
    /**
     * Helper method for inserting a value
     * @param node Current node in recursion
     * @param data Value to insert
     * @return Root of the updated subtree
     */
    private Node insertHelper(Node node, int data) {
        // Step 1: Perform normal BST insertion
        if (node == null) {
            return new Node(data);
        }
        
        if (data < node.data) {
            node.left = insertHelper(node.left, data);
        } else if (data > node.data) {
            node.right = insertHelper(node.right, data);
        } else {
            // Duplicate values not allowed in this implementation
            size--; // Revert size increment
            return node;
        }
        
        // Step 2: Update height of current node
        updateHeight(node);
        
        // Step 3: Get balance factor and perform rotations if needed
        int balance = getBalanceFactor(node);
        
        // Left-Left case
        if (balance > 1 && data < node.left.data) {
            return rotateRight(node);
        }
        
        // Right-Right case
        if (balance < -1 && data > node.right.data) {
            return rotateLeft(node);
        }
        
        // Left-Right case
        if (balance > 1 && data > node.left.data) {
            return rotateLeftRight(node);
        }
        
        // Right-Left case
        if (balance < -1 && data < node.right.data) {
            return rotateRightLeft(node);
        }
        
        // No rotation needed
        return node;
    }
    
    /**
     * Delete a value from the AVL tree
     * @param data The value to delete
     * @return true if value was found and deleted, false otherwise
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
        // Step 1: Perform normal BST deletion
        if (node == null) {
            return node;
        }
        
        if (data < node.data) {
            node.left = deleteHelper(node.left, data);
        } else if (data > node.data) {
            node.right = deleteHelper(node.right, data);
        } else {
            // Node to be deleted found
            size--;
            
            // Case 1: Node has no children or only right child
            if (node.left == null) {
                return node.right;
            }
            // Case 2: Node has only left child
            else if (node.right == null) {
                return node.left;
            }
            // Case 3: Node has two children
            else {
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = deleteHelper(node.right, successor.data);
                size++; // Compensate for the extra decrement in recursive call
            }
        }
        
        // Step 2: Update height of current node
        updateHeight(node);
        
        // Step 3: Get balance factor and perform rotations if needed
        int balance = getBalanceFactor(node);
        
        // Left-Left case
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }
        
        // Left-Right case
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            return rotateLeftRight(node);
        }
        
        // Right-Right case
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }
        
        // Right-Left case
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            return rotateRightLeft(node);
        }
        
        // No rotation needed
        return node;
    }
    
    /**
     * Search for a value in the AVL tree
     * @param data The value to search for
     * @return true if value exists, false otherwise
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
        if (node == null) {
            return false;
        }
        
        if (data == node.data) {
            return true;
        } else if (data < node.data) {
            return searchHelper(node.left, data);
        } else {
            return searchHelper(node.right, data);
        }
    }
    
    // ======================= TRAVERSAL METHODS =======================
    
    /**
     * In-order traversal (Left -> Root -> Right)
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
     * Pre-order traversal (Root -> Left -> Right)
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
     * Post-order traversal (Left -> Right -> Root)
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
    
    // ======================= UTILITY PUBLIC METHODS =======================
    
    /**
     * Get the size of the tree
     * @return Number of nodes in the tree
     */
    public int size() {
        return size;
    }
    
    /**
     * Check if the tree is empty
     * @return true if tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Get the height of the tree
     * @return Height of the tree (0 if empty)
     */
    public int getTreeHeight() {
        return getHeight(root);
    }
    
    /**
     * Check if the tree is balanced (for verification)
     * @return true if tree is balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalancedHelper(root);
    }
    
    private boolean isBalancedHelper(Node node) {
        if (node == null) {
            return true;
        }
        
        int balance = getBalanceFactor(node);
        return Math.abs(balance) <= 1 && 
               isBalancedHelper(node.left) && 
               isBalancedHelper(node.right);
    }
    
    /**
     * Display the tree structure (visual representation)
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
            System.out.println(prefix + (isLast ? "└── " : "├── ") + 
                             node.data + " (h:" + node.height + ", b:" + getBalanceFactor(node) + ")");
            
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
     * Main method for testing AVL Tree operations
     */
    public static void main(String[] args) {
        System.out.println("=== AVL Tree Implementation Demo ===\n");
        
        AVLTree avl = new AVLTree();
        
        // Test insertions
        System.out.println("1. Testing Insertions:");
        int[] values = {10, 20, 30, 40, 50, 25};
        
        for (int value : values) {
            System.out.println("Inserting: " + value);
            avl.insert(value);
        }
        
        System.out.println("\nTree after insertions:");
        avl.displayTree();
        System.out.println("Tree height: " + avl.getTreeHeight());
        System.out.println("Tree size: " + avl.size());
        System.out.println("Is balanced: " + avl.isBalanced());
        
        // Test traversals
        System.out.println("\n2. Testing Traversals:");
        avl.inorderTraversal();
        avl.preorderTraversal();
        avl.postorderTraversal();
        
        // Test search
        System.out.println("\n3. Testing Search:");
        int[] searchValues = {25, 35, 10, 100};
        for (int value : searchValues) {
            System.out.println("Search " + value + ": " + avl.search(value));
        }
        
        // Test deletions
        System.out.println("\n4. Testing Deletions:");
        int[] deleteValues = {30, 50, 10};
        
        for (int value : deleteValues) {
            System.out.println("\nDeleting: " + value);
            boolean deleted = avl.delete(value);
            System.out.println("Deleted: " + deleted);
            avl.displayTree();
            System.out.println("Tree height: " + avl.getTreeHeight());
            System.out.println("Is balanced: " + avl.isBalanced());
        }
        
        // Final state
        System.out.println("\n5. Final Tree State:");
        System.out.println("Size: " + avl.size());
        System.out.println("Height: " + avl.getTreeHeight());
        System.out.println("Is empty: " + avl.isEmpty());
        avl.inorderTraversal();
        
        // Test edge cases
        System.out.println("\n6. Testing Edge Cases:");
        
        // Insert duplicate
        System.out.println("Inserting duplicate value 20:");
        avl.insert(20);
        System.out.println("Size after duplicate insert: " + avl.size());
        
        // Delete non-existent value
        System.out.println("Deleting non-existent value 100:");
        boolean deleted = avl.delete(100);
        System.out.println("Deleted: " + deleted);
        System.out.println("Size after non-existent delete: " + avl.size());
        
        // Create tree that requires all rotation types
        System.out.println("\n7. Testing All Rotation Types:");
        AVLTree rotationTest = new AVLTree();
        
        // Left-Left case
        System.out.println("Left-Left case (inserting 30, 20, 10):");
        rotationTest.insert(30);
        rotationTest.insert(20);
        rotationTest.insert(10);
        rotationTest.displayTree();
        
        System.out.println("\n=== AVL Tree Demo Complete ===");
    }
}
