/**
 * Heap Sort Implementation using MaxHeap
 * 
 * This class demonstrates heap sort by utilizing the MaxHeap class methods.
 * Uses existing heap operations instead of rewriting heap logic.
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) for in-place version, O(n) for MaxHeap version
 */
public class HeapSort {
    
    /**
     * Sort array using MaxHeap class methods
     * Utilizes buildHeap(), getMax(), delete(), isEmpty() from MaxHeap
     * @param arr Array to be sorted
     * @return Sorted array in ascending order
     */
    public static int[] heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        
        // Create MaxHeap with array size
        MaxHeap heap = new MaxHeap(arr.length);
        
        // Step 1: Build heap from array using MaxHeap's buildHeap method
        heap.buildHeap(arr);
        
        System.out.println("Initial Max Heap:");
        heap.print();
        
        // Step 2: Extract max elements one by one using MaxHeap methods
        int[] sortedArray = new int[arr.length];
        int index = arr.length - 1;
        
        while (!heap.isEmpty()) {
            int max = heap.getMax();    // Get maximum element
            heap.delete();              // Remove maximum element
            sortedArray[index--] = max; // Place in sorted position
        }
        
        return sortedArray;
    }
    
    /**
     * In-place heap sort using MaxHeap's heapify logic
     * @param arr Array to be sorted (modified in-place)
     */
    public static void heapSortInPlace(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        // Create temporary MaxHeap to use its heapify method
        MaxHeap tempHeap = new MaxHeap(arr.length);
        
        // Build heap in the original array
        tempHeap.buildHeap(arr);
        
        // Copy the heapified array back
        for (int i = 0; i < arr.length; i++) {
            arr[i] = tempHeap.arr[i];
        }
        
        System.out.println("Array after building max heap:");
        printArray(arr);
        
        // Extract elements from heap one by one
        int heapSize = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            // Move current root (maximum) to end
            swap(arr, 0, i);
            heapSize--;
            
            // Use MaxHeap's heapify logic by creating temp heap
            MaxHeap temp = new MaxHeap(heapSize);
            for (int j = 0; j < heapSize; j++) {
                temp.arr[j] = arr[j];
            }
            temp.size = heapSize;
            temp.heapify(0);
            
            // Copy back the heapified portion
            for (int j = 0; j < heapSize; j++) {
                arr[j] = temp.arr[j];
            }
            
            System.out.println("After extracting max element:");
            printArray(arr);
        }
    }
    
    /**
     * Utility method to swap two elements in array
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Utility method to print array
     */
    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    
    // ==================== DEMONSTRATION AND TESTING ====================
    
    /**
     * Main method to demonstrate heap sort using MaxHeap methods
     */
    public static void main(String[] args) {
        // Test array
        int[] testArray1 = {64, 34, 25, 12, 22, 11, 90};
        int[] testArray2 = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("==================== HEAP SORT DEMONSTRATION ====================");
        System.out.println("Original Array:");
        printArray(testArray1);
        
        // Method 1: Using MaxHeap class methods
        System.out.println("\n=== METHOD 1: Using MaxHeap Class Methods ===");
        int[] sortedArray1 = heapSort(testArray1.clone());
        System.out.println("Sorted Array (using MaxHeap methods):");
        printArray(sortedArray1);
        
        // Method 2: In-place heap sort using MaxHeap logic
        System.out.println("\n=== METHOD 2: In-Place Heap Sort ===");
        heapSortInPlace(testArray2);
        System.out.println("Final Sorted Array (in-place):");
        printArray(testArray2);
        
        // Test with different arrays
        System.out.println("\n=== ADDITIONAL TESTS ===");
        testDifferentArrays();
    }
    
    /**
     * Test heap sort with different types of arrays
     */
    private static void testDifferentArrays() {
        // Already sorted array
        int[] sortedArray = {1, 2, 3, 4, 5};
        System.out.println("Testing with already sorted array:");
        printArray(sortedArray);
        int[] result1 = heapSort(sortedArray.clone());
        System.out.println("Result:");
        printArray(result1);
        
        // Reverse sorted array
        int[] reverseArray = {5, 4, 3, 2, 1};
        System.out.println("\nTesting with reverse sorted array:");
        printArray(reverseArray);
        int[] result2 = heapSort(reverseArray.clone());
        System.out.println("Result:");
        printArray(result2);
        
        // Array with duplicates
        int[] duplicateArray = {3, 7, 3, 1, 7, 1, 9, 3};
        System.out.println("\nTesting with array containing duplicates:");
        printArray(duplicateArray);
        int[] result3 = heapSort(duplicateArray.clone());
        System.out.println("Result:");
        printArray(result3);
    }
}
