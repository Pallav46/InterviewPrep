package DynamicProgramming;

import java.util.*;

/**
 * Dynamic Programming Implementation - Classic Problems
 * 
 * Dynamic Programming is an optimization technique that solves complex problems
 * by breaking them down into simpler subproblems. It stores the results of
 * subproblems to avoid computing the same results again.
 * 
 * Key Principles:
 * 1. Optimal Substructure: Optimal solution contains optimal solutions of subproblems
 * 2. Overlapping Subproblems: Same subproblems appear multiple times
 * 
 * Approaches:
 * 1. Memoization (Top-down): Recursion + Caching
 * 2. Tabulation (Bottom-up): Iterative + Table filling
 * 
 * Classic Problems Implemented:
 * 1. Fibonacci Numbers
 * 2. 0/1 Knapsack Problem
 * 3. Longest Common Subsequence (LCS)
 * 4. Longest Increasing Subsequence (LIS)
 * 5. Coin Change Problem
 * 6. Edit Distance
 * 7. Maximum Subarray Sum (Kadane's Algorithm)
 * 8. House Robber Problem
 * 
 * @author Interview Preparation
 */
public class DynamicProgramming {
    
    // ======================= 1. FIBONACCI NUMBERS =======================
    
    /**
     * Fibonacci using Memoization (Top-down DP)
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static long fibonacciMemo(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        return fibonacciMemoHelper(n, memo);
    }
    
    private static long fibonacciMemoHelper(int n, Map<Integer, Long> memo) {
        if (n <= 1) return n;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        long result = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    /**
     * Fibonacci using Tabulation (Bottom-up DP)
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static long fibonacciDP(int n) {
        if (n <= 1) return n;
        
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    /**
     * Fibonacci Space Optimized
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static long fibonacciOptimized(int n) {
        if (n <= 1) return n;
        
        long prev2 = 0, prev1 = 1, current = 0;
        
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return current;
    }
    
    // ======================= 2. 0/1 KNAPSACK PROBLEM =======================
    
    /**
     * 0/1 Knapsack Problem
     * Given weights and values of items, put items in knapsack of capacity W
     * to get maximum value
     * 
     * Time Complexity: O(n * W), Space Complexity: O(n * W)
     */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        // Build table in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                // If weight of current item is more than capacity, skip it
                if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // Take maximum of including or excluding current item
                    int include = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    int exclude = dp[i - 1][w];
                    dp[i][w] = Math.max(include, exclude);
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    /**
     * Print items included in optimal knapsack solution
     */
    public static List<Integer> knapsackItems(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], 
                                       dp[i - 1][w]);
                }
            }
        }
        
        // Backtrack to find items
        List<Integer> items = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                items.add(i - 1); // Item index
                w -= weights[i - 1];
            }
        }
        
        Collections.reverse(items);
        return items;
    }
    
    // ======================= 3. LONGEST COMMON SUBSEQUENCE =======================
    
    /**
     * Longest Common Subsequence (LCS)
     * Find length of longest subsequence common to both strings
     * 
     * Time Complexity: O(m * n), Space Complexity: O(m * n)
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Print the actual LCS string
     */
    public static String printLCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Backtrack to construct LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return lcs.reverse().toString();
    }
    
    // ======================= 4. LONGEST INCREASING SUBSEQUENCE =======================
    
    /**
     * Longest Increasing Subsequence (LIS)
     * Find length of longest subsequence where elements are in increasing order
     * 
     * Time Complexity: O(n²), Space Complexity: O(n)
     */
    public static int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each element forms a subsequence of length 1
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return Arrays.stream(dp).max().orElse(0);
    }
    
    /**
     * LIS using Binary Search (O(n log n) approach)
     */
    public static int longestIncreasingSubsequenceOptimal(int[] nums) {
        if (nums.length == 0) return 0;
        
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int left = 0, right = tails.size();
            
            // Binary search for insertion position
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            if (left == tails.size()) {
                tails.add(num);
            } else {
                tails.set(left, num);
            }
        }
        
        return tails.size();
    }
    
    // ======================= 5. COIN CHANGE PROBLEM =======================
    
    /**
     * Coin Change - Minimum coins needed to make amount
     * Time Complexity: O(amount * coins.length), Space Complexity: O(amount)
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Initialize with impossible value
        dp[0] = 0; // 0 coins needed to make amount 0
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Coin Change - Number of ways to make amount
     */
    public static int coinChangeWays(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // One way to make amount 0
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    
    // ======================= 6. EDIT DISTANCE =======================
    
    /**
     * Edit Distance (Levenshtein Distance)
     * Minimum operations (insert, delete, replace) to convert string1 to string2
     * 
     * Time Complexity: O(m * n), Space Complexity: O(m * n)
     */
    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i; // Delete all characters
        for (int j = 0; j <= n; j++) dp[0][j] = j; // Insert all characters
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i - 1][j],     // Delete
                                dp[i][j - 1]),     // Insert
                        dp[i - 1][j - 1]           // Replace
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    // ======================= 7. MAXIMUM SUBARRAY SUM =======================
    
    /**
     * Maximum Subarray Sum (Kadane's Algorithm)
     * Find maximum sum of contiguous subarray
     * 
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxSubarraySum(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    /**
     * Return the actual subarray with maximum sum
     */
    public static int[] maxSubarray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0, end = 0, tempStart = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum += nums[i];
            }
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        
        return Arrays.copyOfRange(nums, start, end + 1);
    }
    
    // ======================= 8. HOUSE ROBBER PROBLEM =======================
    
    /**
     * House Robber - Maximum money without robbing adjacent houses
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int houseRobber(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * House Robber II - Houses are in a circle (first and last are adjacent)
     */
    public static int houseRobberII(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Case 1: Rob house 0, can't rob house n-1
        int case1 = houseRobberHelper(nums, 0, nums.length - 2);
        
        // Case 2: Don't rob house 0, can rob house n-1
        int case2 = houseRobberHelper(nums, 1, nums.length - 1);
        
        return Math.max(case1, case2);
    }
    
    private static int houseRobberHelper(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing Dynamic Programming solutions
     */
    public static void main(String[] args) {
        System.out.println("=== Dynamic Programming Implementation Demo ===\n");
        
        // Test Fibonacci
        System.out.println("1. Fibonacci Numbers:");
        int n = 10;
        System.out.println("Fibonacci(" + n + "):");
        System.out.println("Memoization: " + fibonacciMemo(n));
        System.out.println("Tabulation: " + fibonacciDP(n));
        System.out.println("Optimized: " + fibonacciOptimized(n));
        
        // Test 0/1 Knapsack
        System.out.println("\n2. 0/1 Knapsack Problem:");
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Capacity: " + capacity);
        System.out.println("Maximum value: " + knapsack01(weights, values, capacity));
        System.out.println("Items included: " + knapsackItems(weights, values, capacity));
        
        // Test LCS
        System.out.println("\n3. Longest Common Subsequence:");
        String text1 = "ABCDGH";
        String text2 = "AEDFHR";
        System.out.println("String 1: " + text1);
        System.out.println("String 2: " + text2);
        System.out.println("LCS length: " + longestCommonSubsequence(text1, text2));
        System.out.println("LCS: " + printLCS(text1, text2));
        
        // Test LIS
        System.out.println("\n4. Longest Increasing Subsequence:");
        int[] lisArray = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Array: " + Arrays.toString(lisArray));
        System.out.println("LIS length (O(n²)): " + longestIncreasingSubsequence(lisArray));
        System.out.println("LIS length (O(n log n)): " + longestIncreasingSubsequenceOptimal(lisArray));
        
        // Test Coin Change
        System.out.println("\n5. Coin Change:");
        int[] coins = {1, 3, 4};
        int amount = 6;
        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Amount: " + amount);
        System.out.println("Minimum coins: " + coinChange(coins, amount));
        System.out.println("Number of ways: " + coinChangeWays(coins, amount));
        
        // Test Edit Distance
        System.out.println("\n6. Edit Distance:");
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);
        System.out.println("Edit distance: " + editDistance(word1, word2));
        
        // Test Maximum Subarray
        System.out.println("\n7. Maximum Subarray Sum:");
        int[] subarrayArray = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Array: " + Arrays.toString(subarrayArray));
        System.out.println("Maximum sum: " + maxSubarraySum(subarrayArray));
        System.out.println("Maximum subarray: " + Arrays.toString(maxSubarray(subarrayArray)));
        
        // Test House Robber
        System.out.println("\n8. House Robber:");
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("House values: " + Arrays.toString(houses));
        System.out.println("Maximum money (linear): " + houseRobber(houses));
        
        int[] housesCircular = {2, 3, 2};
        System.out.println("House values (circular): " + Arrays.toString(housesCircular));
        System.out.println("Maximum money (circular): " + houseRobberII(housesCircular));
        
        // Performance comparison
        System.out.println("\n9. Performance Comparison (Fibonacci):");
        int largeN = 40;
        
        long start = System.nanoTime();
        fibonacciMemo(largeN);
        long memoTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        fibonacciDP(largeN);
        long dpTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        fibonacciOptimized(largeN);
        long optimizedTime = System.nanoTime() - start;
        
        System.out.println("Fibonacci(" + largeN + ") timing:");
        System.out.println("Memoization: " + memoTime / 1000 + " microseconds");
        System.out.println("Tabulation: " + dpTime / 1000 + " microseconds");
        System.out.println("Optimized: " + optimizedTime / 1000 + " microseconds");
        
        System.out.println("\n=== Dynamic Programming Demo Complete ===");
    }
}
