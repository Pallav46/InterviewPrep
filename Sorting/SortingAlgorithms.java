package Sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Comprehensive Sorting Algorithms Implementation
 * 
 * Essential sorting algorithms every programmer should know for technical interviews.
 * Includes both comparison-based and non-comparison-based sorting algorithms.
 * 
 * Algorithms Implemented:
 * 1. Quick Sort - O(n log n) average, O(n²) worst
 * 2. Merge Sort - O(n log n) guaranteed
 * 3. Bubble Sort - O(n²) 
 * 4. Selection Sort - O(n²)
 * 5. Insertion Sort - O(n²) but efficient for small arrays
 * 6. Counting Sort - O(n + k) non-comparison based
 * 7. Radix Sort - O(d × (n + k)) for integers
 * 
 * @author Interview Preparation
 */
public class SortingAlgorithms {
    
    // ======================= QUICK SORT =======================
    
    /**
     * Quick Sort Implementation
     * Divide and conquer algorithm that picks a pivot and partitions array
     * 
     * Average Time Complexity: O(n log n)
     * Worst Time Complexity: O(n²) - when pivot is always smallest/largest
     * Best Time Complexity: O(n log n)
     * Space Complexity: O(log n) - recursion stack
     * 
     * @param arr Array to sort
     * @param low Starting index
     * @param high Ending index
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition array and get pivot index
            int pivotIndex = partition(arr, low, high);
            
            // Recursively sort elements before and after partition
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    
    /**
     * Partition function for Quick Sort
     * Places pivot in correct position and partitions array
     * 
     * @param arr Array to partition
     * @param low Starting index
     * @param high Ending index
     * @return Final position of pivot
     */
    private static int partition(int[] arr, int low, int high) {
        // Choose rightmost element as pivot
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        // Place pivot in correct position
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /**
     * Randomized Quick Sort - Better average performance
     * @param arr Array to sort
     * @param low Starting index
     * @param high Ending index
     */
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Randomly choose pivot and swap with last element
            Random rand = new Random();
            int randomIndex = low + rand.nextInt(high - low + 1);
            swap(arr, randomIndex, high);
            
            int pivotIndex = partition(arr, low, high);
            randomizedQuickSort(arr, low, pivotIndex - 1);
            randomizedQuickSort(arr, pivotIndex + 1, high);
        }
    }
    
    // ======================= MERGE SORT =======================
    
    /**
     * Merge Sort Implementation
     * Stable, divide and conquer algorithm with guaranteed O(n log n) performance
     * 
     * Time Complexity: O(n log n) - always
     * Space Complexity: O(n) - temporary arrays
     * 
     * @param arr Array to sort
     * @param left Starting index
     * @param right Ending index
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }
    
    /**
     * Merge function for Merge Sort
     * Merges two sorted subarrays into one sorted array
     * 
     * @param arr Array containing subarrays
     * @param left Starting index of first subarray
     * @param mid Ending index of first subarray
     * @param right Ending index of second subarray
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // Sizes of subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temporary arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        
        // Merge temporary arrays back into arr[left..right]
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    // ======================= BUBBLE SORT =======================
    
    /**
     * Bubble Sort Implementation
     * Simple comparison-based algorithm that repeatedly steps through list
     * 
     * Time Complexity: O(n²) average and worst case, O(n) best case
     * Space Complexity: O(1)
     * 
     * @param arr Array to sort
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            
            // If no swapping occurred, array is sorted
            if (!swapped) break;
        }
    }
    
    // ======================= SELECTION SORT =======================
    
    /**
     * Selection Sort Implementation
     * Finds minimum element and places it at beginning
     * 
     * Time Complexity: O(n²) - always
     * Space Complexity: O(1)
     * 
     * @param arr Array to sort
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Find minimum element in remaining unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap found minimum element with first element
            swap(arr, minIndex, i);
        }
    }
    
    // ======================= INSERTION SORT =======================
    
    /**
     * Insertion Sort Implementation
     * Builds final sorted array one element at a time
     * Efficient for small datasets and nearly sorted arrays
     * 
     * Time Complexity: O(n²) average and worst, O(n) best case
     * Space Complexity: O(1)
     * 
     * @param arr Array to sort
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // Move elements greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    // ======================= COUNTING SORT =======================
    
    /**
     * Counting Sort Implementation
     * Non-comparison based sorting algorithm for integers with limited range
     * 
     * Time Complexity: O(n + k) where k is range of input
     * Space Complexity: O(k)
     * 
     * @param arr Array to sort
     * @param maxValue Maximum value in array
     */
    public static void countingSort(int[] arr, int maxValue) {
        int n = arr.length;
        
        // Create count array and output array
        int[] count = new int[maxValue + 1];
        int[] output = new int[n];
        
        // Store count of each element
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }
        
        // Change count[i] to actual position of element in output array
        for (int i = 1; i <= maxValue; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        
        // Copy output array back to original array
        System.arraycopy(output, 0, arr, 0, n);
    }
    
    // ======================= RADIX SORT =======================
    
    /**
     * Radix Sort Implementation
     * Non-comparison based sorting for integers
     * 
     * Time Complexity: O(d × (n + k)) where d is number of digits
     * Space Complexity: O(n + k)
     * 
     * @param arr Array to sort
     */
    public static void radixSort(int[] arr) {
        // Find maximum number to know number of digits
        int max = getMax(arr);
        
        // Do counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }
    
    /**
     * Counting sort for specific digit position
     * @param arr Array to sort
     * @param exp Exponent representing digit position
     */
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0-9 digits
        
        // Store count of occurrences
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }
        
        // Change count[i] to actual position
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        
        // Copy output array back to original
        System.arraycopy(output, 0, arr, 0, n);
    }
    
    // ======================= UTILITY METHODS =======================
    
    /**
     * Swap two elements in array
     * @param arr Array
     * @param i First index
     * @param j Second index
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Find maximum element in array
     * @param arr Array
     * @return Maximum element
     */
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    /**
     * Print array
     * @param arr Array to print
     */
    @SuppressWarnings("unused")
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * Check if array is sorted
     * @param arr Array to check
     * @return true if sorted, false otherwise
     */
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Generate random array for testing
     * @param size Size of array
     * @param maxValue Maximum value for elements
     * @return Random array
     */
    private static int[] generateRandomArray(int size, int maxValue) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxValue + 1);
        }
        return arr;
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing all sorting algorithms
     */
    public static void main(String[] args) {
        System.out.println("=== Sorting Algorithms Demo ===\n");
        
        // Test array
        int[] originalArray = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("Original Array: " + Arrays.toString(originalArray));
        System.out.println();
        
        // Test Quick Sort
        System.out.println("1. Quick Sort:");
        int[] quickSortArray = originalArray.clone();
        long startTime = System.nanoTime();
        quickSort(quickSortArray, 0, quickSortArray.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Result: " + Arrays.toString(quickSortArray));
        System.out.println("Time: " + (endTime - startTime) / 1000 + " microseconds");
        System.out.println("Sorted correctly: " + isSorted(quickSortArray));
        System.out.println();
        
        // Test Merge Sort
        System.out.println("2. Merge Sort:");
        int[] mergeSortArray = originalArray.clone();
        startTime = System.nanoTime();
        mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("Result: " + Arrays.toString(mergeSortArray));
        System.out.println("Time: " + (endTime - startTime) / 1000 + " microseconds");
        System.out.println("Sorted correctly: " + isSorted(mergeSortArray));
        System.out.println();
        
        // Test Bubble Sort
        System.out.println("3. Bubble Sort:");
        int[] bubbleSortArray = originalArray.clone();
        startTime = System.nanoTime();
        bubbleSort(bubbleSortArray);
        endTime = System.nanoTime();
        System.out.println("Result: " + Arrays.toString(bubbleSortArray));
        System.out.println("Time: " + (endTime - startTime) / 1000 + " microseconds");
        System.out.println("Sorted correctly: " + isSorted(bubbleSortArray));
        System.out.println();
        
        // Test Selection Sort
        System.out.println("4. Selection Sort:");
        int[] selectionSortArray = originalArray.clone();
        startTime = System.nanoTime();
        selectionSort(selectionSortArray);
        endTime = System.nanoTime();
        System.out.println("Result: " + Arrays.toString(selectionSortArray));
        System.out.println("Time: " + (endTime - startTime) / 1000 + " microseconds");
        System.out.println("Sorted correctly: " + isSorted(selectionSortArray));
        System.out.println();
        
        // Test Insertion Sort
        System.out.println("5. Insertion Sort:");
        int[] insertionSortArray = originalArray.clone();
        startTime = System.nanoTime();
        insertionSort(insertionSortArray);
        endTime = System.nanoTime();
        System.out.println("Result: " + Arrays.toString(insertionSortArray));
        System.out.println("Time: " + (endTime - startTime) / 1000 + " microseconds");
        System.out.println("Sorted correctly: " + isSorted(insertionSortArray));
        System.out.println();
        
        // Test Counting Sort (for small positive integers)
        System.out.println("6. Counting Sort:");
        int[] countingSortArray = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Input: " + Arrays.toString(countingSortArray));
        startTime = System.nanoTime();
        countingSort(countingSortArray, 8);
        endTime = System.nanoTime();
        System.out.println("Result: " + Arrays.toString(countingSortArray));
        System.out.println("Time: " + (endTime - startTime) / 1000 + " microseconds");
        System.out.println("Sorted correctly: " + isSorted(countingSortArray));
        System.out.println();
        
        // Test Radix Sort
        System.out.println("7. Radix Sort:");
        int[] radixSortArray = {170, 45, 75, 90, 2, 802, 24, 66};
        System.out.println("Input: " + Arrays.toString(radixSortArray));
        startTime = System.nanoTime();
        radixSort(radixSortArray);
        endTime = System.nanoTime();
        System.out.println("Result: " + Arrays.toString(radixSortArray));
        System.out.println("Time: " + (endTime - startTime) / 1000 + " microseconds");
        System.out.println("Sorted correctly: " + isSorted(radixSortArray));
        System.out.println();
        
        // Performance comparison with larger array
        System.out.println("=== Performance Comparison ===");
        int[] largeArray = generateRandomArray(1000, 1000);
        
        System.out.println("Testing with 1000 random elements:");
        
        // Quick Sort
        int[] testArray = largeArray.clone();
        startTime = System.nanoTime();
        quickSort(testArray, 0, testArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick Sort: " + (endTime - startTime) / 1000 + " microseconds");
        
        // Merge Sort
        testArray = largeArray.clone();
        startTime = System.nanoTime();
        mergeSort(testArray, 0, testArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("Merge Sort: " + (endTime - startTime) / 1000 + " microseconds");
        
        // Randomized Quick Sort
        testArray = largeArray.clone();
        startTime = System.nanoTime();
        randomizedQuickSort(testArray, 0, testArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("Randomized Quick Sort: " + (endTime - startTime) / 1000 + " microseconds");
        
        System.out.println("\n=== Sorting Algorithms Demo Complete ===");
    }
}
