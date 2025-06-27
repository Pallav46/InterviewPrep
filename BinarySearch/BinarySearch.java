package BinarySearch;

/**
 * Binary Search Algorithm Implementation
 * 
 * Binary Search is a fundamental divide-and-conquer algorithm that efficiently finds
 * a target value in a sorted array by repeatedly dividing the search interval in half.
 * It's one of the most important algorithms to master for technical interviews.
 * 
 * Key Properties:
 * - Works only on sorted arrays
 * - Divides search space in half at each step
 * - Compares target with middle element
 * - Time Complexity: O(log n)
 * - Space Complexity: O(1) iterative, O(log n) recursive
 * 
 * Common Variations:
 * - Find exact target
 * - Find first/last occurrence
 * - Find insertion position
 * - Search in rotated sorted array
 * 
 * @author Interview Preparation
 */
public class BinarySearch {
    
    // ======================= BASIC BINARY SEARCH =======================
    
    /**
     * Iterative Binary Search - Most Common Implementation
     * Searches for target value in sorted array
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr Sorted array to search in
     * @param target Value to find
     * @return Index of target if found, -1 if not found
     */
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            // Prevent overflow: use left + (right - left) / 2
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;  // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        
        return -1; // Target not found
    }
    
    /**
     * Recursive Binary Search
     * Demonstrates recursive approach to binary search
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) due to recursion stack
     * 
     * @param arr Sorted array to search in
     * @param target Value to find
     * @param left Left boundary of search
     * @param right Right boundary of search
     * @return Index of target if found, -1 if not found
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        // Base case: search space is empty
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }
    
    /**
     * Wrapper method for recursive binary search
     * @param arr Sorted array to search in
     * @param target Value to find
     * @return Index of target if found, -1 if not found
     */
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }
    
    // ======================= ADVANCED BINARY SEARCH VARIATIONS =======================
    
    /**
     * Find First Occurrence
     * Finds the leftmost occurrence of target in sorted array with duplicates
     * 
     * @param arr Sorted array (may contain duplicates)
     * @param target Value to find
     * @return Index of first occurrence, -1 if not found
     */
    public static int findFirstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;           // Store potential result
                right = mid - 1;       // Continue searching left for first occurrence
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Find Last Occurrence
     * Finds the rightmost occurrence of target in sorted array with duplicates
     * 
     * @param arr Sorted array (may contain duplicates)
     * @param target Value to find
     * @return Index of last occurrence, -1 if not found
     */
    public static int findLastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;           // Store potential result
                left = mid + 1;        // Continue searching right for last occurrence
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Find Insertion Position
     * Finds the index where target should be inserted to maintain sorted order
     * This is equivalent to finding the "lower bound"
     * 
     * @param arr Sorted array
     * @param target Value to find insertion position for
     * @return Index where target should be inserted
     */
    public static int findInsertionPosition(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Count Occurrences
     * Counts total occurrences of target in sorted array
     * Uses first and last occurrence methods
     * 
     * @param arr Sorted array
     * @param target Value to count
     * @return Number of occurrences
     */
    public static int countOccurrences(int[] arr, int target) {
        int first = findFirstOccurrence(arr, target);
        if (first == -1) {
            return 0; // Target not found
        }
        
        int last = findLastOccurrence(arr, target);
        return last - first + 1;
    }
    
    // ======================= ADVANCED PROBLEMS =======================
    
    /**
     * Search in Rotated Sorted Array
     * Classic interview problem: array was sorted but then rotated
     * Example: [4,5,6,7,0,1,2] (original: [0,1,2,4,5,6,7])
     * 
     * @param arr Rotated sorted array
     * @param target Value to find
     * @return Index of target if found, -1 if not found
     */
    public static int searchInRotatedArray(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            // Check which half is sorted
            if (arr[left] <= arr[mid]) {
                // Left half is sorted
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1; // Target is in left half
                } else {
                    left = mid + 1;  // Target is in right half
                }
            } else {
                // Right half is sorted
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;  // Target is in right half
                } else {
                    right = mid - 1; // Target is in left half
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Find Peak Element
     * Peak element is greater than its neighbors
     * Array may have multiple peaks, return any one
     * 
     * @param arr Array of integers
     * @return Index of any peak element
     */
    public static int findPeakElement(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[mid + 1]) {
                // Peak is in left half (including mid)
                right = mid;
            } else {
                // Peak is in right half
                left = mid + 1;
            }
        }
        
        return left; // left == right at this point
    }
    
    /**
     * Find Minimum in Rotated Sorted Array
     * Find the minimum element in a rotated sorted array
     * 
     * @param arr Rotated sorted array with no duplicates
     * @return Minimum element in the array
     */
    public static int findMinInRotatedArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[right]) {
                // Minimum is in right half
                left = mid + 1;
            } else {
                // Minimum is in left half (including mid)
                right = mid;
            }
        }
        
        return arr[left];
    }
    
    /**
     * Square Root using Binary Search
     * Find integer square root of a number using binary search
     * 
     * @param x Non-negative integer
     * @return Integer square root of x
     */
    public static int sqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        int left = 1;
        int right = x;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (mid <= x / mid) { // Avoid overflow: mid * mid <= x
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // ======================= UTILITY METHODS =======================
    
    /**
     * Verify if array is sorted (ascending order)
     * @param arr Array to check
     * @return true if sorted, false otherwise
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Print array with indices for debugging
     * @param arr Array to print
     */
    public static void printArrayWithIndices(int[] arr) {
        System.out.print("Indices: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        
        System.out.print("Values:  ");
        for (int value : arr) {
            System.out.printf("%2d ", value);
        }
        System.out.println();
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing Binary Search implementations
     */
    public static void main(String[] args) {
        System.out.println("=== Binary Search Algorithm Demo ===\n");
        
        // Test basic binary search
        System.out.println("1. Testing Basic Binary Search:");
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        printArrayWithIndices(sortedArray);
        
        int[] targets = {7, 2, 19, 1, 20};
        for (int target : targets) {
            int iterativeResult = binarySearchIterative(sortedArray, target);
            int recursiveResult = binarySearchRecursive(sortedArray, target);
            System.out.printf("Search %d: Iterative=%d, Recursive=%d%n", 
                            target, iterativeResult, recursiveResult);
        }
        
        // Test with duplicates
        System.out.println("\n2. Testing with Duplicates:");
        int[] arrayWithDuplicates = {1, 2, 2, 2, 3, 4, 4, 5, 6, 6, 6, 7};
        printArrayWithIndices(arrayWithDuplicates);
        
        int duplicateTarget = 2;
        System.out.printf("Target: %d%n", duplicateTarget);
        System.out.printf("First occurrence: %d%n", findFirstOccurrence(arrayWithDuplicates, duplicateTarget));
        System.out.printf("Last occurrence: %d%n", findLastOccurrence(arrayWithDuplicates, duplicateTarget));
        System.out.printf("Count: %d%n", countOccurrences(arrayWithDuplicates, duplicateTarget));
        
        duplicateTarget = 6;
        System.out.printf("Target: %d%n", duplicateTarget);
        System.out.printf("First occurrence: %d%n", findFirstOccurrence(arrayWithDuplicates, duplicateTarget));
        System.out.printf("Last occurrence: %d%n", findLastOccurrence(arrayWithDuplicates, duplicateTarget));
        System.out.printf("Count: %d%n", countOccurrences(arrayWithDuplicates, duplicateTarget));
        
        // Test insertion position
        System.out.println("\n3. Testing Insertion Position:");
        int[] insertionArray = {1, 3, 5, 7, 9};
        printArrayWithIndices(insertionArray);
        
        int[] insertionTargets = {0, 2, 4, 6, 8, 10};
        for (int target : insertionTargets) {
            int position = findInsertionPosition(insertionArray, target);
            System.out.printf("Insert %d at position: %d%n", target, position);
        }
        
        // Test rotated array search
        System.out.println("\n4. Testing Rotated Array Search:");
        int[] rotatedArray = {4, 5, 6, 7, 0, 1, 2};
        printArrayWithIndices(rotatedArray);
        System.out.println("Original sorted: [0, 1, 2, 4, 5, 6, 7]");
        
        int[] rotatedTargets = {0, 3, 4, 6, 7};
        for (int target : rotatedTargets) {
            int result = searchInRotatedArray(rotatedArray, target);
            System.out.printf("Search %d in rotated array: %d%n", target, result);
        }
        
        // Test peak finding
        System.out.println("\n5. Testing Peak Element:");
        int[] peakArray = {1, 2, 3, 1};
        printArrayWithIndices(peakArray);
        int peak = findPeakElement(peakArray);
        System.out.printf("Peak element at index %d, value: %d%n", peak, peakArray[peak]);
        
        int[] peakArray2 = {1, 2, 1, 3, 5, 6, 4};
        printArrayWithIndices(peakArray2);
        peak = findPeakElement(peakArray2);
        System.out.printf("Peak element at index %d, value: %d%n", peak, peakArray2[peak]);
        
        // Test minimum in rotated array
        System.out.println("\n6. Testing Minimum in Rotated Array:");
        int[] minRotatedArray = {4, 5, 6, 7, 0, 1, 2};
        printArrayWithIndices(minRotatedArray);
        int min = findMinInRotatedArray(minRotatedArray);
        System.out.printf("Minimum element: %d%n", min);
        
        // Test square root
        System.out.println("\n7. Testing Square Root:");
        int[] sqrtInputs = {0, 1, 4, 8, 9, 16, 25, 100};
        for (int x : sqrtInputs) {
            int result = sqrt(x);
            System.out.printf("sqrt(%d) = %d (actual: %.2f)%n", x, result, Math.sqrt(x));
        }
        
        // Performance comparison
        System.out.println("\n8. Performance Comparison:");
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2; // Even numbers: 0, 2, 4, 6, ...
        }
        
        int searchTarget = 999998;
        
        // Linear search for comparison
        long startTime = System.nanoTime();
        int linearResult = -1;
        for (int i = 0; i < largeArray.length; i++) {
            if (largeArray[i] == searchTarget) {
                linearResult = i;
                break;
            }
        }
        long linearTime = System.nanoTime() - startTime;
        
        // Binary search
        startTime = System.nanoTime();
        int binaryResult = binarySearchIterative(largeArray, searchTarget);
        long binaryTime = System.nanoTime() - startTime;
        
        System.out.printf("Array size: %,d elements%n", largeArray.length);
        System.out.printf("Target: %d%n", searchTarget);
        System.out.printf("Linear search result: %d, Time: %,d ns%n", linearResult, linearTime);
        System.out.printf("Binary search result: %d, Time: %,d ns%n", binaryResult, binaryTime);
        System.out.printf("Binary search is %.1fx faster%n", (double) linearTime / binaryTime);
        
        System.out.println("\n=== Binary Search Demo Complete ===");
    }
}
