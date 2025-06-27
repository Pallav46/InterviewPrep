/**
 * Max Heap Implementation
 * 
 * A complete binary tree where parent node is always greater than or equal to its children.
 * Useful for: Priority Queues, Heap Sort, finding Kth largest element
 * 
 * Time Complexities:
 * - Insert: O(log n)
 * - Delete Max: O(log n)
 * - Get Max: O(1)
 * - Build Heap: O(n)
 * 
 * Space Complexity: O(n)
 */
public class MaxHeap {
    int[] arr;      // Array to store heap elements
    int size;       // Current number of elements in heap
    int max_size;   // Maximum capacity of heap

    /**
     * Constructor to initialize heap with given capacity
     * @param n Maximum capacity of heap
     */
    MaxHeap(int n) {
        this.arr = new int [n];
        this.max_size = n;
        this.size = 0;
    }

    // ==================== CORE HEAP OPERATIONS ====================
    
    /**
     * Insert element into heap and maintain max heap property
     * Process:
     * 1. Add element at end of array
     * 2. Compare with parent and swap if needed (heapify up)
     * 3. Repeat until heap property is satisfied
     * 
     * @param ele Element to insert
     * Time Complexity: O(log n)
     */
    public void insert(int ele) {
        if(size == max_size) {
            System.out.println("Heap filled..");
            return;
        }

        // Step 1: Insert at end
        arr[size] = ele;
        int idx = size;
        size += 1;

        // Step 2: Heapify up - compare with parent and swap if needed
        while (idx > 0 && arr[(idx-1)/2] < arr[idx]) {
            // Swap with parent
            int temp = arr[idx];
            arr[idx] = arr[(idx-1)/2];
            arr[(idx-1)/2] = temp;

            // Move to parent index
            idx = (idx-1)/2;
        }

        System.out.println("Element inserted successfully..");
    }

    /**
     * Delete the maximum element (root) from heap
     * Process:
     * 1. Replace root with last element
     * 2. Reduce size
     * 3. Heapify down from root to maintain heap property
     * 
     * Time Complexity: O(log n)
     */
    public void delete() {
        if(size == 0) {
            System.out.println("Heap is empty, cannot delete element.");
            return;
        }

        // Step 1: Replace root with last element
        arr[0] = arr[size - 1];
        size -= 1;
        int idx = 0;

        // Step 2: Heapify down from root
        heapify(idx);

        System.out.println("Element deleted successfully..");
    }

    /**
     * Get the maximum element without removing it
     * In max heap, maximum element is always at root (index 0)
     * @return Maximum element or -1 if heap is empty
     * Time Complexity: O(1)
     */
    public int getMax() {
        if (size <= 0) {
            System.out.println("Heap is empty");
            return -1;
        }
        return arr[0];
    }

    // ==================== HEAP MAINTENANCE (HELPER METHODS) ====================
    
    /**
     * Heapify down - maintain max heap property by moving element down
     * Used after deletion to restore heap property
     * 
     * @param idx Index from which to start heapifying
     * Time Complexity: O(log n)
     */
    public void heapify(int idx) {
        int largest = idx;          // Initialize largest as root
        int left = 2 * idx + 1;     // Left child index
        int right = 2 * idx + 2;    // Right child index

        // Check if left child exists and is greater than root
        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        // Check if right child exists and is greater than current largest
        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root, swap and continue heapifying
        if (largest != idx) {
            int temp = arr[idx];
            arr[idx] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(largest);
        }
    }

    /**
     * Build heap from an array (useful for heap sort)
     * Time Complexity: O(n) - more efficient than inserting elements one by one
     * @param array Array to convert to max heap
     */
    public void buildHeap(int[] array) {
        if (array.length > max_size) {
            System.out.println("Array size exceeds heap capacity");
            return;
        }
        
        size = array.length;
        for (int i = 0; i < size; i++) {
            arr[i] = array[i];
        }
        
        // Start from last non-leaf node and heapify
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    // ==================== ADVANCED OPERATIONS ====================
    
    /**
     * Increase value at given index
     * Used to modify an element and maintain heap property
     * @param index Index of element to modify
     * @param newValue New value (must be greater than current value)
     * Time Complexity: O(log n)
     */
    public void increaseKey(int index, int newValue) {
        if (index >= size) {
            System.out.println("Index out of bounds");
            return;
        }
        
        if (newValue < arr[index]) {
            System.out.println("New value is smaller than current value");
            return;
        }
        
        arr[index] = newValue;
        
        // Fix the max heap property if it's violated
        while (index > 0 && arr[(index - 1) / 2] < arr[index]) {
            int temp = arr[index];
            arr[index] = arr[(index - 1) / 2];
            arr[(index - 1) / 2] = temp;
            
            index = (index - 1) / 2;
        }
    }

    /**
     * Delete element at specific index
     * Strategy: Increase element to maximum value, then delete max
     * @param index Index of element to delete
     * Time Complexity: O(log n)
     */
    public void deleteAt(int index) {
        if (index >= size) {
            System.out.println("Index out of bounds");
            return;
        }
        
        // Increase the value to maximum and then extract max
        increaseKey(index, Integer.MAX_VALUE);
        delete();
    }

    // ==================== UTILITY METHODS ====================
    
    /**
     * Get current size of heap
     * @return Number of elements currently in heap
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if heap is empty
     * @return true if heap has no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if heap is full
     * @return true if heap has reached maximum capacity
     */
    public boolean isFull() {
        return size == max_size;
    }

    /**
     * Display all elements in the heap
     * Shows the array representation of the complete binary tree
     */
    public void print() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }
        
        System.out.print("Max Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Main method to demonstrate MaxHeap operations
     * Tests all major heap functionalities for verification
     */
    public static void main(String[] args) {
        // Create heap with capacity 10
        MaxHeap heap = new MaxHeap(10);
        
        // Test insertion
        System.out.println("=== Testing Insertion ===");
        heap.insert(10);
        heap.insert(20);
        heap.insert(15);
        heap.insert(40);
        heap.insert(50);
        heap.insert(100);
        heap.insert(25);
        
        heap.print(); // Should show max heap property
        
        // Test utility methods
        System.out.println("\n=== Testing Utility Methods ===");
        System.out.println("Max element: " + heap.getMax());
        System.out.println("Heap size: " + heap.getSize());
        System.out.println("Is heap full: " + heap.isFull());
        System.out.println("Is heap empty: " + heap.isEmpty());
        
        // Test deletion
        System.out.println("\n=== Testing Deletion ===");
        heap.delete();
        System.out.println("After deleting max:");
        heap.print();
        
        // Test buildHeap functionality
        System.out.println("\n=== Testing BuildHeap ===");
        int[] testArray = {3, 9, 2, 1, 4, 5};
        MaxHeap heap2 = new MaxHeap(10);
        heap2.buildHeap(testArray);
        System.out.println("Heap built from array [3, 9, 2, 1, 4, 5]:");
        heap2.print();
        
        // Test increaseKey and deleteAt
        System.out.println("\n=== Testing Advanced Operations ===");
        System.out.println("Increasing key at index 2 to 15:");
        heap2.increaseKey(2, 15);
        heap2.print();
        
        System.out.println("Deleting element at index 1:");
        heap2.deleteAt(1);
        heap2.print();
    }
}
