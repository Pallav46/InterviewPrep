package RecursionBacktracking;

import java.util.*;

/**
 * Recursion and Backtracking Algorithm Collection
 * 
 * Recursion is a fundamental programming technique where a function calls itself
 * to solve smaller instances of the same problem. Backtracking is a specific
 * algorithmic approach that uses recursion to explore all possible solutions
 * and "backtracks" when it hits a dead end.
 * 
 * Key Concepts:
 * - Base case: Condition that stops recursion
 * - Recursive case: Function calls itself with modified parameters
 * - Backtracking: Systematic way to explore solution space
 * - State space tree: Tree of all possible solutions
 * 
 * Common Patterns:
 * 1. Mathematical recursion (factorial, fibonacci)
 * 2. Tree/Graph traversal
 * 3. Combinatorial problems (permutations, combinations)
 * 4. Puzzle solving (N-Queens, Sudoku)
 * 5. Path finding problems
 * 
 * @author Interview Preparation
 */
public class RecursionBacktracking {
    
    // ======================= BASIC RECURSION EXAMPLES =======================
    
    /**
     * Factorial using recursion
     * Classic example of mathematical recursion
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to call stack
     * 
     * @param n Non-negative integer
     * @return n! (factorial of n)
     */
    public static long factorial(int n) {
        // Base case
        if (n <= 1) {
            return 1;
        }
        
        // Recursive case
        return n * factorial(n - 1);
    }
    
    /**
     * Fibonacci sequence using recursion
     * Demonstrates exponential time complexity problem
     * 
     * Time Complexity: O(2^n) - very inefficient
     * Space Complexity: O(n) due to call stack depth
     * 
     * @param n Position in fibonacci sequence
     * @return nth fibonacci number
     */
    public static long fibonacciNaive(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Recursive case
        return fibonacciNaive(n - 1) + fibonacciNaive(n - 2);
    }
    
    /**
     * Fibonacci with memoization
     * Optimized version using dynamic programming
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param n Position in fibonacci sequence
     * @param memo Memoization map
     * @return nth fibonacci number
     */
    public static long fibonacciMemo(int n, Map<Integer, Long> memo) {
        if (n <= 1) {
            return n;
        }
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    /**
     * Power function using recursion
     * Calculate base^exponent efficiently
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)
     * 
     * @param base Base number
     * @param exponent Non-negative exponent
     * @return base^exponent
     */
    public static long power(int base, int exponent) {
        // Base case
        if (exponent == 0) {
            return 1;
        }
        
        // Optimize by dividing exponent by 2
        if (exponent % 2 == 0) {
            long half = power(base, exponent / 2);
            return half * half;
        } else {
            return base * power(base, exponent - 1);
        }
    }
    
    /**
     * Greatest Common Divisor using Euclidean algorithm
     * Classic recursive algorithm
     * 
     * Time Complexity: O(log(min(a,b)))
     * Space Complexity: O(log(min(a,b)))
     * 
     * @param a First number
     * @param b Second number
     * @return GCD of a and b
     */
    public static int gcd(int a, int b) {
        // Base case
        if (b == 0) {
            return a;
        }
        
        // Recursive case
        return gcd(b, a % b);
    }
    
    // ======================= ARRAY/STRING RECURSION =======================
    
    /**
     * Reverse a string using recursion
     * 
     * @param str String to reverse
     * @return Reversed string
     */
    public static String reverseString(String str) {
        // Base case
        if (str == null || str.length() <= 1) {
            return str;
        }
        
        // Recursive case
        return reverseString(str.substring(1)) + str.charAt(0);
    }
    
    /**
     * Check if string is palindrome using recursion
     * 
     * @param str String to check
     * @param left Left pointer
     * @param right Right pointer
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String str, int left, int right) {
        // Base case
        if (left >= right) {
            return true;
        }
        
        // Check current characters and recurse
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        return isPalindrome(str, left + 1, right - 1);
    }
    
    /**
     * Binary search using recursion
     * 
     * @param arr Sorted array
     * @param target Target value
     * @param left Left boundary
     * @param right Right boundary
     * @return Index of target or -1 if not found
     */
    public static int binarySearch(int[] arr, int target, int left, int right) {
        // Base case
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, right);
        } else {
            return binarySearch(arr, target, left, mid - 1);
        }
    }
    
    // ======================= BACKTRACKING PROBLEMS =======================
    
    /**
     * Generate all permutations of an array
     * Classic backtracking problem
     * 
     * Time Complexity: O(n! * n)
     * Space Complexity: O(n)
     * 
     * @param nums Array of numbers
     * @return List of all permutations
     */
    public static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermutations(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }
    
    private static void backtrackPermutations(int[] nums, List<Integer> current, 
                                            boolean[] used, List<List<Integer>> result) {
        // Base case: found complete permutation
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try each unused number
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // Choose
                current.add(nums[i]);
                used[i] = true;
                
                // Explore
                backtrackPermutations(nums, current, used, result);
                
                // Unchoose (backtrack)
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
    
    /**
     * Generate all combinations of k numbers from 1 to n
     * 
     * Time Complexity: O(C(n,k) * k)
     * Space Complexity: O(k)
     * 
     * @param n Upper limit
     * @param k Size of combinations
     * @return List of all combinations
     */
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombinations(1, n, k, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrackCombinations(int start, int n, int k, 
                                            List<Integer> current, List<List<Integer>> result) {
        // Base case: found complete combination
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try each number from start to n
        for (int i = start; i <= n; i++) {
            // Choose
            current.add(i);
            
            // Explore
            backtrackCombinations(i + 1, n, k, current, result);
            
            // Unchoose (backtrack)
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Generate all subsets (power set)
     * 
     * Time Complexity: O(2^n * n)
     * Space Complexity: O(n)
     * 
     * @param nums Array of numbers
     * @return List of all subsets
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrackSubsets(int[] nums, int start, 
                                       List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(current));
        
        // Try adding each remaining element
        for (int i = start; i < nums.length; i++) {
            // Choose
            current.add(nums[i]);
            
            // Explore
            backtrackSubsets(nums, i + 1, current, result);
            
            // Unchoose (backtrack)
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * N-Queens Problem
     * Place N queens on NxN chessboard so none attack each other
     * 
     * @param n Size of chessboard
     * @return List of all valid solutions
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n]; // queens[i] = column position of queen in row i
        backtrackNQueens(0, n, queens, result);
        return result;
    }
    
    private static void backtrackNQueens(int row, int n, int[] queens, 
                                       List<List<String>> result) {
        // Base case: placed all queens
        if (row == n) {
            result.add(constructBoard(queens, n));
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isValidQueenPlacement(queens, row, col)) {
                // Choose
                queens[row] = col;
                
                // Explore
                backtrackNQueens(row + 1, n, queens, result);
                
                // Unchoose (backtrack) - not needed as we overwrite queens[row]
            }
        }
    }
    
    private static boolean isValidQueenPlacement(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            int prevCol = queens[i];
            
            // Check column conflict
            if (prevCol == col) {
                return false;
            }
            
            // Check diagonal conflicts
            if (Math.abs(prevCol - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }
    
    private static List<String> constructBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
    
    /**
     * Sudoku Solver
     * Solve 9x9 Sudoku puzzle using backtracking
     * 
     * @param board 9x9 Sudoku board ('.' for empty cells)
     * @return true if solved, false if no solution
     */
    public static boolean solveSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    // Try digits 1-9
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (isValidSudokuPlacement(board, row, col, digit)) {
                            // Choose
                            board[row][col] = digit;
                            
                            // Explore
                            if (solveSudoku(board)) {
                                return true;
                            }
                            
                            // Unchoose (backtrack)
                            board[row][col] = '.';
                        }
                    }
                    return false; // No valid digit found
                }
            }
        }
        return true; // All cells filled
    }
    
    private static boolean isValidSudokuPlacement(char[][] board, int row, int col, char digit) {
        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == digit) {
                return false;
            }
        }
        
        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == digit) {
                return false;
            }
        }
        
        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == digit) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Word Search in 2D grid
     * Find if word exists in character grid
     * 
     * @param board 2D character grid
     * @param word Word to search for
     * @return true if word found, false otherwise
     */
    public static boolean wordSearch(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (wordSearchDFS(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean wordSearchDFS(char[][] board, String word, 
                                       int row, int col, int index) {
        // Base case: found complete word
        if (index == word.length()) {
            return true;
        }
        
        // Check bounds and character match
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
            board[row][col] != word.charAt(index) || board[row][col] == '#') {
            return false;
        }
        
        // Mark cell as visited
        char temp = board[row][col];
        board[row][col] = '#';
        
        // Explore all 4 directions
        boolean found = wordSearchDFS(board, word, row + 1, col, index + 1) ||
                       wordSearchDFS(board, word, row - 1, col, index + 1) ||
                       wordSearchDFS(board, word, row, col + 1, index + 1) ||
                       wordSearchDFS(board, word, row, col - 1, index + 1);
        
        // Backtrack: restore cell
        board[row][col] = temp;
        
        return found;
    }
    
    // ======================= UTILITY METHODS =======================
    
    /**
     * Print 2D character board
     */
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Print list of lists
     */
    public static void printListOfLists(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing recursion and backtracking algorithms
     */
    public static void main(String[] args) {
        System.out.println("=== Recursion and Backtracking Demo ===\n");
        
        // Test basic recursion
        System.out.println("1. Testing Basic Recursion:");
        System.out.println("Factorial(5) = " + factorial(5));
        System.out.println("Power(2, 10) = " + power(2, 10));
        System.out.println("GCD(48, 18) = " + gcd(48, 18));
        
        // Test fibonacci with performance comparison
        System.out.println("\n2. Testing Fibonacci:");
        int n = 30;
        
        long start = System.nanoTime();
        long result = fibonacciNaive(n);
        long naiveTime = System.nanoTime() - start;
        System.out.printf("Naive Fibonacci(%d) = %d, Time: %,d ns%n", n, result, naiveTime);
        
        start = System.nanoTime();
        result = fibonacciMemo(n, new HashMap<>());
        long memoTime = System.nanoTime() - start;
        System.out.printf("Memoized Fibonacci(%d) = %d, Time: %,d ns%n", n, result, memoTime);
        System.out.printf("Speedup: %.1fx%n", (double) naiveTime / memoTime);
        
        // Test string operations
        System.out.println("\n3. Testing String Operations:");
        String testStr = "racecar";
        System.out.println("Original: " + testStr);
        System.out.println("Reversed: " + reverseString(testStr));
        System.out.println("Is palindrome: " + isPalindrome(testStr, 0, testStr.length() - 1));
        
        // Test binary search
        System.out.println("\n4. Testing Recursive Binary Search:");
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 7;
        int index = binarySearch(sortedArray, target, 0, sortedArray.length - 1);
        System.out.printf("Binary search for %d in %s: index %d%n", 
                         target, Arrays.toString(sortedArray), index);
        
        // Test permutations
        System.out.println("\n5. Testing Permutations:");
        int[] permArray = {1, 2, 3};
        List<List<Integer>> perms = permutations(permArray);
        System.out.printf("Permutations of %s:%n", Arrays.toString(permArray));
        printListOfLists(perms);
        
        // Test combinations
        System.out.println("\n6. Testing Combinations:");
        List<List<Integer>> combs = combinations(4, 2);
        System.out.println("Combinations of 2 from {1,2,3,4}:");
        printListOfLists(combs);
        
        // Test subsets
        System.out.println("\n7. Testing Subsets:");
        int[] subsetArray = {1, 2, 3};
        List<List<Integer>> subs = subsets(subsetArray);
        System.out.printf("Subsets of %s:%n", Arrays.toString(subsetArray));
        printListOfLists(subs);
        
        // Test N-Queens
        System.out.println("\n8. Testing N-Queens (4x4):");
        List<List<String>> queensSolutions = solveNQueens(4);
        System.out.printf("Found %d solutions for 4-Queens:%n", queensSolutions.size());
        for (int i = 0; i < queensSolutions.size() && i < 2; i++) {
            System.out.printf("Solution %d:%n", i + 1);
            for (String row : queensSolutions.get(i)) {
                System.out.println(row);
            }
            System.out.println();
        }
        
        // Test Sudoku
        System.out.println("9. Testing Sudoku Solver:");
        char[][] sudoku = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        System.out.println("Original Sudoku:");
        printBoard(sudoku);
        
        if (solveSudoku(sudoku)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(sudoku);
        } else {
            System.out.println("No solution found!");
        }
        
        // Test Word Search
        System.out.println("\n10. Testing Word Search:");
        char[][] wordBoard = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String searchWord = "ABCCED";
        
        System.out.println("Board:");
        printBoard(wordBoard);
        System.out.printf("Search for '%s': %b%n", searchWord, wordSearch(wordBoard, searchWord));
        
        searchWord = "SEE";
        System.out.printf("Search for '%s': %b%n", searchWord, wordSearch(wordBoard, searchWord));
        
        searchWord = "ABCB";
        System.out.printf("Search for '%s': %b%n", searchWord, wordSearch(wordBoard, searchWord));
        
        System.out.println("\n=== Recursion and Backtracking Demo Complete ===");
    }
}
